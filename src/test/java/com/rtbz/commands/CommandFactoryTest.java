package com.rtbz.commands;

import com.rtbtz.commands.CommandFactory;
import junit.framework.Assert;
import org.junit.Test;

/**
 * CommandFactory tests
 * @author Petr
 */
public class CommandFactoryTest extends Assert {
    @Test
    public void testGetCommandsInformation(){
        
    }
    
    @Test
    public void testGetCmdFromResponce(){
        assertEquals("/hey", CommandFactory.getCmdFromResponce("/hey asd asd asd"));
        assertEquals("", CommandFactory.getCmdFromResponce(""));
        assertEquals("", CommandFactory.getCmdFromResponce("asd asd"));
        assertEquals("/hey", CommandFactory.getCmdFromResponce("/hey"));
    }
    
    @Test
    public void testGetInfoFromResponce() {
        assertEquals("", CommandFactory.getInfoFromResponce(""));
        assertEquals("", CommandFactory.getInfoFromResponce("/hey     "));
        assertEquals("", CommandFactory.getInfoFromResponce("asd /hey     "));
        assertEquals("/hey /hey", CommandFactory.getInfoFromResponce("/hey /hey /hey    "));
    }
}
