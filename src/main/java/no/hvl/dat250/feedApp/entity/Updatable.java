package no.hvl.dat250.feedApp.entity;

import java.util.function.*;

public interface Updatable<E> {

    void update(E t);
    default <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
