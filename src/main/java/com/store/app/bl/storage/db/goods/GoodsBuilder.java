package com.store.app.bl.storage.db.goods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoodsBuilder {

    private GoodsBuilder(){}


    public static List<Goods> createGoods(int quantity){
        List<Goods> goodsList = new ArrayList<>();
        long goodsIdCreator = 0;
        int counter = 0, limit = 100;
        var random = new Random();
        int minCost = 340, maxCost = 350;
        int maxQuantity = 3;

        while(counter < quantity && counter <= limit){
            var item = new Goods( ++goodsIdCreator, "item" + goodsIdCreator,
                    random.nextInt(maxCost - minCost) + minCost,
                    random.nextInt(maxQuantity), "item" + goodsIdCreator,
                   (random.nextInt(2)%2 == 0)? "Ru" : "Com");
            goodsList.add(item);
            ++counter;
        }

        return goodsList;
    }

}
