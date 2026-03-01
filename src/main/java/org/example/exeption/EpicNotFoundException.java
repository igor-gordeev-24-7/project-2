package org.example.exeption;

public class EpicNotFoundException extends RuntimeException {
    public EpicNotFoundException(String message) {
        super(message);
    }

    public EpicNotFoundException(int id) {
        super("Epic с id " + id + " не найден");
    }
}
