package org.example.exeption;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(int id) {
        super("Задача с ID " + id + " не найдена");
    }
}
