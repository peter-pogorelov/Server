package com.rtbtz.commands;

import java.io.IOException;
import java.io.Serializable;
import com.rtbtz.client.Client;

/**
 * Some abstract command
 * @author Petr
 */
public abstract class Command implements Serializable {
    private String description;
    
    public Command(String description){
        this.description = description;
    }
    
    public abstract void exec(Client client, String info) throws IOException ; //Метод переопределяется в остальных классах

    public String getDescription() {
        return description;
    }
}
