package com.ianxc.core;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Intended for static import
 */
public class CoreUtil {

    public static void println() {
        System.out.println();
    }

    public static void println(boolean x) {
        System.out.println(x);
    }

    public static void println(char x) {
        System.out.println(x);
    }

    public static void println(int x) {
        System.out.println(x);
    }

    public static void println(long x) {
        System.out.println(x);
    }

    public static void println(float x) {
        System.out.println(x);
    }

    public static void println(double x) {
        System.out.println(x);
    }

    public static void println(@NotNull char[] xs) {
        System.out.println(xs);
    }

    public static void println(@Nullable String x) {
        System.out.println(x);
    }

    public static void println(@Nullable Object x) {
        System.out.println(x);
    }

    public static void require(boolean p, Supplier<String> failMessageSupplier) {
        if (!p) {
            throw new IllegalArgumentException(failMessageSupplier.get());
        }
    }

    public static void check(boolean p, Supplier<String> failMessageSupplier) {
        if (!p) {
            throw new IllegalStateException(failMessageSupplier.get());
        }
    }

    public static final boolean DEBUG = true;

    public static void dbg(Runnable r) {
        if (DEBUG) {
            r.run();
        }
    }

    public static void dbg(boolean on, Runnable r) {
        if (on) {
            r.run();
        }
    }

    public static boolean isLastIndex(@NotNull Object[] xs, int index) {
        return index == xs.length - 1;
    }

    public static boolean isLastIndex(@NotNull List<Object> xs, int index) {
        return index == xs.size() - 1;
    }
}
