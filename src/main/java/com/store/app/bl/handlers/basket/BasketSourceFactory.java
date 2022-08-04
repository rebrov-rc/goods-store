package com.store.app.bl.handlers.basket;

import com.store.app.bl.Connectable;


import java.util.HashMap;
import java.util.Map;

public class BasketSourceFactory {

    private static Map<String, Connectable> src = null;

    private BasketSourceFactory(){}

    private static void init(){
        src = new HashMap<>();
        src.put("add", new AddGoodsHandler());
        src.put("del", new DelGoodsHandler());
        src.put("clr", new ClrGoodsHandler());
        src.put("vw", new ViewGoodsHandler());

    }

    public static Map<String, Connectable> getSources(){
        if (src == null) {
            init();
        }
        return src;
    }
}
