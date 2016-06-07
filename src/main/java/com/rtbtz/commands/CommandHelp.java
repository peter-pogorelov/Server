package com.rtbtz.commands;

import java.io.IOException;
import com.rtbtz.client.Client;

/**
 * Displays information about other commands
 * @author Petr
 */
public class CommandHelp extends Command {
    public CommandHelp(){
        super("show number of active users");
    }
    
    @Override
    public void exec(Client client, String info) throws IOException {
        client.sendMessage(CommandFactory.getInstance().getCommandsInformation());
    }
}
