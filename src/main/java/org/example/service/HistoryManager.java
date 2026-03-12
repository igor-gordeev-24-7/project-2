package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface HistoryManager {
    void addToHistory(Task task);
    void deleteFromHistory(Task task);
    List<Task> getHistory();
    String getHistoryAsString();
}
