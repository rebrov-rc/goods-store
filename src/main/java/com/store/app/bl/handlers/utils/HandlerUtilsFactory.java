package com.store.app.bl.handlers.utils;

import java.util.List;

public class HandlerUtilsFactory {

    public static <E> Marker getMarkerImpl(List<E> list){
        return new MarkerImpl<E>(list);
    }
}
