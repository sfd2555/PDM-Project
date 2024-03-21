package com.teamthree.pdmapi.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class ConnectionHandler {
    
    //CHANGE THIS VALUE IF YOU GET A BIND ERROR
    private static int LOCAL_PORT = 8080;
    private static String LOCAL_HOST = "127.0.0.1";
    
    private Connection dbConn = null;
    private Session session = null;

    private final String username;
    private final String password;
    private final String hostname;
    private final String database;
    private final int sshPort;
    private final int remotePort;

    public ConnectionHandler(@Value("${database.hostname}") String hostname, @Value("${database.database}") String database, @Value("${database.sshPort}") int sshPort, @Value("${database.remotePort}") int remotePort,
    @Value("${database.username}") String username, @Value("${database.password}") String password)  {
        this.hostname = hostname;
        this.database = database;
        this.sshPort = sshPort;
        this.remotePort = remotePort;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection(boolean forceNew) {

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

    public void closeConnection() {
        try {
            dbConn.close();
            session.disconnect();
            session = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
