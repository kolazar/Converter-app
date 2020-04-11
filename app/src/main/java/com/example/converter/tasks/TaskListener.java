package com.example.converter.tasks;

public interface TaskListener<T> {
    void onSuccess(T result);
    void onError (Throwable error);
}
