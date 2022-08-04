package com.store.app.bl.handlers.basket;

import com.store.app.bl.Connectable;
import com.store.app.bl.Response;
import com.store.app.bl.handlers.utils.HandlerUtilsFactory;
import com.store.app.bl.handlers.utils.Marker;
import com.store.app.bl.storage.db.Storage;

class ViewGoodsHandler implements Connectable {
    @Override
    public Response connect(String request) {
        Marker marker = HandlerUtilsFactory.getMarkerImpl(Storage.getBasket());
        marker.getMarkedList(x -> true);
        return new Response(false, marker.toString());
    }
}
