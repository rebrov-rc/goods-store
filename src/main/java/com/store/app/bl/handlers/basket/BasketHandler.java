package com.store.app.bl.handlers.basket;

import com.store.app.bl.Connectable;
import com.store.app.bl.Response;
import com.store.app.bl.storage.AbstractDispatcher;

import java.util.Map;

public class BasketHandler extends AbstractDispatcher {

    private Map<String, Connectable> handlers = BasketSourceFactory.getSources();
    private String command;

    @Override
    protected String defaultStep(String s) {
        super.start();
        return s + "Commands: " + handlers.keySet().toString();
    }

    @Override
    protected String command(String request) {
        boolean res = handlers.keySet().contains(request);
        if (res){
            this.command = request;
            super.setCurrentStep(Steps.EXECUTE);
            return this.execute("");
        }
        return "";
    }

    @Override
    protected String execute(String request) {
        Response res = this.handlers.get(this.command).connect(request);
        if (!res.process) {
            super.finish();
            this.command = null;
        }
        return res.response;
    }
}
