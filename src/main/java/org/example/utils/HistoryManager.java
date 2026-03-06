package org.example.utils;

import org.example.exeption.ItemNotFoundException;
import org.example.model.Task;

import java.util.*;

public enum HistoryManager {
    HISTORY;
    private LinkedList<Task> historyList = new LinkedList<>();
    private Map<Integer, Task> historyMap = new HashMap<>();

    public List<Task> getHistory() {
        return new ArrayList<>(historyList);
    }

    public String getHistoryAsString() {
        if (historyList.isEmpty()) {
            return "История пуста";
        }

        StringBuilder sb = new StringBuilder("История просмотров:\n");
        int index = 1;
        for (Task task : historyList) {
            sb.append(index++)
                    .append(". ")
                    .append(task.toString())
                    .append("\n");
        }
        return sb.toString();
    }

    public void remove(Task task) {
        if (task == null) {
            throw new ItemNotFoundException("Не может быть равен null");
        }

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
