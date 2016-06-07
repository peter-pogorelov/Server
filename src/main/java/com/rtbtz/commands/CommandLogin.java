package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.other.ChatHistory;
import com.rtbtz.client.Client;
import com.rtbtz.client.ClientPool;

/**
 * Used to for logging in a client to chat
 * @author Petr
 */
public class CommandLogin extends Command {
    public CommandLogin(){
        super("login to chat");
    }
    
    @Override
    public void exec(Client client, String info) throws IOException {
        if(!client.isLogged()){
            String name = info;
            if(ClientPool.getInstance().findClientByLogin(name) == null){ //Check is there already client with same name
                client.setLogin(name); //Sets a login
                client.sendMessage("You have logged to the server!");
                String messages = ChatHistory.getInstance().getLastMessages(); //Dislpay last N chat messages
                if(!messages.equals("")){
                    client.sendMessage(messages); 
                }
                ClientPool.getInstance().sendMessageToOthers(client, name + " has joined the conversation!"); //Inform other clients
            } else {
                client.sendMessage("This nickname is already in use.");
            }
        }
        else {
            client.sendMessage("You already have a nickname.");
        }
    }
}
