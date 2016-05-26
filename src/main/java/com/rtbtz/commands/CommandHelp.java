package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.client.Client;

/**
 * Displays information about other commands
 * @author Petr
 */
public class CommandHelp implements Command {
    @Override
    public void exec(Client client, String info) throws IOException {
        client.SendMessage(CommandFactory.getInstance().getCommandsInformation());
    }
}
