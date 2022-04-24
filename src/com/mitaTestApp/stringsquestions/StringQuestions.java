package com.mitaTestApp.stringsquestions;

import sun.text.normalizer.UCharacter;

import java.nio.charset.Charset;
import java.util.*;

public class StringQuestions {
    public static void main(String[] args) {
        //isIsomorphic("egg","foo");
       // rotateString("abcde", "cdeab");
       //Character[] str = new Character[]{'A'};
        //MinWindow(str,"B");
      // longestDiverseString(1,1,7);
        reverseWords("a good   example");
    }
    //Leetcode 205 - Isomorphic Strings
    public static boolean isIsomorphic(String s, String t) {
       return checkString(s).equals(checkString(t));
    }
    private static String checkString(String str){
        Map<Character,Integer> map = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i =0;i< str.length();i++) {
            char ch = str.charAt(i);

            if (!map.containsKey(ch)) {
                map.put(ch, i);
            }
            stringBuilder.append(Integer.toString(map.getOrDefault(ch,0)));
            stringBuilder.append(" ");
        }
        return  stringBuilder.toString();

    }

    //Leetcode 769
    public static boolean rotateString(String s, String goal) {
        if(s.length() == 0 || goal.length()==0) return false;
        if(s.length() != goal.length()) return false;
        int last = goal.length()-1; //pivot
        boolean isValid = false;
        int index =0;
        char pivot = goal.charAt(last); //character
        Queue<Character> q = new LinkedList<>();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i) == pivot){
                q.add(s.charAt(i));
                index =i+1;
                isValid=true;
                break;
            } else {
                q.add(s.charAt(i));
            }
        }
        StringBuilder str = new StringBuilder();
        if(isValid) {
            while (index < s.length()) {
                if(s.charAt(index) == pivot){
                    q.add(s.charAt(index++));
                }
                else {
                    str.append(s.charAt(index++));
                }
            }
            while(!q.isEmpty()){
                str.append(q.poll());
            }
            str.append(pivot);
        }
        return str.toString().equals(goal);




    }

    //Leetcode 76
    public static String MinWindow(Character[] arr, String str) {
        if(str.length() ==0 || arr.length ==0)
            return "";
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : arr){
            if(map.containsKey(ch))
                map.put(ch, map.get(ch)+1);
            else
                map.put(ch,1);
        }
        int count = map.size();
        int slow =0; int fast =0;
        String output = "";
        while(fast< str.length()){
            char ch1 = str.charAt(fast);
            if(map.containsKey(ch1))
                map.put(ch1,map.get(ch1)-1);
            else {
                fast++;
                continue;
            }

            if(map.get(ch1) ==0)
                count--;
            while(count==0){
                if(output == "" || output.length() > (fast-slow)+1)
                    output = str.substring(slow,fast+1);
                char ch2 = str.charAt(slow);
                if(map.containsKey(ch2)){
                    map.put(ch2, map.get(ch2)+1);

                    if(map.get(ch2) >0){
                        count++;
                    }

                }
                slow++;
            }
            fast++;
        }
        System.out.println(output);
        return output;
    }

    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int index1 =-1; int index2 =-1;
        boolean issame = word1.equals(word2);
        int min =Integer.MAX_VALUE;
        for(int i = 0; i < wordsDict.length; i++){

            if(wordsDict[i].equals(word1)){
                if(issame){
                    index1 = index2;
                    index1 = i;

                }
                else
                    index1 =i;

            }
            if(wordsDict[i].equals(word2)){
                index2 = i;
            }

            if(index1!=-1 && index2!=-1){
                min =   Math.min(min, Math.abs(index1-index2));
            }
        }
        return min;
        //System.out.println("min = " + min);
    }

    public String[] THOUSAND ={"", "Thousand","Million","Billion"};
    public String[] TENS ={"", "", "Twenty","Thirty","Forty","Fifty", "Sixty","Seventy","Eighty","Ninety"};
    public String[] LESS_THAN_20 ={"", "One","Two","Three","Four", "Five","Six","Seven","Eight", "Nine","Ten","Eleven","Twelve", "Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    public String numberToWords(int num) {

        StringBuilder str = new StringBuilder();
        int index =0;
        if(num ==0) return "Zero";
        while (num>0)
        {
            if(num%1000 >0){
                StringBuilder temp =new StringBuilder();
                Helper(num%1000,temp);
                temp.append(THOUSAND[index]).append(" ");
                str.insert(0,temp);
            }
            num =num/1000;
            index++;
        }
        return str.toString().trim();

    }
    private void Helper(int num, StringBuilder output) {
        if(num ==0)
            return;
        else if(num <20)
        {
            output.append(LESS_THAN_20[num]).append(" ");
        }
        else if(num <100)
        {
            output.append(TENS[num/10]).append(" ");
            Helper(num%10,output);
        }
        else
        {
            output.append(LESS_THAN_20[num/100]).append(" Hundred ");
            Helper(num%100,output);
        }
    }

    //Leetcode 1405 - Longest Happy String
    class  CharacterPair{
        int frequency;
        char ch;
        CharacterPair(int frequency, char ch){
            this.frequency = frequency;
            this.ch = ch;
        }
    }
    public  String longestDiverseString(int a, int b, int c){
        StringBuilder str = new StringBuilder();
        PriorityQueue<CharacterPair> pq = new PriorityQueue<CharacterPair>((x,y)-> y.frequency -x.frequency); //max heap
        if(a>0){
            pq.offer(new CharacterPair(a, 'a'));
        }
        if(b>0){
            pq.offer(new CharacterPair(b, 'b'));
        }
        if(c>0){
            pq.offer(new CharacterPair(c, 'c'));
        }

        while(!pq.isEmpty()){
            CharacterPair top = pq.poll();
            CharacterPair second =pq.isEmpty() ? null :  pq.poll();

            int count = Math.min(2, top.frequency);
            str.append(getString(top.ch, count));
            top.frequency -= count;

            if(second == null){
                break;
            }
            count = top.frequency < second.frequency ? Math.min(2, second.frequency): Math.min(1, second.frequency);
            str.append(getString(second.ch, count));
            second.frequency-=count;

           if(top.frequency >0) {
                pq.offer(top);
           }

           if(second.frequency >0) {
                pq.offer(second);
           }
        }
        return str.toString();
    }
    private  String getString(char ch, int count) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<count; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String reverseWords(String s) {
        StringBuilder output = new StringBuilder();
        if(s.length() ==0) return output.toString();
        s = s.trim();

        String[] strings = s.split(" ");
        int i =0; Stack<String> stack = new Stack<>();
        while(i < strings.length){
            String word = strings[i].trim();
            if(!word.isEmpty()){
                stack.push(word);
            }
        }
        while(!stack.isEmpty()){
           output.append(stack.pop()).append(" ");
        }
        return  output.toString().trim();

    }



    //
    public int lengthOfLastWord(String s) {
        if(s.length() == 0) return 0;
        s = s.trim();
        String[] str = s.split(" ");
        // return s.length()-s.lastIndexOf(" ")-1;
        int i = str.length-1;
        while(i >= 0){
            String word = str[i--].trim();
            if(!word.isEmpty()){
                return word.length();
            }
        }
        return 0;
    }

}
