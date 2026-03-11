package org.example.service;

import org.example.model.Epic;
import org.example.model.Subtask;
import org.example.model.Task;

import java.util.List;
import java.util.Map;

public interface TaskManager {

    List<Task> getHistory();
//    task
    List<Task> getTasks();
    void deleteAllTasks();
    Task getTaskById(int id);
    void addTask(Task task);
    void editTask(Task task);
    void deleteTaskById(int id);
//    epic
    List<Epic> getEpic();
    void deleteAllEpic();
    Epic getEpicById(int id);
    void addEpic(Epic epic);
    void editEpic(Epic epic);
    void deleteEpicById(int id);
//    subtask
    List<Subtask> getSubtask();
    void deleteAllSubtask();
    Subtask getSubtaskById(int id);
    Map<Integer, Subtask> getSubtaskByEpicId(int epicId);
    void addSubtask(Subtask subtask, int epicId);
    void editSubtask(Subtask subtask);
    void deleteSubtaskById(int id);
}
