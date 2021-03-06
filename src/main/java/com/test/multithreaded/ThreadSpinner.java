package com.test.multithreaded;

import static java.util.Objects.requireNonNull;

import java.util.function.Consumer;
import java.util.function.Function;

public class ThreadSpinner<T, R> {
    private Function<T, R> func;
    private Consumer<R> onSuccess;
    private Consumer<Exception> onFailure;

    public ThreadSpinner (Function<T, R> func, Consumer<R> onSuccess, Consumer<Exception> onFailure) {
        this.func = func;
        this.onSuccess = requireNonNull(onSuccess, "onSuccess Consumer must not be null");
        this.onFailure = requireNonNull(onFailure, "onFailure Consumer must not be null");
    }

    public void run(T argument) {
        try {
            R result = func.apply(argument);
            onSuccess.accept(result);
        }
        catch (Exception e) {
            onFailure.accept(e);
        }
    }
}
