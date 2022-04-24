package com.mitaTestApp.prefixSum;

public class PreFixSumQA {
    public static void main(String[] args) {

    }
   //Leetcode 926 - Flip String to Monotone Increasing
    public int minFlipsMonoIncr(String s) {
        int onecount =0;
        int res =0;
        for(char ch: s.toCharArray()){
            if(ch =='1')
                onecount++;
            else{
                res++;
                res = Math.min(res,onecount);
            }
        }
        return res;
    }

    //152
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp_max = Math.max(curr, Math.max(max_so_far * curr, min_so_far * curr));
            min_so_far = Math.min(curr, Math.min(max_so_far * curr, min_so_far * curr));

            max_so_far = temp_max;

            result = Math.max(max_so_far, result);
        }

        return result;
    }

    //Leetcode 1653 - Minimum Deletions to Make String Balanced - Prefix sum
    public int minimumDeletions(String s) {
        //aababbab
        int bcount =0;
        int count =0;
        for(char ch : s.toCharArray()){
            if(ch=='b')
                bcount++;
            else{
                count++;
                count = Math.min(count,bcount);
            }
        }
        return count;

    }
}
