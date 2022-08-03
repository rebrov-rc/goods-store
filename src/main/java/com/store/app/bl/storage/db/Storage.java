package com.store.app.bl.storage.db;

import com.store.app.bl.handlers.utils.HandlerUtilsFactory;
import com.store.app.bl.handlers.utils.Marker;
import com.store.app.bl.storage.db.goods.Goods;
import com.store.app.bl.storage.db.goods.GoodsBuilder;

import java.util.List;
import java.util.function.Predicate;


public class Storage {

    private static List<Goods> goodsSet = null;

    private Storage(){}

    public static void init(){
        updateGoods();
    }

    public static List<Goods> getGoodsSet(){
        if (goodsSet == null) {
            updateGoods();
        }
        return goodsSet;
    }

    public static void updateGoods(){
        goodsSet = GoodsBuilder.createGoods(20);
    }

    public static Goods getItem(int selected) {
        return goodsSet.get(selected - 1);
    }


    public static String getMarkedList(Predicate<Goods> predicate) {
        Marker marker = HandlerUtilsFactory.getMarkerImpl(goodsSet);
        goodsSet = marker.getMarkedList(predicate);
        return marker.toString();

    }

}