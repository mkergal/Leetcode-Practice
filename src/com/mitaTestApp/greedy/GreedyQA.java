package com.mitaTestApp.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreedyQA {
    public static void main(String[] args) {

    }

    //Leetcode 1386 - Cinema Seat Allocation - Greedy
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] seats : reservedSeats){
            map.put(seats[0], new ArrayList<Integer>());
            map.get(seats[0]).add(seats[1]);
        }
        int ans =2*(n-map.size());

        for(Map.Entry<Integer,List<Integer>> entry: map.entrySet()){
            boolean left = false; boolean right = false; boolean middle = false;

            for(int seat: entry.getValue()){
                if(seat >=2 && seat <=5)
                    left = true;
                if(seat >=6 && seat <=9)
                    right = true;
                if(seat >=4 && seat<=7)
                    middle = true;

                if(left && right && middle)
                    break;
            }
            if(!left)
                ans++;
            if(!right)
                ans++;
            if(left && right && !middle)
                ans++;
        }
        return ans;
    }
}
