package com.store.app;

import com.store.app.bl.BLFactory;
import com.store.app.bl.Connectable;
import com.store.app.bl.Response;
import com.store.app.bl.storage.db.Storage;
import com.store.app.client.Client;
import com.store.app.client.ClientFactory;

/**
 *
 */
public class App {

    Client client = ClientFactory.getClient();
    Connectable bl = BLFactory.getBL();

    public void run(){

        Storage.init();
        client.sendResponse(bl.connect("").response); // default request.

        while(true){

            String clientRequest = client.getRequest();

            if (clientRequest.equals("--off")) break;

            Response response = bl.connect(clientRequest);

            client.sendResponse(response.response);

        }
    }
}