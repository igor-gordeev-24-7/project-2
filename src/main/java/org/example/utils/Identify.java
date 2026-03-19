package org.example.utils;

import org.example.model.Task;

import java.util.HashMap;
import java.util.Map;

public enum Identify {
    INSTANCE;

    private int identifier = 0;

//    private Map<Class<?>, Integer> identifier = new HashMap<>();

    public Integer generateTaskId(Task task) {
//        Class<?> taskClass = task.getClass();
//        if (identifier.containsKey(taskClass)) {
//            Integer currentCount = identifier.get(taskClass);
//            identifier.put(taskClass, currentCount + 1);
//        } else {
//            identifier.put(taskClass, 1);
//        }
//        return identifier.get(taskClass);
        return ++identifier;
    }
}
