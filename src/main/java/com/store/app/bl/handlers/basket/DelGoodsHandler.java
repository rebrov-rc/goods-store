package com.store.app.bl.handlers.basket;

import com.store.app.bl.handlers.utils.HandlerUtilsFactory;
import com.store.app.bl.handlers.utils.Marker;
import com.store.app.bl.storage.AbstractDispatcher;
import com.store.app.bl.storage.db.Storage;


class DelGoodsHandler extends AbstractDispatcher {


    @Override
    protected String defaultStep(String s) {
        super.start();
        Marker marker = HandlerUtilsFactory.getMarkerImpl(Storage.getBasket());
        marker.getMarkedList(x -> true);
        return marker.toString() + "Enter value:";
    }

    @Override
    protected String command(String request) {
        return this.execute(request);
    }

    @Override
    protected String execute(String request) {
        try{

            int selected = Integer.parseInt(request);
            int index = selected - 1;
            Storage.getBasket().remove(index);

        }catch(NumberFormatException ex){
            return "Not a number";
        }catch(IndexOutOfBoundsException ex){
            super.finish();
            return "Not correct number.\n";
        }
        super.finish();
        return "The item was removed.\n";
    }
}
