package com.rtbtz.client;

import com.rtbtz.commands.Command;
import com.rtbtz.commands.CommandFactory;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wrapper for LowLevelClient with reading loop and some
 * useful methods
 * @author Petr
 */

public class Client extends LowLevelClient {
    private static String welcomeMessage = "Welcome to Chat 1.0, type /help to help yourself!";
    private String login;
    private int activityDelay = 5000;

    
    public Client(Socket sock, int activityDelay) throws Exception {
        super(sock);
        this.activityDelay = activityDelay;
    }

    /**
     * Interceptiong client activity in different thread
    */
    @Override
    public void run() {
        setTimeout(getActivityDelay()); //Client should show some activity within specified time
        SendMessage(welcomeMessage); //Send client welcome message
        for (;;) {
            try {
                if(!isConnected()){
                    break;
                }
                
                String line = GetClientInput().readLine();
                String cmd = CommandFactory.getCmdFromResponce(line);
                String info = CommandFactory.getInfoFromResponce(line);
                Command command = CommandFactory.getInstance().commandFactory(cmd);
                
                if (command != null) {
                    command.exec(this, info);
                } else {
                    SendMessage("Unknown command [" + line + "].");
                }
            } 
            catch (SocketTimeoutException e) { //If client was unactive for some time
                try{
                    KickWithMessage("You have been kicked due to unactivity.");
                    break;
                }
                catch(IOException iex){
                    System.out.println(iex.getMessage());
                    Kick();
                    break;
                }
            }
            catch (Exception ex) {
                System.out.println("Something went wrong: " + ex.getMessage());
                Kick();
                break;
            }
        }
    }

    /**
     * Disconnects a client
     */
    public void Kick() {
        try {
            CloseConnection();
            ClientPool.getInstance().removeClient(this);
            if (isLogged()) { //If client wasnt logged no message
                ClientPool.getInstance().SendMessageToAll(login + " has left.");
            }
            System.out.println("Client disconnected.");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void KickWithMessage(String message) throws IOException{
        SendMessage(message);
        Kick();
    }

    synchronized public int getActivityDelay() {
        if(activityDelay < 1000) {
            setActivityDelay(1000);
        }
        
        return activityDelay;
    }

    synchronized public void setActivityDelay(int activityDelay) {
        this.activityDelay = activityDelay;
    }
    
    synchronized public boolean isLogged() {
        return getLogin() != null;
    }

    synchronized public String getLogin() {
        return login;
    }

    synchronized public void setLogin(String login) {
        this.login = login;
    }
    
    synchronized public static String getWelcomeMessage() {
        return welcomeMessage;
    }

    synchronized public static void setWelcomeMessage(String aWelcomeMessage) {
        welcomeMessage = aWelcomeMessage;
    }
}
