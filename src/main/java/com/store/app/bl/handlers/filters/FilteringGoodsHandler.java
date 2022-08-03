package com.store.app.bl.handlers.filters;


import com.store.app.bl.storage.AbstractDispatcher;
import com.store.app.bl.storage.db.Storage;

import java.util.Set;

public class FilteringGoodsHandler extends AbstractDispatcher {

    private String command;
    private String byName = "name", byCost = "cost", byFactory = "factory";
    private Set<String> filterValueSet = Set.of(byName, byCost, byFactory);


    private String filteringForName(String name) {
        return Storage.getMarkedList(x -> x.getName().equals(name));
    }

    private String filteringForCost(int cost) {
        return Storage.getMarkedList(x -> x.getCost() == cost);
    }

    private String filteringForFactory(String factory) {
        return Storage.getMarkedList(x -> x.getFactory().equals(factory));
    }

    @Override
    protected String defaultStep(String s) {
        super.setProcess(true);
        super.setCurrentStep(Steps.COMMAND);
        return s + "Filtering by: " + filterValueSet;
    }
    @Override
    protected String command(String request) {
        boolean res = filterValueSet.contains(request);
        if (res) {
            this.command = request;
            super.setCurrentStep(Steps.EXECUTE);
        }else{
            return "Not correct the command";
        }

        return "Enter value:";

    }
    @Override
    protected String execute(String request) {
        String res = "";
        if (this.byName.equals(this.command)) {
            res = this.filteringForName(request);
        } else if (this.byCost.equals(this.command)) {
            try {
                int num = Integer.parseInt(request);
                res = this.filteringForCost(num);
            } catch (NumberFormatException exception) {
                return "Cost is not a number!";
            }

        } else if (this.byFactory.equals(this.command)) {
            res = this.filteringForFactory(request);
        }
        super.setCurrentStep(Steps.DEFAULT);
        super.setProcess(false);
        return res;
    }
}
