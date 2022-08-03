package com.store.app.bl.storage.db.goods;

public class Goods {

    private final long id;
    private final String name;
    private int cost;
    private int quantity;
    private String description;
    private String factory;

    public Goods(long id, String name, int cost, int quantity, String desc, String factory){
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.description = desc;
        this.factory = factory;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getFactory() {
        return factory;
    }
    public String getDescription() {
        return description;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "name= " + name  +
                ", cost= " + cost +
                ", quantity= " + quantity + "." +
                ", factory= " + factory;
    }
}
