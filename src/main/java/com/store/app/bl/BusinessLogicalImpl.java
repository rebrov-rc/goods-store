package com.store.app.bl;

import com.store.app.bl.handlers.HandlerSources;
import com.store.app.bl.storage.AbstractDispatcher;

import java.util.Map;


class BusinessLogicalImpl extends AbstractDispatcher {

    private Map<String, Connectable> handlers = HandlerSources.getSources();
    private String command;

    @Override
    protected String defaultStep(String defaultStr){
        super.setProcess(true);
        super.setCurrentStep(Steps.COMMAND);
        return defaultStr + "Commands: " + handlers.keySet().toString();
    }

    @Override
    protected String command(String command){
        boolean res = handlers.keySet().contains(command);
        if (res){
            this.command = command;
            super.setCurrentStep(Steps.EXECUTE);
            return this.execute("");
        }
        return "";
    }

    @Override
    protected String execute(String req){
        Response res = this.handlers.get(this.command).connect(req);
        if (!res.process) {
            super.setCurrentStep(Steps.DEFAULT);
            this.command = null;
            return this.defaultStep(res.response);
        }
        return res.response;
    }
}
