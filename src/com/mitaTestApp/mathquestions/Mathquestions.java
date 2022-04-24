package com.mitaTestApp.mathquestions;

import java.util.*;

public class Mathquestions {
    public static void main(String[] args) {
     int[][] num = new int[][]{ {1,1},{2,2},{3,3} };
        maxPoints(num);
    }

    //leetcode 367 - Valid Perfect Square
    public boolean isPerfectSquare(int num) {
        if(num<2) return true;
        long num1 = num/2;
        while(num1*num1 > num){
            num1 = (num1 +num/num1)/2;
        }
        return (num1*num1 == num);
    }

    //Leetcode 149. Max Points on a Line
    public static int maxPoints(int[][] points) {
        int max=0; int same=0;
        Set<String> set = new HashSet<String>();
        int n = points.length;
        if(n<2) return n;
        for(int i =0;i<n && !set.contains(points[i][0]-points[i][1]);i++){
            int[] a = points[i];
            int localmax =1;
            Map<Double, Integer> map = new HashMap<Double, Integer>();
            for(int j =i+1;j<n;j++){
                int[] b = points[j];
                if(isSame(a,b)){
                    same++;
                    continue;
                }

                double slope = calculateSlope(a,b);
                map.put(slope,map.getOrDefault(slope,1)+1);
                localmax = Math.max(localmax, map.get(slope));

            }
            set.add(a[0]+"-"+a[1]);
            max = Math.max(max, localmax);
        }
        return max;

    }
    private static boolean isSame(int[]a, int[]b ){
        if(a[0] == b[0] && a[1] == b[1])
            return  true;

        return false;
    }
    private static  double calculateSlope(int[]a, int[]b){
        if(a[0] == b[0]) return Integer.MAX_VALUE;
        if(a[1] ==b[1]) return 0;
        return ((double) b[0] - a[0]) / ((double) b[1] - a[1]);
    }

    //Leetcode 319 - Bulb Switcher
     public int bulbSwitch(int n) {
            // if(n==0)
            //     return 0;
            // return (int)Math.sqrt(n);
             int ans=0;
            for(int i=1; i*i<=n; i++){  // for finding perfect squares
             ans++;  //
            }
            return ans;
        }

    //Leetcode 672 - Bulb Switcher II
    public int flipLights(int n, int presses) {
        if(presses ==0) return 1;
        if(n ==1)
            return 2;
        if(n==2){
            if(presses ==1)
                return 3;
            else
                return 4;
        }
        if(n>=3){
            if(presses ==1)
                return 4;
            else if(presses ==2)
                return 7;
            else
                return 8;
        }
        return 0;

    }

    //Leetcode 1304 - Find N Unique Integers Sum up to Zero
    public int[] sumZero(int n) {
        if (n == 0) return null;
        List<Integer> list = new ArrayList<>();
        if(n%2 !=0){
            list.add(0);
        }
        for(int i=1;i<n/2;i++){
            list.add(i);
            list.add(-i);
        }
        int[] output = list.stream().mapToInt(Integer::intValue).toArray();
        return output;
    }


}
