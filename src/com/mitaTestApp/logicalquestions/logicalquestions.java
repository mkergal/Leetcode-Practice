package com.mitaTestApp.logicalquestions;

import java.util.HashMap;
import java.util.Map;

public class logicalquestions {
    public static void main(String[] args) {

    }

    public static int fibinacci(int n){
        int f[] = new int[n + 2];int i;

        // 0th and 1st number of
        // the series are 0 and 1
        f[0] = 0;
        f[1] = 1;

        for (i = 2; i <= n; i++) {

            // Add the previous 2 numbers
            // in the series and store it
            f[i] = f[i - 1] + f[i - 2];
        }

        // Nth Fibonacci Number
        return f[n];
    }

    public static void factors(int n){
//        for(int i =1;i<=n;i++ ){
//            if(n%i ==0) {
//                System.out.println(i);
//            }
//        }

        for(int i =1;i<Math.sqrt(n);i++){
            if(n%i ==0) {
                System.out.println(n/i);
            }
        }
    }
    class TwoSum {

        private Map<Integer,Integer> map ;
        public TwoSum() {
            map = new HashMap<Integer,Integer>();
        }

        public void add(int number) {
            if(map.containsKey(number)){
                map.put(number,map.get(number)+1);
            }
            else{
                map.put(number,1);
            }

        }

        public boolean find(int value) {
            for(Map.Entry<Integer,Integer> item : map.entrySet()){
                int comp =Math.abs(item.getKey() - value);
                if(map.containsKey(comp)){
                    return true;
                }
            }
            return false;
        }
    }

    //Arrays Problem
    static int findMaxSubarraySum(int arr[],
                                  int n, int sum) {

        if(arr.length ==0 || sum == 0) return 0;
        int curr_sum =arr[0], start =0,  max =0, maxLen =0;

        for(int i =1;i<n;i++){
            if(curr_sum < sum){
                max = Math.max(max,curr_sum);
                maxLen= Math.max(maxLen, i-start+1);
            }
            while(curr_sum + arr[i] > sum && start<i){
                curr_sum-=  arr[start];
                start++;
            }
            curr_sum += arr[i];
        }

        if(curr_sum < sum){
            max = Math.max(max,curr_sum);
            maxLen= Math.max(maxLen, n-1-start+1);
        }

        return maxLen;
    }
}
