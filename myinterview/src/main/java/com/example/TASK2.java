package com.example;

/**
 * Task here is to write a list. Each element must know the element before and
 * after it. Print out your list and them remove the element in the middle of
 * the list. Print out again.
 */
public class TASK2 {

    private static Node head;
    private static Node tail;

    public static void exeTask2(){
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);

        System.out.print("Original list: ");
        printList();

        removeMiddleElement();

        System.out.print("List after removing middle element: ");
        printList();
    }

    public static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public static void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void removeMiddleElement() {
        if (head == null || head == tail) {
            return; // Nothing to remove for empty or single element list
        }

        Node middleNode = findMiddleNode();
        middleNode.prev.next = middleNode.next;
        middleNode.next.prev = middleNode.prev;
    }

    private static Node findMiddleNode() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}