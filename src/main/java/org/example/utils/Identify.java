package org.example.utils;

public enum Identify {
    INSTANCE;
    private int identifierTask = 1;
    private int identifierSubtask = 1;
    private int identifierEpic = 1;

    public int generateTaskId() {
        return identifierTask++;
    }

    public int generateSubtaskId() {
        return identifierSubtask++;
    }

    public int generateEpicId() {
        return identifierEpic++;
    }
//todo
// использовать типа класса и map
}
