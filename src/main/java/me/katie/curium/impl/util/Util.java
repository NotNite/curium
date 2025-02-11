package me.katie.curium.impl.util;

import me.katie.curium.impl.asm.helper.StubInternalHelper;

import java.util.function.Consumer;

public class Util {
    private Util() {
        throw new UnsupportedOperationException("Cannot instantiate Util");
    }

    public static <T> T make(T initial, Consumer<T> consumer) {
        consumer.accept(initial);
        return initial;
    }

    /**
     * Throws a descriptive error about a failed assertion due to a stubbed out method, including the stubbed method in
     * the method name. This uses a stack walker - do not wrap.
     */
    @SuppressWarnings("UnusedReturnValue")
    public static <T> T stubbed() {
        StubInternalHelper.stubbed0();
        throw new AssertionError();
    }
}
