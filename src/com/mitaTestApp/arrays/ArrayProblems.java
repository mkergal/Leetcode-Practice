package com.mitaTestApp.arrays;

import java.util.*;

public class ArrayProblems {

    public static void main(String[] args) {
    }

    //Leetcode 1 -Two Sum- > T-O(N) S- O(N)
    public int[] twoSum(int[] nums, int target) {
        int[] arr =new int[2];
        if(nums.length ==0) return arr;

        Map<Integer,Integer> map  = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int comp = target-nums[i];
            if(map.containsKey(comp)){
                arr[0] =i;
                arr[1] = map.get(comp);
            }
            else
            {
                if(map.containsKey(nums[i])){
                    map.put(nums[i],i);
                }
                else
                    map.put(nums[i],i);
            }
        }
        return arr;
    }

    //Leetcode 13 - Three Sum  T-O(n2), S- O(N)
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length ==0)
            return  new ArrayList();
        Arrays.sort(nums);
        int j =0;
        int k = 0;
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0;i< nums.length-2;i++){
            if (i > 0 && nums[i] == nums[i - 1]) { continue; }
            j =i+1;
            k = nums.length-1;
            while(j<k){
                int sum = nums[j]+nums[k]+nums[i];
                if(sum == 0){
                    set.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while(j<k && nums[j] == nums[j-1])
                        ++j;
                }
                else
                {
                    if(sum > 0){
                        k--;
                    }
                    else if(sum <0){
                        j++;
                    }
                }
            }
        }
        return new ArrayList(set);

    }

    //Leetcode 18 - 4Sum  T-O(N3) S- O(N)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length <4) return list;
        int n = nums.length;
        Arrays.sort(nums);
        for(int i =0;i<n;i++){
            if(i>0 && nums[i] == nums[i-1]) continue;
            for(int j =i+1;j<n;j++){
                if(j>i+1 && nums[j]== nums[j-1]) continue;
                int low  = j+1;
                int high = n-1;
                while(low < high){
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if(sum < target)
                        low++;
                    else if(sum > target)
                        high--;
                    else{
                        list.add(Arrays.asList(nums[i],nums[j],nums[low],nums[high]));
                        while(low < high && nums[low] == nums[low+1]) low++;
                        while(high > low && nums[high] == nums[high-1]) high--;
                        low++;
                        high--;
                    }
                }
            }
        }
        return list;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 =  m-1;
        int p2 = n-1;
        int i = m+n-1;

        while(p2>=0){
            if(p1>0 && nums1[p1]> nums2[p2]){
                nums1[i--] = nums1[p1--];
            }
            else{
                nums1[i--] = nums2[p2--];
            }
        }
    }

    //Leetcode 611 - valid Triangle Number
    public int triangleNumber(int[] nums) {
        if (nums.length == 0)
            return 0;
        Arrays.sort(nums);
        int k = 0;
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            k = i + 2;
            for (int j = i + 1; j < nums.length - 1; j++) {
                while (k < nums.length && nums[i] + nums[j] > nums[k]) {
                    k++;
                    count += k - j - 1;
                }
            }
        }
        return count;
    }

    //Leetcode 121 - Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        if(prices.length ==0)
            return 0;
        int min =Integer.MAX_VALUE;
        int diff =Integer.MIN_VALUE;
        for(int i =0;i<prices.length;i++)
        {
            diff = Math.max(prices[i]-min, diff);
            min = Math.min(prices[i],min);
        }
        return diff;
    }

    //Leetcode 66 -  Plus One
    public int[] plusOne(int[] digits) {
        if(digits.length ==0)
            return null;
        int carry =0;
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i]==9){
                digits[i] =0;
                carry=1;
            }
            else{
                digits[i]++;
                return digits;
            }

        }
        if(carry ==1){
            digits = new int[digits.length+1];
            digits[0]=1;
            return digits;
        }
        return null;
    }

    //Leetcode 53 - Maximum Subarray
    public int maxSubArray(int[] nums) {

        int sumarray = nums[0];
        int maxarray = nums[0];
        for(int i=1;i<nums.length;i++){
            sumarray = Math.max(nums[i], (sumarray+nums[i]));
            maxarray = Math.max(maxarray,sumarray);
        }
        return maxarray;

    }

    //Leetcode 244. Shortest Word Distance II
    class WordDistance {
        Map<String,ArrayList<Integer>> map;
        public WordDistance(String[] wordsDict) {
            map = new HashMap<String,ArrayList<Integer>>();

            for(int i=0;i<wordsDict.length;i++){
                ArrayList<Integer> loc = map.getOrDefault(wordsDict[i], new ArrayList<Integer>());
                loc.add(i);
                map.put(wordsDict[i],loc);
            }
        }
        public int shortest(String word1, String word2) {
            ArrayList<Integer> loc1,loc2;
            loc1  = map.get(word1);
            loc2  = map.get(word2);
            int l1 =0, l2=0, min = Integer.MAX_VALUE;
            while(l1<loc1.size() && l2<loc2.size()){
                min = Math.min(min, Math.abs(loc1.get(l1)- loc2.get(l2)));
                if(loc1.get(l1) < loc2.get(l2)){
                    l1++;
                }
                else{
                    l2++;
                }
            }
            return min;
        }
    }

    //
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) output[idx++] = s;
        return output;
    }

    //leetcode 243 - Shortest Word distance
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length;
        if(n ==0) return -1;
        int min = Integer.MAX_VALUE;
        int l1 =-1;
        int l2 =-1;
        if(word1.length() ==0 || word2.length() ==0) return 0;

        for(int i =0;i<wordsDict.length;i++){
            if(wordsDict[i].equals(word1)){
                l1= i;}
            if(wordsDict[i].equals(word2)){
                l2= i;}

            if(l1!=-1 && l2!=-1){
                min = Math.min(min, Math.abs(l1-l2));
            }

        }
        return min;
    }

    //Leetcode 150
    public int evalRPN(String[] tokens){
        return 1;
    }

    //Leetcode 1304. Find N Unique Integers Sum up to Zero
    public int[] sumZero(int n) {
        if(n==0) return null;
        if(n ==1) return new int[]{0};
        List<Integer> list = new ArrayList<>();
        if(n%2!=0) list.add(0);
        for(int i=1;i<=n/2;i++){
            list.add(i);
            list.add(-i);
        }
        int[] output =list.stream().mapToInt(Integer::intValue).toArray();
        return output;
    }




}
