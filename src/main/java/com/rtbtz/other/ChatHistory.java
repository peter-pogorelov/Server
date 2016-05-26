package com.rtbtz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains history of chat messages
 * @author Petr
 */
public class ChatHistory {
    private static ChatHistory instance;
    private List<String> messages = new ArrayList<>();
    private ChatHistory(){}
    
    synchronized public static ChatHistory getInstance() {
        if(instance == null)
            instance = new ChatHistory();
        return instance; 
    }
    
    synchronized  public void addMessage(String message){
        messages.add(message);
    }
    
    synchronized public void clear(){
        messages.clear();
    }
    
    //Returns last N messages in history
    synchronized public String getLastN(int N){
        int startPos = 0;
        if(messages.size() > N) {
            startPos = messages.size() - N;
        }
        
        StringBuilder build = new StringBuilder();
        
        for(int i = startPos; i < messages.size(); ++i){
            build.append(messages.get(i));
            if(i < messages.size() - 1){
                build.append("\n");
            }
        }
        
        return build.toString();
    }
}
