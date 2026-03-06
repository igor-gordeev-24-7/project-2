package org.example.service;

public class Managers {

    public Managers() {
    }

    public static TaskManager getDefault() {
        return new TaskManagerImpl();
    }
}
