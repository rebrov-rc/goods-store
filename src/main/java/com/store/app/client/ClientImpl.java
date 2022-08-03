package com.store.app.client;

import java.util.Scanner;

class ClientImpl implements Client{

    Scanner scanner = new Scanner(System.in);

    @Override
    public String getRequest() {
        return this.scanner.nextLine();
    }

    @Override
    public void sendResponse(String response) {
        System.out.println(response);
    }
}
