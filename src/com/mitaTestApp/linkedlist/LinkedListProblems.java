package com.mitaTestApp.linkedlist;

import java.util.*;

public class LinkedListProblems {

    //Leetcode 206,141,21,23,19,143,142,24,25,160,234,2,61,203,445,61
   public class ListNode{
       public  int val;
       public  ListNode next;
       public ListNode(){}
       public ListNode(int val){this.val=val;}
       public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode p1 = l1;
        ListNode p2 =l2;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int sum =0;
        int carry =0;
        while(p1!=null || p2!=null){
            int num = (p1!= null) ?p1.val: 0;
            int num2 = (p2!= null) ?p2.val: 0;
            sum  = num+num2+carry;
            carry = sum/10;
            curr.next = new ListNode(sum%10);
            curr=curr.next;
            if(p1!=null)  p1= p1.next;
            if(p2!=null)  p2= p2.next;

        }
        if(carry >0)
            curr.next = new ListNode(carry);

        return dummy.next;

    }

    class AllOne {
    class Block{
        Set<String> set;
        Block next;
        Block prev;
        int occurence;
        Block(int occurence){
            this.occurence = occurence;
            set = new HashSet<>();
        }
    }

    Map<String, Block> map;
    Block head;
    Block tail;
    public AllOne() {
        map = new HashMap<>();
        head = new Block(0);
        tail = new Block(0);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if(!map.containsKey(key)){
            Block first = head.next;
            if(first.occurence == 1){
                map.put(key, first);
                first.set.add(key);
            } else {
                // put b between head and first
                Block b = new Block(1);
                b.set.add(key);
                map.put(key, b);
                head.next = b;
                b.next = first;
                first.prev = b;
                b.prev = head;
            }
        } else {
            Block cur = map.get(key);
            Block prev = cur.prev;
            Block next = cur.next;
            int newOccurrence = cur.occurence + 1;
            cur.set.remove(key);
            // if not removed, prev -> cur -> b -> next
            // if the block becomes empty: prev -> b -> next
            if(cur.set.isEmpty()){
                prev.next = next;
                next.prev = prev;
                cur = prev;
            }

            if(next.occurence == newOccurrence){
                next.set.add(key);
                map.put(key, next);
            } else {
                Block b = new Block(newOccurrence);
                b.set.add(key);
                // add a new block between cur and next
                map.put(key, b);
                cur.next = b;
                b.next = next;
                next.prev = b;
                b.prev = cur;
            }
        }
    }

    public void dec(String key) {
        Block cur = map.get(key);
        int newOccurrence = cur.occurence - 1;
        Block prev = cur.prev;
        Block next = cur.next;
        cur.set.remove(key);
        // if not removed, prev -> b -> cur -> next
        // if the block becomes empty: prev -> b -> next
        if(cur.set.isEmpty()){
            prev.next = next;
            next.prev = prev;
            cur = next;
        }
        if(newOccurrence == 0){
            map.remove(key);
            return;
        }

        if(prev.occurence == newOccurrence){
            prev.set.add(key);
            // update map pointer
            map.put(key, prev);
        } else {
            Block b = new Block(newOccurrence);
            b.set.add(key);
            map.put(key, b);
            prev.next = b;
            b.prev = prev;
            cur.prev = b;
            b.next = cur;
        }
    }

    public String getMaxKey() {
        if(tail.prev == head){
            return "";
        }
        Block last = tail.prev;
        return last.set.iterator().next();
    }

    public String getMinKey() {
        if(head.next == tail){
            return "";
        }
        Block first = head.next;
        return first.set.iterator().next();
    }
}

    //Leetcode 25 - reverse k groups
    public ListNode ReverseKGroup(ListNode head, int k) {
       ListNode dummy = new ListNode(-1);
       Stack<Integer> stack = new Stack<>();
       ListNode ptr = head;
        int i =0;
       while(ptr!=null){
           ListNode temp = ptr;
           for(;i<k &&temp!= null;i++){
               stack.push(temp.val);
               temp = temp.next;
           }
           if(i!=k) break;
           while (stack.size() > 0)
           {
               ptr.val = stack.pop();
               ptr = ptr.next;
           }
       }
        return head;
       }


}




