package com.store.app.client;

public interface Client {
    String getRequest();
    void sendResponse(String response);
}
