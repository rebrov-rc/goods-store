package com.store.app.bl.handlers.utils;


import java.util.List;
import java.util.function.Predicate;

public interface Marker<E> {
    List<E> getMarkedList(Predicate predicate);

}
