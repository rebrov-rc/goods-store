package com.store.app.bl.handlers.basket;

import com.store.app.bl.Connectable;
import com.store.app.bl.Response;
import com.store.app.bl.storage.db.Storage;

class ClrGoodsHandler implements Connectable {
    @Override
    public Response connect(String request) {
        Storage.getBasket().clear();
        return new Response(false, "Your basket was cleaned.\n");
    }
}
