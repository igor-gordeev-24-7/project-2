package org.example.service;

import org.example.exeption.EpicNotFoundException;
import org.example.exeption.TaskNotFoundException;
import org.example.model.Epic;
import org.example.model.Status;
import org.example.model.Subtask;
import org.example.model.Task;
import org.example.utils.Identify;
import org.example.utils.SearchHistory;

import java.sql.SQLOutput;
import java.util.*;

public class TaskManagerImpl implements TaskManager {
    private List<Task> historyOfTasksRequest = new ArrayList<>();
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();




    //    Task
    @Override
    public List<Task> getTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список tasks пустой");
            return Collections.emptyList();
        }
        System.out.println("Получен список задач. Количество: " + tasks.size());
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        SearchHistory.HISTORY.addToHistory(task);
        return task;
    }

    @Override
    public void addTask(Task task) {
        task.setId(Identify.INSTANCE.generateTaskId());
        task.setStatus(Status.NEW);
        tasks.put(task.getId(), task);
    }

    @Override
    public void editTask(Task task) {
        if(tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

//    Epic
    public void updateStatusEpic(int epicId) {
        Epic epic = epics.get(epicId);

        if (epic == null) {
            throw new EpicNotFoundException(epicId);
        }

        List<Integer> epicSubtaskIds = epic.getSubtasksId();

        if (epicSubtaskIds.isEmpty()) {
            epic.setStatus(Status.NEW);
            return;
        }

        boolean allNew = true;
        boolean allDone = true;

        for (Integer epicSubtaskId : epicSubtaskIds) {
            Status status = subtasks.get(epicSubtaskId).getStatus();

            if (status != Status.NEW) {
                allNew = false;
            }
            if (status != Status.DONE) {
                allDone = false;
            }
        }
        if (allDone) {
            epic.setStatus(Status.DONE);
        } else if (allNew) {
            epic.setStatus(Status.NEW);
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
        epic.setId(Identify.INSTANCE.generateEpicId());
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void editEpic(Epic epic) {
        tasks.put(epic.getId(), epic);
    }

    @Override
    public void deleteEpicById(int id) {
        Epic epic = epics.remove(id);
        if (epic != null) {
            List<Integer> subtasksForDel = epic.getSubtasksId();
            for (Integer subtaskId : subtasksForDel) {
                subtasks.remove(subtaskId);
            }
        }
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
    public void addSubtask(Subtask subtask, int epicId) {
        subtask.setId(Identify.INSTANCE.generateSubtaskId());
        subtask.setStatus(Status.NEW);
        if (epics.containsKey(epicId)) {
            subtask.setEpicId(epicId);
            subtasks.put(subtask.getId(), subtask);
            getEpicById(epicId).addSubtaskId(subtask.getId());
            updateStatusEpic(epicId);
        } else {
            throw new EpicNotFoundException(epicId);
        }

    }

    @Override
    public void editSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Subtask editindSubtask = subtasks.get(subtask.getId());
        if (editindSubtask == null) {
            throw new TaskNotFoundException(subtask.getId());
        }

        Epic epic = epics.get(epicId);
        if (epic == null) {
            throw new EpicNotFoundException(epicId);
        }

        subtask.setEpicId(epicId);
        subtasks.put(subtask.getId(), subtask);

        updateStatusEpic(epicId);
    }

    @Override
    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = getEpicById(subtask.getEpicId());
            if (epic != null) {
                epic.deleteSubtaskId(id);
                updateStatusEpic(epic.getId());
            }
        }
    }
}
