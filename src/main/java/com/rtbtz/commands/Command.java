package com.rtbtz.commands;

import java.io.IOException;
import java.io.Serializable;
import com.rtbtz.client.Client;

/**
 * Some abstract command
 * @author Petr
 */
public interface Command extends Serializable {
    public void exec(Client client, String info) throws IOException ; //Метод переопределяется в остальных классах
}
