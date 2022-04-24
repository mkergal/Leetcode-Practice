package com.mitaTestApp;

import com.mitaTestApp.domain.Person;
import com.mitaTestApp.exceptions.AgeException;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public  static void main(String[] args) {
	    // write your code here
        sortArray();
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter your age: ");
//        int age = scan.nextInt();
//        try{
//        validate(age);}
//        catch (Exception e){
//            System.out.println("Problem occurred"+e);
//        }
    }

    public ArrayList<Person> OperationComparator(){
        ArrayList<Person> list = new ArrayList<Person>();

        Person person = new Person("YoYo", 34);

        list.add(person);
        list.add(new Person("asd",74));
        Collections.sort(list, (a,b) -> b.getName().compareTo(a.getName()));
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                // return p1.age+"".compareTo(p2.age+""); //sort by age
                return p1.getName().compareTo(p2.getName()); // if you want to short by name
            }
        });
        return list;
    }

    public static void OperationsToStack() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("count " + stack.size());
        System.out.println("peek " + stack.peek());

        while(!stack.empty()){
            System.out.println("pop " + stack.pop());
        }

    }

    public static void  OperationsToQueue(){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        System.out.println("count " + queue.size());
        System.out.println("Queue peek " + queue.peek());

        while(!queue.isEmpty()){
            System.out.println("queue pop " + queue.poll());
        }

    }

    public static void OperationsToMap() {
        int[] arr = new int[] {1, 2, 2, 4, 5, 5, 6, 7} ;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int item : arr) {
            if (map.containsKey(item)) {
                map.put(item, map.get(item).intValue()+1);
            }
            else {
                map.put(item,1);
                //map.remove(item); Reomve item
            }
        }
      map.forEach((k,v) -> System.out.println("key "+k+ " value "+v ));

    }

    public  static void OperationsToSet(){
        Set<Integer> hashSet = new HashSet<Integer>();
        int[] arr = new int[]{1,2,3,4,5};
        for (int item : arr) {
            if(hashSet.contains(item))
                System.out.println("duplicate item present");
            else {
                hashSet.add(item);
                //hashSet.remove(item);
            }
        }
        hashSet.forEach((k)-> System.out.println(k));
    }

    public  static void OperationsToList(){
        List<Integer> listOutput = new ArrayList<Integer>();
        listOutput.add(1);
        listOutput.add(4);
        listOutput.add(5);
        listOutput.add(3);
        listOutput.add(2);

        System.out.println("Element at Index 2 "+listOutput.get(2));
        Collections.sort(listOutput);

        for(int i =0;i<listOutput.size();i++){
            System.out.println(listOutput.get(i));
        }

        listOutput.remove(3);

        System.out.println("------------------------");
        listOutput.forEach((k)-> System.out.println(k));

        }

    public static void OperationListOfList(){
        List<ArrayList<String>>  lists = new ArrayList<ArrayList<String>>();
        List<List<String>> lists1 = new ArrayList<List<String>>();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Tampa");
        arrayList.add("Florida");
        arrayList.add("California");

        lists.add(arrayList);
        //Asc order
        Collections.sort(arrayList);  // default soty
        System.out.println("----------------Asc---------------");

        lists.forEach((list) -> list.forEach((city)-> System.out.println(city)));

        //Dsc order
        System.out.println("----------------DSc---------------");
        Collections.sort(arrayList,Comparator.reverseOrder());

        lists.forEach((list) -> list.forEach((city)-> System.out.println(city)));

        //lists.stream().map()
    }

    public static void validate(int age) throws AgeException{
        if(age<18){
            throw new AgeException("Invalid age group for registration!");
        }
        else{
            System.out.println("Age is valid!");
        }
    }

    //Lambda
    public static class Staff {

        public String name;
        public int age;
        public Double salary;
        Staff(String name, int age, Double salary){
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
    }
    public static class Staff1 {

        public String name;
        public int age;
        public List<Integer> salary;
        Staff1(String name, int age, List<Integer> salary){
            this.name = name;
            this.age = age;
            this.salary = salary;
        }
    }
    public static void checkmap(){
        List<String> list = Arrays.asList("a","b","c","d");
        List<Integer> listInt = Arrays.asList(1,2,3,4,5);
        List<String> list1 = list.stream().map(String::toUpperCase).collect(Collectors.toList());

        List<Integer> list2 = Arrays.asList(1,2,3,4);
        List<Integer> list3 = list2.stream().map(x-> x * 2).collect(Collectors.toList());

        List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new Double(10000)),
                new Staff("jack", 27, new Double(20000)),
                new Staff("lawrence", 33, new Double(30000))
        );
        List<String> output = staff.stream().map(x->x.name).collect(Collectors.toList());

        List<Staff1> staff1 = Arrays.asList(
                new Staff1("mkyong", 30, Arrays.asList(2000,2000)),
                new Staff1("jac", 27, Arrays.asList(2000,2000)),
                new Staff1("lawrence", 33,Arrays.asList(2000,2000))
        );
        List<String> gamesList = new ArrayList<String>();
        gamesList.add("Football");
        gamesList.add("Cricket");
        gamesList.add("Chess");
        gamesList.add("Hocky");
        System.out.println("------------Iterating by passing method reference---------------");
        gamesList.forEach(System.out::println);


        //MAke a list of objeccts
         // List<Integer> output1 = staff1.stream().flatMap(x -> x.salary.stream()).collect(Collectors.toList());

          List<String> lst = staff1.stream().filter(c -> (c.name.length() % 2 !=0)).map(n -> n.name).collect(Collectors.toList());
          for(String str: lst){
              System.out.println(str);
          }
    }
    //A=[1 ,2,3,4] B=[4,3,2,1]
    public static void create(int[]A, int[]B){
        Map<Integer,List<Integer>> map =new HashMap<>();
        for(int num : A){
            map.put(num, new ArrayList<>());
        }
        for(int i =0;i<A.length;i++){
            map.get(A[i]).add(B[i]);
        }
    }



    public static  void  sortArray(){
//        int[] num1 = { 4, 25, 9, 36, 49 };
//
//        Integer[] array = Arrays.stream(num1).boxed().toArray(Integer[]:: new);
//
//        //Integer squares[] = new Integer[] { 4, 25, 9, 36, 49 };
//        Arrays.sort(array,Collections.reverseOrder());
//
//        //Integer array[] = { 1, 2, 3, 4, 5 };
//
//        // Sorting the array in descending order
//        Arrays.sort(array, Collections.reverseOrder());

//
//        int[] num3 = { 4, 25, 9, 36, 49 };
        //Arrays.sort(num3, Collections.reverseOrder());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int[] example2 = list.stream().mapToInt(Integer::intValue).toArray();
        for(int num : example2){
            System.out.println(num);
        }
      //  return example2;
//        Arrays.sort(num1);



    }


}
    

