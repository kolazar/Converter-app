package com.example.converter.tasks;

public interface Task<T> {
    void execute(TaskListener<T> listener);

    void cancel();
}
