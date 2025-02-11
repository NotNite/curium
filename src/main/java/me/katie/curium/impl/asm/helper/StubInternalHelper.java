package me.katie.curium.impl.asm.helper;

import me.katie.curium.impl.asm.ASMUtil;
import me.katie.curium.impl.asm.annotations.StubClass;

import java.lang.invoke.MethodType;

/**
 * Internal helper to generate throws for {@link StubClass} in throwing mode.
 */
public class StubInternalHelper {
    private static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    private StubInternalHelper() {
        throw new UnsupportedOperationException("Cannot instantiate StubInternalHelper");
    }

    public static void stubbed0() {
        // Get the calling method.
        String calledMethod = STACK_WALKER.walk(s ->
                s.skip(2)
                        .findFirst()
                        .map(f -> {
                            MethodType type = f.getMethodType();
                            String desc = ASMUtil.methodTypeToAsmType(type).getDescriptor();

                            return String.format("%s;%s%s", f.getClassName(), f.getMethodName(), desc);
                        })
                        .orElseThrow()
        );

        throw new IllegalStateException("call against a stubbed out method: " + calledMethod);
    }

    public static void stubbed() {
        stubbed0();
        throw new AssertionError();
    }
}
