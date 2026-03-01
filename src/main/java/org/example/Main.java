package org.example;

import org.example.model.Epic;
import org.example.model.Status;
import org.example.model.Subtask;
import org.example.model.Task;
import org.example.service.TaskManagerImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TaskManagerImpl manager1 = new TaskManagerImpl();

        Task task1 = new Task("Задача-1", "Описание задачи 1");
        Task task2 = new Task("Задача-2", "Описание задачи 2");
        Task task3 = new Task("Задача-3", "Описание задачи 3");
        Task task4 = new Task("Задача-4", "Описание задачи 4");
        Task task5 = new Task("Задача-5", "Описание задачи 5");
        Task task6 = new Task("Задача-6", "Описание задачи 6");
        Task task7 = new Task("Задача-7", "Описание задачи 7");
        Task task8 = new Task("Задача-8", "Описание задачи 8");
        Task task9 = new Task("Задача-9", "Описание задачи 9");
        Task task10 = new Task("Задача-10", "Описание задачи 10");
        Task task11 = new Task("Задача-11", "Описание задачи 11");
        Task task12 = new Task("Задача-12", "Описание задачи 12");

        manager1.addTask(task1);
        manager1.addTask(task2);
        manager1.addTask(task3);
        manager1.addTask(task4);
        manager1.addTask(task5);
        manager1.addTask(task6);
        manager1.addTask(task7);
        manager1.addTask(task8);
        manager1.addTask(task9);
        manager1.addTask(task10);
        manager1.addTask(task11);
        manager1.addTask(task12);
//        System.out.println(manager1.getTasks());

        manager1.getTaskById(1);
        manager1.getTaskById(2);
        manager1.getTaskById(3);
        manager1.getTaskById(4);
        manager1.getTaskById(5);
        manager1.getTaskById(6);
        manager1.getTaskById(7);
        manager1.getTaskById(8);
        manager1.getTaskById(9);
        manager1.getTaskById(11);
        manager1.getTaskById(12);
//        manager1.getHistoryAsString();


        Epic epic1 = new Epic("Epic-1", "Описание Epic 1");
        manager1.addEpic(epic1);

        Subtask subtask1 = new Subtask("ПодЗадача-1", "Описание ПодЗадачи 1");
        Subtask subtask2 = new Subtask("ПодЗадача-1", "Описание ПодЗадачи 2");

//        System.out.println(manager1.getEpic());

        manager1.addSubtask(subtask1, 1);
        manager1.addSubtask(subtask2, 1);
//        System.out.println(epic1.toString());

        subtask1.setStatus(Status.DONE);
        manager1.editTask(subtask1);
        System.out.println(manager1.getSubtaskById(1).toString());

        subtask2.setStatus(Status.DONE);
        manager1.editTask(subtask2);
        System.out.println(manager1.getSubtaskById(2).toString());

        System.out.println("---------------");
        System.out.println("Вывод в финале");
        System.out.println(epic1.toString());
        System.out.println("---------------");

    }
}