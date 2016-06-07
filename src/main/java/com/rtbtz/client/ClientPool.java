package com.rtbtz.client;

import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Helper class for managing multiple clients
 * @author Petr
 */
public class ClientPool {
    //Used for singleton pattern
    private static ClientPool instance;
    //Linked list should be faster than other implementation of List for this purpose
    private List<Client> pool = new LinkedList<>();
    
    private ClientPool(){}
    
    synchronized public static ClientPool getInstance() {
        if(instance == null)
            return instance = new ClientPool();
        return instance;
    }
    
    //Adds new client and sets its timeout
    synchronized public void addNewClient(Socket sock) throws Exception{
        Client client = new Client(sock, 60*1000);
        pool.add(client);
        client.start();
    }
    
    //Searching for a cliend and removing it
    synchronized public void removeClient(Client client){
        Iterator<Client> it = pool.iterator();
        while(it.hasNext()){
            if(client.equals(it.next())){
                it.remove();
                break;
            }
        }
    }
    
    //Sends messages for all the clients
    synchronized public void SendMessageToAll(String message) {
        Iterator<Client> it = pool.iterator();
        while(it.hasNext()){
            Client client = it.next();
            if(client.isLogged()){
                client.sendMessage((String)message);
            }
        }
    }
    
    //Sends messages for all clients but specified client
    synchronized public void sendMessageToOthers(Client self, String message) {
        Iterator<Client> it = pool.iterator();
        while(it.hasNext()){
            Client client = it.next();
            if(client != self){
                if(client.isLogged()){
                    client.sendMessage((String)message);
                }
            }
        }
    }
    
    //Searches for a client by login
    synchronized public Client findClientByLogin(String login) {
        Iterator<Client> it = pool.iterator();
        while(it.hasNext()){
            Client client = it.next();
            if(client.getLogin() != null && client.getLogin().equals(login))
                return client;
        }
        
        return null;
    }
    
    //Looking is a client in pool
    synchronized public Client findClient(Client c) {
        Iterator<Client> it = pool.iterator();
        while(it.hasNext()){
            Client client = it.next();
            if(client.equals(c))
                return client;
        }
        
        return null;
    }
    
    synchronized public int getPoolSize() {
        return pool.size();
    }
}
