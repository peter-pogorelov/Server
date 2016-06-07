package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.client.Client;
import com.rtbtz.client.ClientPool;

/**
 * Allows client to exit a chat
 * @author Petr
 */
public class CommandQuit extends Command {
    public CommandQuit(){
        super("leave the conversation");
    }
        
    @Override
    synchronized public void exec(Client client, String info) throws IOException {
        if(client.isLogged()){
            ClientPool.getInstance().sendMessageToOthers(client, client.getLogin() + " has left a conversation.");
        }
        client.kick();
    }
}
