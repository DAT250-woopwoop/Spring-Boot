package no.hvl.dat250.feedApp.entity;

import java.util.function.*;

public abstract class Updatable {

    public <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
