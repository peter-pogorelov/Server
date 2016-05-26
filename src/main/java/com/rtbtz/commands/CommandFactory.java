package com.rtbtz.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for build commands
 * @author Petr
 */
public class CommandFactory {
    public static CommandFactory instance = null;
    private Map<String, Command> commands = new HashMap<>();
    private Map<String, String> descriptions = new HashMap<>();
    
    private CommandFactory(){}
    
    synchronized public static CommandFactory getInstance() {
        if(instance == null){
            instance = new CommandFactory();
        }
        
        return instance;
    }
    
    //Registers new command
    synchronized public void registerNewCommand(String cmd, String description, Command command){
        commands.put(cmd, command);
        descriptions.put(cmd, description);
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
        for(Map.Entry<String, String> it : descriptions.entrySet()){
            info.append(it.getKey()).append(" - ").append(it.getValue()).append(".\n");
        }
        
        return info.toString();
    }
    
    //Returns cmd from clients responce
    public static String getCmdFromResponce(String command){
        command = command.trim();
        
        if(command.length() > 0 && command.charAt(0) == '/'){
            int lastSpace = command.indexOf(" ");
            if(lastSpace == -1){
                return command;
            }
            String cmd = command.substring(0, lastSpace);
            return cmd;
        }
        
        return "";
    }
    
    //Returns command information from clients responce
    public static String getInfoFromResponce(String command) {
        command = command.trim();
        if(command.length() > 0 && command.charAt(0) == '/'){
            int lastSpace = command.indexOf(" ");
            if(lastSpace == -1){
                return "";
            }
            String cmd = command.substring(lastSpace + 1);
            return cmd;
        }
        return "";
    }
    
    public static void registerCommands(){
        CommandFactory.getInstance().registerNewCommand("/count", "show number of active users", new CommandCount());
        CommandFactory.getInstance().registerNewCommand("/help", "output help information", new CommandHelp());
        CommandFactory.getInstance().registerNewCommand("/say", "print message to chat", new CommandSay());
        CommandFactory.getInstance().registerNewCommand("/quit", "leave the conversation", new CommandQuit());
        CommandFactory.getInstance().registerNewCommand("/login", "login to chat", new CommandLogin());
    }
}
