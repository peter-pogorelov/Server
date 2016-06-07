package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.client.Client;
import com.rtbtz.client.ClientPool;

/**
 * Displays current amount of clients
 * @author Petr
 */
public class CommandCount extends Command {
    public CommandCount(){
        super("output help information");
    }
    
    @Override
    public void exec(Client client, String info) throws IOException{
        client.sendMessage("Active users: " + Integer.toString(ClientPool.getInstance().getPoolSize()));
    }
}
