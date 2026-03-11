package org.example.service;

import org.example.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager {
    private final List<Task> history = new ArrayList<>();

    public void addToHistory(Task task) {
        history.add(task);
    }

    public void deleteFromHistory(Task task) {
        history.remove(task);
    }

    public void getHistory() {
        System.out.println("История поиска: " + "\n");
        for (Task historyItem : history) {
            System.out.println("Тип -" + historyItem.getClass().getSimpleName() + "\n");
            System.out.println("Id - " + historyItem.getId() + "\n");
            System.out.println("Title - " + historyItem.getTitle() + "\n");
        }
    }
}
