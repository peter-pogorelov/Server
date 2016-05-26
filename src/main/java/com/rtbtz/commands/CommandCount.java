package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.client.Client;
import com.rtbtz.client.ClientPool;

/**
 * Displays current amount of clients
 * @author Petr
 */
public class CommandCount implements Command {
    @Override
    public void exec(Client client, String info) throws IOException{
        client.SendMessage("Active users: " + Integer.toString(ClientPool.getInstance().getPoolSize()));
    }
}
