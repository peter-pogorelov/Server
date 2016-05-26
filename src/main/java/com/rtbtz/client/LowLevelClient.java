package com.rtbtz.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client class that works with sockets directly
 * @author Petr
 */
public abstract class LowLevelClient extends Thread{
    private Socket clientSocket;
    private BufferedReader clientIn;
    private PrintWriter clientOut;
    
    LowLevelClient(Socket sock) throws Exception {
        try {
            clientSocket = sock;
            clientIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            clientOut = new PrintWriter(sock.getOutputStream());
        } catch (IOException ex) {
            throw ex;
        }
    }
    
    protected BufferedReader GetClientInput(){
        return clientIn;
    }
    
    //Sends text message to a client
    synchronized public void SendMessage(String message) {
        clientOut.println(message);
        clientOut.flush();
    }

    //Closes connection
    protected void CloseConnection() throws IOException {
        clientIn.close();
        clientOut.close();
        clientSocket.close();
    }
    
    //Sets timeout for a client. If client was unactive for <time> time he would be kicked 
    protected void setTimeout(int time){
        try{
            clientSocket.setSoTimeout(time);
        } catch (SocketException ex) {
            Logger.getLogger(LowLevelClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected boolean isConnected() {
        return !clientSocket.isClosed();
    }
}