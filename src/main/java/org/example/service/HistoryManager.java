package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface HistoryManager {

    void add(Task task);
    void remove(Task task);
    List<Task> getHistory();
}
