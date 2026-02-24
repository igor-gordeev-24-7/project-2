package org.example.service;

import org.example.model.Epic;
import org.example.model.Status;
import org.example.model.Subtask;
import org.example.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManagerImpl implements TaskManager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();

//    Task
    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    @Override
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void editTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

//    Epic
    public void statusEpicCheck(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }

        List<Subtask> epicSubtasks = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == epicId) {
                epicSubtasks.add(subtask);
            }
        }

        if (epicSubtasks.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subtask : epicSubtasks) {
            Status status = subtask.getStatus();

            if (status != Status.NEW) {
                allNew = false;
            }
            if (status != Status.DONE) {
                allDone = false;
            }
        }

        if (allNew) {
            epic.setStatus(Status.NEW);
        } else if (allDone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }

    @Override
    public List<Epic> getEpic() {
        return new ArrayList<>(epics.values());

    }

    @Override
    public void deleteAllEpic() {
        epics.clear();
    }

    @Override
    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    @Override
    public void addEpic(Epic epic) {
        tasks.put(epic.getId(), epic);
    }

    @Override
    public void editEpic(Epic epic) {
        tasks.put(epic.getId(), epic);
    }

    @Override
    public void deleteEpicById(int id) {
        epics.remove(id);

    }

//    Subtask
    @Override
    public List<Subtask> getSubtask() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public void deleteAllSubtask() {
        subtasks.clear();
    }

    @Override
    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        int id = subtask.getId();
        subtasks.put(id, subtask);
        statusEpicCheck(id);
    }

    @Override
    public void editSubtask(Subtask subtask) {
        int id = subtask.getId();
        subtasks.put(id, subtask);
        statusEpicCheck(id);
    }

    @Override
    public void deleteSubtaskById(int id) {
        subtasks.remove(id);
    }
}
