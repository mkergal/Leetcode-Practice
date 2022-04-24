package com.mitaTestApp.DataStructures;

public class ImpPQ {

    public static void main(String[] args) {

    }

    static class Node{
        int val;
        int priority;
        Node next;
        Node(int val, int priority){
            this.val = val;
            this.priority = priority;
        }
    }
    private static Node head = null;

    private void push(int val, int priority){
        Node newnode = new Node(val, priority);
        if(head == null){
            head = newnode;
            return ;
        }
        Node prev = null;
        Node curr = head;
        while(curr!= null && curr.priority > priority){
            prev = curr;
            curr = curr.next;
        }
        if(curr == null){
            prev.next = newnode;
        }
        else if(prev == null){
            newnode.next = head;
            head = newnode;
        }
        else{
            newnode.next = curr;
            prev.next = newnode;
        }
    }
    private int peek(){
        if(head != null){
            return head.val;
        }
        return -1;
    }

    private int pop(){
        if(head != null){
           int data = head.val;
           head = head.next;
           return data;
        }
        return -1;
    }
}
