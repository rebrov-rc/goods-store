package com.store.app.bl.handlers.data;

import com.store.app.bl.Connectable;
import com.store.app.bl.Response;

import java.util.function.Supplier;

public class FunctionSupplier implements Connectable {

    private final Supplier<String> supplier;

    public FunctionSupplier(Supplier<String> supplier){
        this.supplier = supplier;
    }

    @Override
    public Response connect(String request) {
        return new Response(false, this.supplier.get());
    }
}
