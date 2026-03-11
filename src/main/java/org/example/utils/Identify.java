package org.example.utils;

import org.example.model.Task;

import java.util.HashMap;
import java.util.Map;

public enum Identify {
    INSTANCE;
    private Map<String, Integer> identifier = new HashMap<>();

    public Integer generateTaskId(Task task) {
        String taskClass = task.getClass().getSimpleName();
        if (identifier.containsKey(taskClass)) {
            Integer currentCount = identifier.get(taskClass);
            identifier.put(taskClass, currentCount + 1);
        } else {
            identifier.put(taskClass, 1);
        }
        return identifier.get(taskClass);
    }
}
