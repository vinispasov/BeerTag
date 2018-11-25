package com.beertag.utils.validators.base;

public interface Validator<T> {
    boolean isItemValid(T item);
}
