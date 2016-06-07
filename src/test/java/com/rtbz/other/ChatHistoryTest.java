package com.rtbz.other;

import com.rtbtz.other.ChatHistory;
import junit.framework.Assert;
import org.junit.Test;

/**
 * ChatHistory tests
 * @author Petr
 */
public class ChatHistoryTest extends Assert {
    @Test
    public void testGetLastN0 (){
        ChatHistory.getInstance().clear();
        ChatHistory.getInstance().resize(5);
        
        ChatHistory.getInstance().addMessage("a");
        ChatHistory.getInstance().addMessage("b");
        ChatHistory.getInstance().addMessage("c");
        ChatHistory.getInstance().addMessage("d");
        ChatHistory.getInstance().addMessage("e");
        ChatHistory.getInstance().addMessage("f");
        
        String result = "b\nc\nd\ne\nf";
        assertEquals(result, ChatHistory.getInstance().getLastMessages());
    }
    
    @Test
    public void testGetLastN1 (){
        ChatHistory.getInstance().clear();
        ChatHistory.getInstance().resize(5);
        
        String result = "";
        assertEquals(result, ChatHistory.getInstance().getLastMessages());
    }
    
    @Test
    public void testGetLastN2 (){
        ChatHistory.getInstance().clear();
        ChatHistory.getInstance().resize(5);
        ChatHistory.getInstance().addMessage("a");
        ChatHistory.getInstance().addMessage("b");
        ChatHistory.getInstance().addMessage("c");
        ChatHistory.getInstance().addMessage("d");
        
        String result = "a\nb\nc\nd";
        assertEquals(result, ChatHistory.getInstance().getLastMessages());
    }
    
    @Test
    public void testGetLastN3(){
        ChatHistory.getInstance().clear();
        ChatHistory.getInstance().resize(3);
        ChatHistory.getInstance().addMessage("a");
        ChatHistory.getInstance().addMessage("b");
        ChatHistory.getInstance().addMessage("c");
        ChatHistory.getInstance().addMessage("d");
        
        String result = "b\nc\nd";
        
        ChatHistory.getInstance().resize(5);
        assertEquals(result, ChatHistory.getInstance().getLastMessages());
    }
    
    @Test
    public void testGetLastN4(){
        ChatHistory.getInstance().clear();
        ChatHistory.getInstance().resize(3);
        ChatHistory.getInstance().addMessage("a");
        ChatHistory.getInstance().addMessage("b");
        ChatHistory.getInstance().addMessage("c");
        ChatHistory.getInstance().addMessage("d");
        
        ChatHistory.getInstance().resize(5);
        ChatHistory.getInstance().addMessage("e");
        
        String result = "b\nc\nd\ne";
        
        assertEquals(result, ChatHistory.getInstance().getLastMessages());
    }
}
