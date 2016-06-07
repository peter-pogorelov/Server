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
    public void testGetCMDFromResponce(){
        assertEquals("/hey", CommandFactory.getParamsFromResponce("/hey asd asd asd")[0]);
        assertNull(CommandFactory.getParamsFromResponce(""));
        assertNull(CommandFactory.getParamsFromResponce("asd asd"));
        assertEquals("/hey", CommandFactory.getParamsFromResponce("/hey")[0]);
    }
    
    @Test
    public void testGetInfoFromResponce() {
        assertNull(CommandFactory.getParamsFromResponce(""));
        assertEquals("", CommandFactory.getParamsFromResponce("/hey     ")[1]);
        assertNull(CommandFactory.getParamsFromResponce("asd /hey     "));
        assertEquals("/hey /hey", CommandFactory.getParamsFromResponce("/hey /hey /hey    ")[1]);
    }
}
