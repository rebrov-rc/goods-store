package com.store.app.bl.handlers;

import com.store.app.bl.Connectable;
import com.store.app.bl.handlers.basket.BasketHandler;
import com.store.app.bl.handlers.data.FunctionSupplier;
import com.store.app.bl.handlers.filters.AvailableGoodsHandler;
import com.store.app.bl.handlers.filters.FilteringGoodsHandler;
import com.store.app.bl.storage.db.Storage;

import java.util.HashMap;
import java.util.Map;

public class HandlerSources {

    private static Map<String, Connectable> src = null;

    private HandlerSources(){}

    private static void init(){
        src = new HashMap<>();
        src.put("avl", new AvailableGoodsHandler());
        src.put("flt", new FilteringGoodsHandler());
        src.put("bsk", new BasketHandler());
        src.put("upd", new FunctionSupplier(() -> {
            Storage.updateGoods();
            return "list was updated\n";
        }));
        src.put("list", new FunctionSupplier(() -> Storage.getMarkedList(x -> true)));

    }

    public static Map<String, Connectable> getSources(){
        if (src == null) {
            init();
        }
        return src;
    }
}
