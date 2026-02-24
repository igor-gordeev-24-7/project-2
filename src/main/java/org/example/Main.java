package org.example;

import org.example.model.Epic;
import org.example.model.Status;
import org.example.model.Subtask;
import org.example.model.Task;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Задача-1", "Описание задачи 1", Status.NEW);
        Task task2 = new Task("Задача-2", "Описание задачи 2", Status.NEW);

        Epic epic1 = new Epic("Epic-1", "Описание Epic 1");

        Subtask subtask1 = new Subtask("ПодЗадача-1", "Описание ПодЗадачи 1", Status.NEW, 3);
        Subtask subtask2 = new Subtask("ПодЗадача-1", "Описание ПодЗадачи 2", Status.NEW, 3);

        epic1.addSubtaskId(subtask1.getId());
        epic1.addSubtaskId(subtask2.getId());

        Epic epic2 = new Epic("Epic-2", "Описание Epic 2");

        Subtask subtask3 = new Subtask("ПодЗадача-3", "Описание ПодЗадачи 3", Status.NEW, 6);

        epic2.addSubtaskId(subtask3.getId());


        System.out.println(task1.toString());
        System.out.println(task2.toString());

        System.out.println(epic1.toString());
        System.out.println(subtask1.toString());
        System.out.println(subtask2.toString());

        System.out.println(epic2.toString());
        System.out.println(subtask3.toString());
    }
}