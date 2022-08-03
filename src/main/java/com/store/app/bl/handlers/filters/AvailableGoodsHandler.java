package com.store.app.bl.handlers.filters;


import com.store.app.bl.Connectable;
import com.store.app.bl.Response;
import com.store.app.bl.storage.db.Storage;
import com.store.app.bl.storage.db.goods.Goods;

public class AvailableGoodsHandler implements Connectable {

    private enum Steps{DEFAULT}
    private Steps mode = Steps.DEFAULT; // default

    @Override
    public Response connect(String request) {

        if (mode == Steps.DEFAULT) {
            String res = Storage.getMarkedList((Goods x) -> x.getQuantity() > 0);
            return new Response(false, res);
        }

        return new Response(false, "");
    }
}
