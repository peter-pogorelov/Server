package com.rtbtz.server;

import com.rtbtz.client.ClientPool;
import com.rtbtz.commands.CommandFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main server class
 * @author Petr
 */
public class MCServer {
    public static final int DEFAULT_PORT = 8030;
    
    public static void main(String[] args) {
        System.out.println("Welcome to MultiClientChat Server!");
        
        java.net.ServerSocket serverSock;
        try {
            serverSock = new ServerSocket(DEFAULT_PORT);
        } catch (IOException ex) {
            throw new Error("Error during server initialization.");
        }
        
        System.out.println(serverSock.getLocalSocketAddress());
        CommandFactory.registerCommands();
        
        for(;;){
            Socket sock;
            try {
                sock = serverSock.accept();
                System.out.println("Client connected!");
                ClientPool.getInstance().addNewClient(sock);
            } catch (Exception ex) {
                //Record an exception and continue execution
                Logger.getLogger(MCServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
