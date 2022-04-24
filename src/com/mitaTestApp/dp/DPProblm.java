package com.mitaTestApp.dp;

import java.util.*;

public class DPProblm {
    public static void main(String[] args) {
        //countPalindromicSubsequences("bccb");
       // minInsertions("(()))");
//        int[] arr = new int[]{2,3,4};
//        SubsetSum(arr,5);
        shortestCommonSupersequence("abac" , "cab");
    }

    //Leetcode 256 - Paint House
    public int minCost(int[][] costs) {
        if(costs.length ==0) return 0;
        int n = costs.length;
        for(int i=1;i<n;i++){
            costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
            costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
            costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]) ;
        }
        return Math.min(costs[n-1][0], Math.min(costs[n-1][1], costs[n-1][2]));
    }

    //Leetcode 265 - Paint House II
    public int minCostII(int[][] costs) {
        if(costs.length ==0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        for(int i=1;i<n;i++){

            int fstmin =-1,seconmin =-1;
            for(int j =0;j<k;j++){
                int cost = costs[i-1][j];
                if(fstmin ==-1 || cost < fstmin){
                    seconmin = fstmin;
                    fstmin = cost;
                }else if(seconmin ==-1|| cost <seconmin){
                    seconmin = cost;
                }
            }
            for(int j =0;j<k;j++){
                if(costs[i][j] == fstmin){
                    costs[i][j] +=seconmin;
                }
                else
                    costs[i][j] +=fstmin;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int price : costs[n-1]){
            min = Math.min(min,price);
        }
        return min;
    }

    //Leetcode 516 - Longest Palindromic Subsequence
    public int longestPalindromeSubseq(String s) {
        if(s.length() ==0) return 0;

        StringBuilder str = new StringBuilder();
        str.append(s).reverse();
        String s1 = str.toString();
        int m = s.length();
        int n = s1.length();
        int[][] t = new int[m+1][n+1];
        for(int i=0;i<m+1;i++){
            for(int j=0;j<n+1;j++){
                if(i==0||j==0)
                    t[i][j] =0;
            }
        }
        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(s.charAt(i-1) == s1.charAt(j-1))
                    t[i][j] = 1+ t[i-1][j-1];
                else
                    t[i][j] = Math.max(t[i-1][j],t[i][j-1]);
            }
        }
        return t[m][n];
    }

    //Leetcode 560 - Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        int count =0;
        int sum =0;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(map.containsKey(sum-k))
                count += map.get(sum-k);
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return count;
    }

    //Leetcode 698 - Partition to K Equal Sum Subsets
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums.length ==0 || k ==0)
            return false;
        int sum =0; int target =0;
        for(int num:nums) sum+= num;
        if(sum%k !=0)
            return false;
        else
            target = sum/k ;
        int m = nums.length;
        int[][] t = new int[m+1][target+1];

        for(int i =0;i<m+1;i++){
            for(int j =0;j<target+1;j++){
                if(i==0) t[i][j] =1;
                else if(j==0)
                    t[i][j] =0;
            }
        }
        for(int i =1;i<m+1;i++){
            for(int j =1;j<target+1;j++){
                if(nums[i-1] <j){
                    t[i][j] = t[i-1][j] + t[i-1][j-nums[i-1]];
                }
                else
                {
                    t[i][j] = t[i-1][j];
                }
            }

        }
        return (t[m][target] > 0);

    }

    //Leetcode 53 - Maximum Sub array
    public int maxSubArray(int[] nums) {
        int sumarray = nums[0];
        int maxarray = nums[0];
        for(int i=1;i<nums.length;i++){
            sumarray = Math.max(nums[i], (sumarray+nums[i]));
            maxarray = Math.max(maxarray,sumarray);
        }
        return maxarray;
    }

    public int minimumOneBitOperations(int n) {
        String s=Integer.toBinaryString(n);
        String s2 = Integer.toBinaryString(0);
        int len = s.length();
        int len1 = s2.length();
        int[][] t = new int[len+1][len1+1];

        for(int i =0;i<len+1;i++){
            for(int j=0;j<len1+1;j++){
                if(i ==0 || j==0){
                    t[i][j] = 0;
                }
            }
        }
        for(int i =1;i<len+1;i++){
            for(int j=1;j<len1+1;j++){
                if(s.charAt(i-1) == s2.charAt(j-1) ){
                    t[i][j] = 1+t[i-1][j-1];
                }
                else
                    t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
            }
        }
        int lps =  t[len][len1];
        int insert = s.length()-lps;
        return insert;
    }

    //1541
    public static int minInsertions(String s) {
        int res = 0, right = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                if (right % 2 > 0) {
                    right--;
                    res++;
                }
                right += 2;
            } else {
                right--;
                if (right < 0) {
                    right += 2;
                    res++;
                }
            }
        }
        return right + res;

    }

    //leetcode 1092 - SuperSequence
    public static  int shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] t = new int[m + 1][n + 1];

        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0 || j == 0) {
                    t[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = Math.max(t[i][j - 1], t[i - 1][j]);

            }
        }
         int lcs =  t[m][n];
         int output = m+n-lcs;
        int i = m;
        int j = n;
        StringBuilder str = new StringBuilder();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                str.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (t[i-1][j] > t[i][j-1]) {
                str.append(str1.charAt(i - 1));
                i--;
            } else {
                str.append(str2.charAt(j - 1));
                j--;
            }
        }
        while(i > 0){
            str.append(str1.charAt(i-1));
            i--;
        }
        while(j > 0){
            str.append(str2.charAt(j-1));
            j--;
        }
       // return str.reverse().toString();
        System.out.println(output);
        System.out.println( str.reverse().toString());
        return output;
    }

    public static boolean SubsetSum(int[] nums, int target) {
        int m = nums.length;
        boolean[][]t = new boolean[m+1][target+1];
        for(int i =0;i<m+1;i++){
            for(int j=0;j<target+1;j++){
                if(i ==0 && j==0 || j==0)
                    t[i][j] = true;
                if(i == 0 && j!=0)
                    t[i][j] = false;

            }

        }
        for(int i =1;i<m+1;i++){
            for(int j=1;j<target+1;j++){
                if(nums[i-1] <= j ){
                    t[i][j] = t[i-1][j] || t[i-1][j-nums[i-1]];
                }
                else
                    t[i][j] =  t[i-1][j];
            }
        }
        print2D(t);
        System.out.println(t[m][target]);
        return t[m][target];
    }

    public static void print2D(boolean mat[][]) {
        // Loop through all rows
        for (boolean[] row : mat)

            // Loop through all elements of current row
            //for (int j = 0; j < mat[i].length; j++)
                System.out.println(Arrays.toString(row));
    }

    public int maxKillbyBomb(char[][] grid){
        if(grid == null || grid.length ==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int max =0 ; int[] col = new int[n];
        for(int i =0;i<m;i++){
            int row =0;
            for(int j =0;j<n;j++){
                if(grid[i][j] == '0'){
                    int total = row+ col[j];
                    for(int k = j+1;k<n && grid[i][k] !='W';k++){ //cols
                        if(grid[i][k] =='E'){
                            total++;
                        }
                    }
                    for(int k = i+1;k<n && grid[k][j] !='W';k++){ //rows
                        if(grid[k][j] =='E'){
                            total++;
                        }
                    }
                    max = Math.max(total,max);
                }
                else if(grid[i][j] =='W') {
                    row = 0; col[j] =0;
                }
                else{
                    row++;
                    col[j]++;
                }
            }
        }
        return max;
    }

    //Leetcode 72. Edit Distance
    public int minDistance(String word1, String word2) {
        //25895
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1+1][l2+1];

        // Base cases
        // Initializing First row
        for(int i=0; i <= l2; i++)
            dp[0][i] = i;
        // Initializing First col
        for(int i=0; i <= l1; i++)
            dp[i][0] = i;

        for(int i=1; i <= l1; i++){
            for(int j=1; j <= l2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], // replace
                            Math.min(dp[i-1][j], // delete
                                    dp[i][j-1]) // insert
                    );
            }
        }

        return dp[l1][l2];
    }

    //152. Maximum Product Subarray
//    public int maxProduct(int[] nums) {
//
//    }

    static List<String> list1;
    public static int countPalindromicSubsequences(String s) {
        if(s.length() == 0) return 0;
        list1 = new ArrayList<>();
        backtrack(s,0,new ArrayList<String>());

        for(int i =0;i<list1.size();i++){
            System.out.println(list1.get(i));
        }
        return list1.size();
    }
    private static void backtrack(String s, int start, List<String> list){
        if(start >= s.length()) {
            for(int i =0;i<list.size();i++){
                list1.add(list.get(i));
            }
            return;
        }

        for(int i=start;i<s.length();i++){
            if(Palindrome(s,start,i)){
                list.add(s.substring(start,i+1));
                backtrack(s,i+1,list);
                list.remove(list.size()-1);
            }
        }
    }
    private static boolean Palindrome(String s, int low, int high) {
        while(low<high){
            if(s.charAt(low++)!= s.charAt(high--))
                return false;
        }
        return true;
    }
}
