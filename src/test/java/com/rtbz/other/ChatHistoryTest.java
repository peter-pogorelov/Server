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
        ChatHistory.getInstance().addMessage("a");
        ChatHistory.getInstance().addMessage("b");
        ChatHistory.getInstance().addMessage("c");
        ChatHistory.getInstance().addMessage("d");
        ChatHistory.getInstance().addMessage("e");
        ChatHistory.getInstance().addMessage("f");
        ChatHistory.getInstance().clear();
        assertEquals("", ChatHistory.getInstance().getLastN(100));
    }
    
    @Test
    public void testGetLastN1 (){
        ChatHistory.getInstance().clear();
        assertEquals("", ChatHistory.getInstance().getLastN(-100));
        assertEquals("", ChatHistory.getInstance().getLastN(0));
        assertEquals("", ChatHistory.getInstance().getLastN(100));
    }
    
    @Test
    public void testGetLastN2 (){
        ChatHistory.getInstance().clear();
        assertEquals("", ChatHistory.getInstance().getLastN(0));
    }
    
    @Test
    public void testGetLastN3 (){
        ChatHistory.getInstance().clear();
        
        ChatHistory.getInstance().addMessage("a");
        ChatHistory.getInstance().addMessage("b");
        ChatHistory.getInstance().addMessage("c");
        ChatHistory.getInstance().addMessage("d");
        ChatHistory.getInstance().addMessage("e");
        ChatHistory.getInstance().addMessage("f");
        
        String result = "b\nc\nd\ne\nf";
        String result1 = "a\nb\nc\nd\ne\nf";
        
        assertEquals(result, ChatHistory.getInstance().getLastN(5));
        assertEquals(result1, ChatHistory.getInstance().getLastN(100));
    }
}
