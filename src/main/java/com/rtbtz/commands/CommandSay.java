package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.other.ChatHistory;
import com.rtbtz.client.Client;
import com.rtbtz.client.ClientPool;

/**
 * Send text message to other users
 * @author Petr
 */
public class CommandSay extends Command {
    public CommandSay(){
        super("login to chat");
    }
    
    @Override
    synchronized public void exec(Client client, String info) throws IOException{
        String message = client.getLogin() + ": " + info;
        if(client.isLogged()){
            ChatHistory.getInstance().addMessage(message);
            ClientPool.getInstance().sendMessageToOthers(client, message);
            System.out.println(message);
        } else {
            client.sendMessage("You are not logged yet.");
        }
    }
}
