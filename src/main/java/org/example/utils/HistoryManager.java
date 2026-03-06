package org.example.utils;

import org.example.model.Task;

import java.util.ArrayList;
import java.util.List;

public enum HistoryManager {
    HISTORY;
    private List<Task> historyOfTasksRequest = new ArrayList<>();

    public List<Task> getHistory() {
        return historyOfTasksRequest;
    }

    public void getHistoryAsString() {
        System.out.println("--------");
        System.out.println("История поиска tasks: ");
        for (Task task : historyOfTasksRequest) {
            System.out.println("Id таска - " + task.getId());
        }
        System.out.println("--------");
    }

    public void deleteFromHistory(Task task) {
        if (historyOfTasksRequest.contains(task)) {
            historyOfTasksRequest.remove(task);
        }
    }

    public void addToHistory(Task task) {
        historyOfTasksRequest.add(task);

        if (historyOfTasksRequest.size() > 10) {
            historyOfTasksRequest.remove(0);
        }
    }

}
