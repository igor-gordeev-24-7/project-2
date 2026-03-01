package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{
    private final List<Integer> subtasksId = new ArrayList<Integer>();

    public Epic(String title, String description) {
        super(title, description);
    }

    public List<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void addSubtaskId(int subtaskId) {
        subtasksId.add(subtaskId);
    }

    public void deleteSubtaskId(int subtaskId) {
        subtasksId.remove(subtaskId);
    }

    public void deleteAllSubtasks() {
        subtasksId.clear();
    }

    @Override
    public String toString() {
        return "Epic id - " + id + "\n" +
                "subtasksId - " + getSubtasksId() + "\n" +
                "title - " + title + "\n" +
                "description - " + description + "\n" +
                "status - " + status;
    }
}
