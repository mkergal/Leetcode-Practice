package com.mitaTestApp.stacks;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StackQuestions {

   // private static boolean nums;

    public static void main(String[] args) {
//        int[] arr = new int[]{1};//1,3,-1,-3,5,3,6,7
//        maxSlidingWindow(arr,1);
        //simplifyPath("/home//path/");
//        int [] arr = new int[]{5,-5};
//        AsteroidCollision(arr);
      //  minimumDeletions("bbaaaabb");
        int[] num = {1,3,-1,-3,5,3,6,};
       // nextGreaterElements(num);
        //maxSlidingWindow(num,3);
    }

    //Leetcode 150 - Evaluate Reverse Polish Notation
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        int output = 0;
        String operators = "+*-/";
        for (String character : tokens) {
            if (!operators.contains(character)) {
                stack.push(Integer.valueOf(character));
                continue;
            }
            int num2 = stack.pop();
            int num1 = stack.pop();

            switch (character) {
                case "+":
                    output = num1 + num2;
                    stack.push(output);
                    break;
                case "-":
                    output = num1 - num2;
                    stack.push(output);
                    break;
                case "*":
                    output = num1 * num2;
                    ;
                    stack.push(output);
                    break;
                case "/":
                    output = num1 / num2;
                    stack.push(output);
                    break;
            }
        }
        return stack.pop();

    }

    //Leetcode 496 - Next Greater Element I
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
            return null;
        int n = nums1.length;
        int[] output = new int[n];
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while (!stack.empty()) {
            map.put(stack.pop(), -1);
        }
        for (int i = 0; i < nums1.length; i++) {
            output[i] = map.get(nums1[i]);
        }
        return output;
    }

    //Leetcode 503 - Next Greater Element II
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        if (n == 0) return null;
        int[] output = new int[n];
        Arrays.fill(output, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                output[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return output;
    }

    //leetcode  1047 Remove All Adjacent Duplicates In String
    public String removeDuplicates(String str) {
        if (str.length() == 0)
            return "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == str.charAt(i)) {
                stack.pop();
                continue;
            }
            stack.push(str.charAt(i));
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty())
            stringBuilder = stringBuilder.append(stack.pop());
        return stringBuilder.reverse().toString();
    }

    //Leetcode 316 - Remove Duplicate Letters
    public String removeDuplicateLetters(String s) {
        if(s.length()==0) return null;
        int[] freq = new int[26];
        boolean[] visisted = new boolean[26];
        for(char ch: s.toCharArray()){
            freq[ch-'a']++;
        }
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            freq[ch-'a']--;
            if(visisted[ch-'a'])
                continue;
            while(!stack.isEmpty() && stack.peek()>ch && freq[stack.peek() -'a'] > 0){
                char rem = stack.pop();
                visisted[rem-'a'] = false;
            }
            stack.push(ch);
            visisted[ch-'a'] = true;
        }
        int len = stack.size()-1;
        char[] arr = new char[stack.size()];
        while(!stack.isEmpty()){
            arr[len--] = stack.pop();
        }
        return new String(arr);
    }

    //Leetcode 1544 - Make string good
    public static String makeGood(String s) {
        if (s.length() == 0) return null;
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(!stack.isEmpty() && Math.abs(stack.peek()- s.charAt(i)) == 32){
                stack.pop(); continue;
            }
            else
                stack.push(s.charAt(i));
        }
       while(!stack.isEmpty()) {
           stringBuilder = stringBuilder.append(stack.pop());
       }
        System.out.println(stringBuilder.reverse().toString());
       return  stringBuilder.reverse().toString();
    }

    //Leetcode 402. Remove K Digits ---On Hold
    public static String removeKdigits(String num, int k) {
        LinkedList<Character> list = new LinkedList<>();
        for(char c: num.toCharArray()){
            while(!list.isEmpty() && list.peekLast() > c && k>0){
                list.removeLast();
                k--;
            }
            list.addLast(c);
        }
        while(k>0){
            list.removeLast();
            k--;
        }
        StringBuilder str  = new StringBuilder();
        for(int i=0;i< list.size();i++){
            if(str.toString().isEmpty() && list.get(i) =='0') continue;
            str.append(list.get(i));
        }
        if(str.length() ==0) return "0";
        return str.toString();
    }

    //Leetcode 71-  Simplify Path
    public static String simplifyPath(String path) {
        if(path.length() ==0)
            return null;

              String[] component = path.split("/");

        Stack<String> stack = new Stack<>();
        for(String comp : component){
            if(comp.contains(".") || comp.isEmpty()) continue;
            else if(comp.contains("..")){
                if(!stack.isEmpty())
                    stack.pop();
            }
            else{
                stack.push(comp);
            }
        }

        for (String item: stack) {
            System.out.println(item);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String str:stack) {
            stringBuilder.append("/");
            stringBuilder.append(str);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString().length() > 0? stringBuilder.toString():"/";

    }

    //239,84,85,907
     ArrayDeque<Integer> deq = new ArrayDeque<Integer>();
     int [] nums;
    public  void clean_deque(int i, int k) {
        // remove indexes of elements not from sliding window
        if (!deq.isEmpty() && deq.getFirst() == i - k)
            deq.removeFirst();

        // remove from deq indexes of all elements
        // which are smaller than current element nums[i]
        while (!deq.isEmpty() && nums[i] > nums[deq.getLast()])
            deq.removeLast();
    }

    public  int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        // init deque and output
        this.nums = nums;
        int max_idx = 0;
        for (int i = 0; i < k; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            // compute max in nums[:k]
            if (nums[i] > nums[max_idx]) max_idx = i;
        }
        int [] output = new int[n - k + 1];
        output[0] = nums[max_idx];

        // build output
        for (int i  = k; i < n; i++) {
            clean_deque(i, k);
            deq.addLast(i);
            output[i - k + 1] = nums[deq.getFirst()];
        }
        return output;
    }

    //Leetcode 735. Asteroid Collision
    public static  int[] AsteroidCollision(int[] asteroids) {

        Deque<Integer> stack = new ArrayDeque<>();
        for (int ast : asteroids) {
            // While the asteroids are going to the right and they are smaller than the next asteroid
            while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -ast) {
                stack.pop();
            }
            // The stack is empty or the asteroid is going to the left or the same direction as the next asteroid
            if (stack.isEmpty() || stack.peek() < 0 || stack.peek() > 0 && ast > 0) stack.push(ast);
                // The two asteroids can destroy each other
            else if (stack.peek() == -ast) stack.pop();
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) res[i] = stack.pop();
        return res;
    }

    //Leetcode 1653 - Minimum Deletions to Make String Balanced
    public static int minimumDeletions(String s) {
        Stack<Character> st = new Stack<>();
        int min = 0;

        for (char c : s.toCharArray()) {
            if (!st.isEmpty() && st.peek() > c) {
                st.pop();
                min++;
            } else {
                st.push(c);
            }
        }

        return min;
    }
}












