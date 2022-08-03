package com.store.app.bl.handlers.basket;


import com.store.app.bl.handlers.utils.HandlerUtilsFactory;
import com.store.app.bl.handlers.utils.Marker;
import com.store.app.bl.storage.AbstractDispatcher;
import com.store.app.bl.storage.db.Storage;
import com.store.app.bl.storage.db.goods.Goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
        Commands: clr, add, del, vw
 */
public class BasketHandler extends AbstractDispatcher {



    private String command;
    private enum BasketMode {START, ENTER_VALUE, EXECUTE}
    private BasketMode currentBasketMode = BasketMode.START;

    private String clr = "clr", add = "add", del = "del", vw = "vw";
    private Set<String> commandsSet = Set.of(clr, add, del, vw);

    List<Goods> basket = new ArrayList<>();


    private String getBasketMarkerList(){
        Marker marker = HandlerUtilsFactory.getMarkerImpl(this.basket);
        marker.getMarkedList(x -> true);
        return marker.toString();
    }

    private String clearMeth(){
        this.basket.clear();
        return "Your basket was cleaned.\n";
    }

    private String addMeth(Goods item){
        this.basket.add(item);
        return "Your item was added.\n";
    }

    private String delMeth(int selected){
        int index = selected - 1;
        this.basket.remove(index);
        return "The item was removed.\n";
    }

    private String viewMeth(){
        return this.getBasketMarkerList();
    }

    @Override
    protected String defaultStep(String s) {
        super.setProcess(true);
        super.setCurrentStep(Steps.COMMAND);
        return s + "\n" + "Commands: " + commandsSet;
    }

    @Override
    protected String command(String request) {
        boolean res = commandsSet.contains(request);
        if (res) {
            this.command = request;
            super.setCurrentStep(Steps.EXECUTE);
        }else{
            return "Not correct the command\n";
        }

        if (currentBasketMode == BasketMode.ENTER_VALUE){
            this.currentBasketMode = BasketMode.EXECUTE;
            return "Enter value:";
        }

        return this.execute(request);
    }

    @Override
    protected String execute(String request) {

        String result = "";

        if (this.command.equals(this.add)){

            if (currentBasketMode == BasketMode.START) {

                this.currentBasketMode = BasketMode.ENTER_VALUE;
                result = this.command(request);
                return Storage.getMarkedList(x -> true) + result;

            }else if (currentBasketMode == BasketMode.EXECUTE) {

                try{

                    int selected = Integer.parseInt(request);
                    Goods item = Storage.getItem(selected);
                    result = this.addMeth(item);

                }catch(NumberFormatException ex){
                    return "Not a number";
                }catch(IndexOutOfBoundsException ex){
                    result = "Not correct number.\n";
                }
            }

        }else if (this.command.equals(this.clr)){

            result = this.clearMeth();

        }else if (this.command.equals(this.del)){

            if (currentBasketMode == BasketMode.START) {

                this.currentBasketMode = BasketMode.ENTER_VALUE;
                result = this.command(request);

                return this.getBasketMarkerList() + result;

            }else if (currentBasketMode == BasketMode.EXECUTE) {

                try{

                    int selected = Integer.parseInt(request);
                    result = this.delMeth(selected);

                }catch(NumberFormatException ex){
                    return "Not a number";
                }catch(IndexOutOfBoundsException ex){
                    result = "Not correct number.\n";
                }
            }

        }else if (this.command.equals(this.vw)){
            result = this.viewMeth();
        }

        this.currentBasketMode = BasketMode.START;
        super.setCurrentStep(Steps.DEFAULT);
        super.setProcess(false);
        return result;
    }

}