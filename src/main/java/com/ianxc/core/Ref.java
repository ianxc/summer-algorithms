package com.ianxc.core;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

public class Ref<T> implements Supplier<T> {

    @NotNull
    public T data;

    public Ref(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Ref [data=" + data + "]";
    }

    @Override
    public T get() {
        return this.data;
    }
}
