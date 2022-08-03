package com.store.app.bl.handlers.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class MarkerImpl<E> implements Marker {


    private final List<E> list;
    private StringBuilder builder = null;

    public MarkerImpl(List<E> list) {
        this.list = list;
    }


    @Override
    public List<E> getMarkedList(Predicate predicate) {

        this.builder = new StringBuilder();
        List<E> filtered = new ArrayList<>();

        int counter = 0;

        for (E item : list) {
            if (predicate.test(item)) {
                filtered.add(item);
                builder.append(++counter + ") " + item.toString() + "\n");
            }
        }
        if (filtered.size() == 0) {
            this.builder.append("Пока пусто!\n");
        }
        return filtered;
    }

    @Override
    public String toString() {
        return  builder.toString();
    }
}