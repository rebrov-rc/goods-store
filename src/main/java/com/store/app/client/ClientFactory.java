package com.store.app.client;

public class ClientFactory {
    public static Client getClient(){
        return new ClientImpl();
    }
}
