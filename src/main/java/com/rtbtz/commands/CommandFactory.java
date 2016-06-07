package com.rtbtz.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for build commands
 * @author Petr
 */
public class CommandFactory {
    public static int PARAM_CMD = 0, PARAM_INFO = 1;
    public static CommandFactory instance = null;
    private Map<String, Command> commands = new HashMap<>();
    
    private CommandFactory(){}
    
    synchronized public static CommandFactory getInstance() {
        if(instance == null){
            instance = new CommandFactory();
        }
        
        return instance;
    }
    
    //Registers new command
    synchronized public void registerNewCommand(String cmd, Command command){
        commands.put(cmd, command);
    }
    
    //Returns command by its cmd
    synchronized public Command commandFactory(String cmd) {
        Command result = null;
        result = commands.get(cmd);
        
        return result;
    }
    
    //Returns a description of command
    synchronized public String getCommandsInformation(){
        StringBuilder info = new StringBuilder();
        for(Map.Entry<String, Command> it : commands.entrySet()){
            info.append(it.getKey()).append(" - ").append(it.getValue().getDescription()).append(".\n");
        }
        
        return info.toString();
    }
    
    /**
     * Method returns pair of command params: command itself and additional information
     * @param command
     * @return pair of value in array of string (0 - cmd, 1 - info)
     */
    public static String[] getParamsFromResponce(String command){
        command = command.trim();
        String[] params = new String[2];
        
        if(command.length() > 0 && command.charAt(0) == '/'){
            int lastSpace = command.indexOf(" ");
            if(lastSpace == -1){
                params[0] = command;
                params[1] = ""; //Empty parameters
                return params;
            }
            params[0] = command.substring(0, lastSpace);
            params[1] = command.substring(lastSpace + 1);
            return params;
        }
        
        return null;
    }
    
    public static void registerCommands(){
        CommandFactory.getInstance().registerNewCommand("/count", new CommandCount());
        CommandFactory.getInstance().registerNewCommand("/help", new CommandHelp());
        CommandFactory.getInstance().registerNewCommand("/say", new CommandSay());
        CommandFactory.getInstance().registerNewCommand("/quit", new CommandQuit());
        CommandFactory.getInstance().registerNewCommand("/login", new CommandLogin());
    }
}
