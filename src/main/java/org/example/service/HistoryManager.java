package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface HistoryManager {
//    void deleteFromHistory(Task task);
//    List<Task> getHistory();
//    String getHistoryAsString();

    void add(Task task);
    void remove(int id);
    List<Task> getHistory();
}
