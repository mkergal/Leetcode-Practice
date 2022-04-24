package com.mitaTestApp.priorityqueueproblems;

import com.mitaTestApp.domain.Pair;
import com.mitaTestApp.domain.Person;

import java.util.*;

public class PriorityQsample {

    public static void main(String[] args) {
        PriorityQueue<Person> pq = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        PriorityQueue<Person> pq2 = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - 5 > -o2.getAge() -5 ? -1 : 1;
            }
        });
        PriorityQueue<Person> pq1 = new PriorityQueue<>(((o1, o2) -> o1.getAge() - o2.getAge()));
       // Queue<Integer> heap = new PriorityQueue<>((o1, o2) -> Math.abs(o1 - target) > Math.abs(o2 - target) ? -1 : 1);

        //int[] arr =new int[]{4,4,4,5,5,6,2,3};
       // minHeap(arr);
       // maxHeap(arr);
        //arrangeStudentsByName();
       // topKFrequent(arr,2);
        frequencySort("tree");
    }

    // PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
    // PriorityQueue<Integer> pq =new PriorityQueue<>((a,b)-> b-a);
    //PriorityQueue<Pair<Integer,Integer> > pq=
    //                    new PriorityQueue<Pair<Integer,Integer>>(n,(a,b) -> a.getKey() - b.getKey());
    //PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {
    //    @Override
    //    public int compare(Integer a, Integer b) {
    //        return b - a;
    //    }
    //});

    public static void minHeap(int[] arr){
        PriorityQueue<Integer> pq =new PriorityQueue<>();
        if(arr.length !=0) {
            for (int i = 0; i < arr.length; i++) {
                pq.add(arr[i]);
            }

            System.out.println("-------Min Heap-----------");
            while(!pq.isEmpty()){
                System.out.println(pq.poll());
            }
         }
    }

    public static void maxHeap(int[] arr){
        PriorityQueue<Integer> pq =new PriorityQueue<>((a,b)-> b-a);
        if(arr.length !=0) {
            for (int i = 0; i < arr.length; i++) {
                pq.add(arr[i]);
            }

            System.out.println("-------Max Heap-----------");
            while(!pq.isEmpty()){
                System.out.println(pq.poll());
            }
        }
    }

    public  static void arrangeStudentsAsc(){
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b)->a.getAge()-b.getAge());
        pq.add(new Pair(12,30));
        pq.add(new Pair(1,34));
        pq.add(new Pair(23,100));


        System.out.println("-------Min Heap-----------");
        while(!pq.isEmpty()){
            System.out.println(pq.poll().getAge());
        }
    }

    public static void arrangeStudentsDsc(){
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b)->a.getRoll()-b.getRoll());
        pq.add(new Pair(12,30));
        pq.add(new Pair(1,34));
        pq.add(new Pair(23,100));


        System.out.println("-------Min Heap-----------");
        while(!pq.isEmpty()){
            System.out.println(pq.poll().getRoll());
        }
    }

    public  static void arrangeStudentsByAge(){
        PriorityQueue<Person> pq = new PriorityQueue<>((a,b)->a.getAge() - b.getAge());

        List<Person> list =new ArrayList<>();
        list.add(new Person("A",45));
        list.add(new Person("z",67));
        list.add(new Person("E",34));


        for(int i =0;i<list.size();i++){
            pq.add(new Person(list.get(i).getName(), list.get(i).getAge()));
        }

        System.out.println("-------Min Heap-----------");
        while(!pq.isEmpty()){
            System.out.println(pq.poll().getName());
        }
    }

    public  static void arrangeStudentsByName (){
       // Comparator<Person> nameSorter = Comparator.comparing(Person::` `);

        //PriorityQueue<Employee> priorityQueue = new PriorityQueue<>( nameSorter );
        PriorityQueue<Person> pq = new PriorityQueue<>((a,b) -> b.getName().compareTo(a.getName()));

        List<Person> list =new ArrayList<>();
        list.add(new Person("Bonga",45));
        list.add(new Person("Aarha",67));
        list.add(new Person("Edie",34));


        for(int i =0;i<list.size();i++){
            pq.add(new Person(list.get(i).getName(), list.get(i).getAge()));
        }

        System.out.println("-------Min Heap-----------");
        while(!pq.isEmpty()){
            System.out.println(pq.poll().getName());
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        if(nums.length ==0 || k<=0)
            return null;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i])+1);
            else
                map.put(nums[i],1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
        //PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> b.getAge()-a.getAge());

        for (Map.Entry<Integer,Integer> entry: map.entrySet()) {
            pq.add(entry);
            //pq.add(new Pair(entry.getKey(),entry.getValue()));
        }
        int[] output =new int[k];
        for(int i =0;i<k;i++){
            output[i] = pq.poll().getKey();
        }

        System.out.println(Arrays.toString(output));
        return output;
    }

    //451. Sort Characters By Frequency
    public static String frequencySort(String s) {

        if(s.length() ==0)
            return null;

        Map<Character,Integer> map = new HashMap<Character,Integer>();

        for(int i=0;i<s.length();i++)
        {
            Character ch  = s.charAt(i);
            if(map.containsKey(ch))
                map.put(ch, map.get(ch)+1);
            else
                map.put(ch,1);
        }

        PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a,b)-> b.getValue()-a.getValue());

        for(Map.Entry<Character,Integer> entry: map.entrySet())
            pq.add(entry);

        StringBuilder str = new StringBuilder();
        while(!pq.isEmpty())
        {
            int val = pq.peek().getValue();
            Character key  = pq.poll().getKey();
            if(val>1){
                while(val!=0)
                {
                    str = str.append(key);
                    val--;
                }
            }
            else{
                str =str.append(key);
            }
        }

        return str.toString();

    }

}
