package org.example.service;

import org.example.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private static InMemoryHistoryManager instance;

    private Node head;
    private Node tail;

    Map<Integer, Node> index = new HashMap<>();

    private static class  Node{
        Task data;
        Node prev;
        Node next;

        Node(Task data){
            this.data = data;
        }
    }

    public InMemoryHistoryManager() {
    }

    public static InMemoryHistoryManager getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new InMemoryHistoryManager();
            return instance;
        }
    }

    private void linkLast(Task task){
        Node node = new Node(task);
        if (tail == null) {
            head = node;
        } else {
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
        index.put(task.getId(), node);
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        Node node = index.remove(task.getId());
        if (node != null) {
            removeNode(node);
            System.out.println(node);
            linkLast(node.data);
        }
        linkLast(task);
    }

    @Override
    public void remove(Task task) {
        Node node = index.remove(task.getId());
        if (node != null) {
            index.remove(task.getId());
            removeNode(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> tasksList = new ArrayList<>();
        Node currentNode = head;
        while (currentNode != null) {
            tasksList.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return tasksList;
    }
}
