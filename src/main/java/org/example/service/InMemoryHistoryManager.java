package org.example.service;

import org.example.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static InMemoryHistoryManager instance;
    private final List<Task> history = new ArrayList<>();

    public InMemoryHistoryManager() {
    }

    public static InMemoryHistoryManager getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new InMemoryHistoryManager();
            return instance;
        }
    }

    public void addToHistory(Task task) {
        history.add(task);
    }

    public void deleteFromHistory(Task task) {
        history.removeIf(t -> t.equals(task));
    }

    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }

    public String getHistoryAsString() {
        if (history.isEmpty()) {
            throw new RuntimeException("История = null");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("История поиска: \n\n");

            for (Task historyItem : history) {
                sb.append("Тип - ").append(historyItem.getClass().getSimpleName()).append("\n");
                sb.append("Id - ").append(historyItem.getId()).append("\n");
                sb.append("Title - ").append(historyItem.getTitle()).append("\n\n");
            }
            return sb.toString();
        }
    }
}
