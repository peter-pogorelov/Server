package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.client.Client;
import com.rtbtz.client.ClientPool;

/**
 * Allows client to exit a chat
 * @author Petr
 */
public class CommandQuit implements Command {
    @Override
    synchronized public void exec(Client client, String info) throws IOException {
        ClientPool.getInstance().SendMessageToOthers(client, client.getLogin() + " has left a conversation.");
        client.Kick();
    }
}
