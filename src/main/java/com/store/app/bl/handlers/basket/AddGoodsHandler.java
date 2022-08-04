package com.store.app.bl.handlers.basket;

import com.store.app.bl.storage.AbstractDispatcher;
import com.store.app.bl.storage.db.Storage;
import com.store.app.bl.storage.db.goods.Goods;


class AddGoodsHandler extends AbstractDispatcher {


    @Override
    protected String defaultStep(String s) {
        super.start();
        return Storage.getMarkedList(x -> true) + "Enter value:";
    }

    @Override
    protected String command(String request) {
        return this.execute(request);
    }

    @Override
    protected String execute(String request) {
        try{

            int selected = Integer.parseInt(request);
            Goods item = Storage.getItem(selected);
            Storage.getBasket().add(item);

        }catch(NumberFormatException ex){
            return "Not a number";
        }catch(IndexOutOfBoundsException ex){
            super.finish();
            return "Not correct number.\n";
        }
        super.finish();
        return "Your item was added.\n";
    }
}
