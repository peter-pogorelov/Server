package com.rtbtz.client;

import com.rtbtz.commands.Command;
import com.rtbtz.commands.CommandFactory;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wrapper for LowLevelClient with reading loop and some useful methods
 *
 * @author Petr
 */
public class Client extends LowLevelClient {
    //During this period client should show some activity
    public static final int MIN_ACTIVITY_DELAY = 1000;
    private static final String welcomeMessage = "Welcome to Chat 1.0, type /help to help yourself!";
    private String login;
    private int activityDelay = 5000;

    public Client(Socket sock, int activityDelay) throws Exception {
        super(sock);
        this.activityDelay = activityDelay;
    }

    /**
     * Intercepting client activity in different thread
     */
    @Override
    public void run() {
        setTimeout(getActivityDelay()); //Client should show some activity within specified time
        sendMessage(welcomeMessage); //Send client welcome message
        for (;;) {
            try {
                if (!isConnected()) {
                    break;
                }

                String line = getClientInput().readLine();
                String[] params = CommandFactory.getParamsFromResponce(line);

                if (params != null) {
                    Command command = CommandFactory.getInstance().commandFactory(params[CommandFactory.PARAM_CMD]);

                    if (command != null) {
                        command.exec(this, params[CommandFactory.PARAM_INFO]);
                    } else {
                        sendMessage("Unknown command [" + line + "].");
                    }
                }
            } catch (SocketTimeoutException e) { //If client was unactive for some time
                try {
                    kickWithMessage("You have been kicked due to unactivity.");
                    break;
                } catch (IOException iex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, iex);
                    kick();
                    break;
                }
            } catch (Exception ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                kick();
                break;
            }
        }
    }

    /**
     * Disconnects a client
     */
    public void kick() {
        try {
            closeConnection();
            ClientPool.getInstance().removeClient(this);
            if (isLogged()) { //If client wasnt logged no message
                ClientPool.getInstance().SendMessageToAll(login + " has left.");
            }
            System.out.println("Client disconnected.");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void kickWithMessage(String message) throws IOException {
        sendMessage(message);
        kick();
    }

    //Get a period when client should show some activity
    synchronized public int getActivityDelay() {
        return activityDelay;
    }

    //Set a period when client should show some activity
    synchronized public void setActivityDelay(int activityDelay) {
        if (activityDelay < MIN_ACTIVITY_DELAY) {
            activityDelay = MIN_ACTIVITY_DELAY;
        }
        
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
}
