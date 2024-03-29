package com.teamthree.pdmapi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;



/**
 * This class handles connecting to the database, and closing the connection as needed
 * 
 * @author Sean Droll
 */
@Component
public class ConnectionHandler {
    
    //CHANGE THIS VALUE IF YOU GET A BIND ERROR
    private static int LOCAL_PORT = 8081;
    private static String LOCAL_HOST = "127.0.0.1";
    
    private static Connection dbConn = null;
    private static Session session = null;

    private final String username;
    private final String password;
    private final String hostname;
    private final String database;
    private final int sshPort;
    private final int remotePort;

    private final Lock lock = new ReentrantLock();

    /**
     * Creates a new connection handler
     * @param hostname The hostname of the ssh tunnel destination
     * @param database name of the database to connect to
     * @param sshPort port of the shh tunnel destination
     * @param remotePort port of the sql connection port
     * @param username username for sql connection and ssh tunnel
     * @param password password for sql connection and ssh tunnel
     */
    public ConnectionHandler(@Value("${database.hostname}") String hostname, @Value("${database.database}") String database, @Value("${database.sshPort}") int sshPort, @Value("${database.remotePort}") int remotePort,
    @Value("${database.username}") String username, @Value("${database.password}") String password)  {
        this.hostname = hostname;
        this.database = database;
        this.sshPort = sshPort;
        this.remotePort = remotePort;
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a connection between host and database
     * @param forceNew forces a new connection
     * @return a connection to the database
     */
    public Connection getConnection(boolean forceNew) {
        lock.lock();

        //Check for a connection or if we are forcing a new connection
        if(forceNew || dbConn == null) {
        
            JSch jsch = new JSch();
            try {
                //If there is already a session, we can skip creating a new one, or else we get a bind error every time we connect
                if(session == null) { 

                    //Set up an SSH connection
                    session = jsch.getSession(username, hostname, sshPort);
                    session.setPassword(password);

                    //Sip Key Check
                    java.util.Properties config = new java.util.Properties();
                    config.put("StrictHostKeyChecking", "no");
            
                    session.setConfig(config);
                    //Create SSH connection
                    session.connect();
                    //Create SSH tunnel
                    session.setPortForwardingL(LOCAL_PORT, LOCAL_HOST, remotePort);
                }
                
                //Load postgresql drivers
                Class.forName("org.postgresql.Driver"); 
                //Create database connection routing through 127.0.0.1
                dbConn = DriverManager.getConnection("jdbc:postgresql://" + LOCAL_HOST + ":" + String.valueOf(LOCAL_PORT) + "/" + database, username, password);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (JSchException e) {
                e.printStackTrace();
            }
        }
        return dbConn;
    }

    /**
     * Closes the sql connection and ssh tunnel
     */
    public void closeConnection() {
        try {
            if(dbConn != null) {
                dbConn.close();
                dbConn = null;
            }
            session.disconnect();
            session = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

}
