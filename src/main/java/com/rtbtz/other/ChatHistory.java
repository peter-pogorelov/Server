package com.rtbtz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains history of chat messages
 * @author Petr
 */
public class ChatHistory {
    private int size = 100; //Default size of chat history
    private static ChatHistory instance;
    private RingBuffer buff;
    
    private ChatHistory(){
        buff = new RingBuffer(size);
    }
    
    //Change buffer size but save previous messages
    synchronized public void resize(int size){
        this.size = size;
        buff = new RingBuffer(buff, size);
    }
    
    synchronized public static ChatHistory getInstance() {
        if(instance == null)
            instance = new ChatHistory();
        return instance; 
    }
    
    synchronized  public void addMessage(String message){
        buff.push(message);
    }
    
    //This method is only used in tests!
    synchronized public void clear(){
        buff = new RingBuffer(size);
    }
    
    //Returns last <size> messages in history
    synchronized public String getLastMessages(){
        StringBuilder build = new StringBuilder();
        
        buff.reset();
        for(int i = 0; i < buff.getLength(); i++){
            build.append(buff.getNext());
            if(i < buff.getLength() - 1){
                build.append("\n");
            }
        }
        
        return build.toString();
    }
}
