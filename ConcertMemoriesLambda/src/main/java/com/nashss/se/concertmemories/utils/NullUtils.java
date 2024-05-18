package com.nashss.se.concertmemories.utils;

import java.util.function.Supplier;

public class NullUtils {
    private NullUtils() { }

    public static <T> T ifNull(T obj, T valIfNull) {
        return obj != null ? obj : valIfNull;
    }

    public static <T> T ifNull(T obj, Supplier<T> valIfNullSupplier) {
        return obj != null ? obj : valIfNullSupplier.get();
    }

    public static <T> T ifNotNull(T obj, T valIfNotNull) {
        return obj == null ? null : valIfNotNull;
    }

    public static <T, U> U ifNotNull(T obj, Supplier<U> valIfNotNullSupplier) {
        return obj == null ? null : valIfNotNullSupplier.get();
    }
}