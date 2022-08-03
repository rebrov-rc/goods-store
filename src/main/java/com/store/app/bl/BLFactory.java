package com.store.app.bl;

public class BLFactory {
    public static Connectable getBL(){
        return new BusinessLogicalImpl();
    }
}
