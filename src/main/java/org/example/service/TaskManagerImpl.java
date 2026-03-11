package org.example.service;

import org.example.exeption.EpicNotFoundException;
import org.example.exeption.ItemNotFoundException;
import org.example.exeption.TaskNotFoundException;
import org.example.model.Epic;
import org.example.model.Status;
import org.example.model.Subtask;
import org.example.model.Task;
import org.example.utils.Identify;

import java.util.*;

public class TaskManagerImpl implements TaskManager {
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
        HistoryManager.HISTORY.add(task);
        return task;
    }

    @Override
    public void addTask(Task task) {
        task.setId(Identify.INSTANCE.generateTaskId(task));
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
            if (epicSubtaskIds.isEmpty()) {
                System.out.println("Subtasks пустой");
                return;
            } else {
                Status status = subtasks.get(epicSubtaskId).getStatus();
                if (status != Status.NEW) {
                    allNew = false;
                }
                if (status != Status.DONE) {
                    allDone = false;
                }
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
        HistoryManager.HISTORY.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(Identify.INSTANCE.generateTaskId(epic));
        epic.setStatus(Status.NEW);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void editEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
        } else {
            System.out.println("Такой epic уже добавлен");
        }
    }

    @Override
    public void deleteEpicById(int id) {
        Epic epic = epics.get(id);
        System.out.println("Получен epic " + epic + "\n");
        if (epic != null) {
            List<Integer> subtasksForDel = new ArrayList<>(epic.getSubtasksId());
            for (Integer subtaskId : subtasksForDel) {
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
            HistoryManager.HISTORY.remove(epic);
            System.out.println("Удален epic " + epic);
        }
    }

//    Subtask
    @Override
    public List<Subtask> getSubtask() {
        List<Subtask> subtasksFind = new ArrayList<>(subtasks.values());

        if (subtasksFind.isEmpty()) {
            System.out.println("Список Subtask пуст");
//            throw new RuntimeException("Список Subtask пуст");
        } else {
            System.out.println("---------");
            System.out.println("Найдено подзадач: " + subtasksFind.size());
            System.out.println("---------");
        }

        return subtasksFind;
    }

    @Override
    public void deleteAllSubtask() {
        System.out.println("Все subtask удалены");
        subtasks.clear();
    }

    @Override
    public Subtask getSubtaskById(int id) {
        HistoryManager.HISTORY.add(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public Map<Integer, Subtask> getSubtaskByEpicId(int epicId) {
        return Map.of();
    }

    @Override
    public void addSubtask(Subtask subtask, int epicId) {
        subtask.setId(Identify.INSTANCE.generateTaskId(subtask));
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
        Subtask subtaskForDel = subtasks.get(id);
        if (subtaskForDel != null) {
            System.out.println("____-_______");
            System.out.println(subtaskForDel);
            System.out.println("____-_______");

            Epic epic = getEpicById(subtaskForDel.getEpicId());

            epic.deleteSubtaskId(id);

            HistoryManager.HISTORY.remove(subtaskForDel);

            updateStatusEpic(epic.getId());
            subtasks.remove(id);

        } else {
            throw new ItemNotFoundException("Subtask с таким id не найден");
        }


    }

}
