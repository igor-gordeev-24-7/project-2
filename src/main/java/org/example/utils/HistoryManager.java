package org.example.utils;

import org.example.model.Task;

import java.util.*;

public enum HistoryManager {
    HISTORY;
    private LinkedList<Task> historyList = new LinkedList<>();
    private Map<Integer, Task> historyMap = new HashMap<>();

    public List<Task> getHistory() {
        return new ArrayList<>(historyList);
    }

    public void remove(Task task) {
        if (historyMap.containsKey(task.getId())) {
            historyMap.remove(task.getId());
            historyList.remove(task);
        }
    }

    public void add(Task task) {
        if (task == null) {
            return;
        }

        remove(task);

        historyMap.put(task.getId(), task);
        historyList.addLast(task);
    }

}
