package com.mitaTestApp.priorityqueueproblems;

import com.mitaTestApp.domain.ListNode;
import com.mitaTestApp.domain.Pairs;

import java.util.*;
import java.util.PriorityQueue;

public class priorityleetcode {

    public static void main(String[] args) {
        //int[] nums1 = new int[]{1,2,3,4,6};
       // int[] nums2 = new int[]{3,5,10,6,9};
      //  int[] nums3 = new int[]{20,20,100,70,60};
        //kthSmallestProduct(nums1, nums2, 1);

        int[][] a = {
                {1, 6, 1},
                {3, 10, 2},
                {10, 12, 3},
                {11, 12, 2},
                {12, 15, 2},
                {13, 18, 1},
        };
        maxTaxiEarnings(20,a);
    }

    //leetcode 23 - Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>((o1, o2) -> o1.val - o2.val);
        for (ListNode l1 : lists) {
            if (l1 != null) {
                pq.add(l1);
            }
        }
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (!pq.isEmpty()) {
            curr.next = pq.poll();
            curr = curr.next;

            if (curr.next != null)
                pq.add(curr.next);
        }
        return head.next;
    }

    //295. Find Median from Data Stream
    public class MedianFinder {
        PriorityQueue<Integer> min;                             //minHeap
        PriorityQueue<Integer> max;

        public MedianFinder() {
            min = new PriorityQueue<>();
            max = new PriorityQueue<>((a, b) -> b - a);
        }

        public void addNum(int num) {
            if (max.isEmpty() || num <= max.peek())
                max.add(num);
            else
                min.add(num);
            balanceHeap();
        }

        public double findMedian() {

            if (max.size() == min.size())
                return ((double) max.peek() + min.peek()) / 2;
            else
                return max.peek();
        }

        public void balanceHeap() {

            if (max.size() > min.size() + 1) {
                min.add(max.remove());
            } else if (min.size() > max.size()) {
                max.add(min.remove());
            }
        }
    }

    //480. Sliding Window Median
    public double[] medianSlidingWindow(int[] nums, int k) {
        return null;
    }

    //347. Top K Frequent Elements
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0 || k <= 0)
            return null;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        //PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> b.getAge()-a.getAge());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
            //pq.add(new Pair(entry.getKey(),entry.getValue()));
        }
        int[] output = new int[k];
        for (int i = 0; i < k; i++) {
            output[i] = pq.poll().getKey();
        }
        return output;
    }

    //Leetcode 373 -
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((a, b) -> (a.get(0) + a.get(1)) - (b.get(0) + b.get(1)));
        k = Math.min(k, nums1.length * nums2.length);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pq.add(Arrays.asList(nums1[i], nums2[j]));
            }
        }
        while (k != 0 && !pq.isEmpty()) {
            list.add(new ArrayList<>(pq.poll()));
            k--;
        }
        return list;
    }

    //Leetcode 215 - Kth largest element
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        for (int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.poll();
    }

    //Leetcode 973-K Closest Points to Origin
    public int[][] KClosest(int[][] points, int k) {
        PriorityQueue<Pairs> pq = new PriorityQueue<Pairs>((a, b) -> b.getDist() - a.getDist());
        for (int[] cords : points) {
            int dist = cords[0] * cords[0] + cords[1] * cords[1];
            pq.offer(new Pairs(cords[0], cords[1], dist));
            if (pq.size() > k)
                pq.poll();
        }

        int[][] ans = new int[k][2];
        for (int i = 0; i < k; i++) {
            Pairs p = pq.poll();
            ans[i] = new int[]{p.getX(), p.getY()};
        }
        return ans;
    }

    //Leetcode 2040 -
    public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> b-a);

        for(int i =0;i<nums1.length;i++){
            for(int j =0;j<nums2.length;j++){
                int prod = nums1[i]*nums2[j];
                pq.add(prod);
                if(pq.size()>k)
                    pq.poll();
            }
        }
        int min =Integer.MIN_VALUE;
        while(!pq.isEmpty()) {
            min = Math.min(pq.poll(),min);

        }
        System.out.println(min);

        return Long.MIN_VALUE;
//        int min =Integer.MAX_VALUE;
//        while(!pq.isEmpty()){
//            min = Math.min(pq.poll(),min);
//        }
//        return min;
    }

    //Leetcode 1235. Maximum Profit in Job Scheduling
    static class The_Comparator implements Comparator<ArrayList<Integer>> {
        public int compare(ArrayList<Integer> list1, ArrayList<Integer> list2) {
            return list1.get(0) - list2.get(0);
        }
    }

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        if (!(startTime.length == endTime.length && endTime.length == profit.length))
            return 0;
        List<List<Integer>> jobs = new ArrayList<>();
        for (int i = 0; i < profit.length; i++) {
            ArrayList<Integer> curr = new ArrayList<>();
            curr.add(startTime[i]);
            curr.add(endTime[i]);
            curr.add(profit[i]);
            jobs.add(curr);
        }
        jobs.sort(Comparator.comparingInt(a -> a.get(0)));
        return maxProfit(jobs);

    }
    private static int maxProfit(List<List<Integer>> jobs) {
        int n = jobs.size(), maxProfit = 0;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>((list1,list2)-> list1.get(0) - list2.get(0));
        for (int i = 0; i < n; i++) {
            int start = jobs.get(i).get(0), end = jobs.get(i).get(1), profit = jobs.get(i).get(2);
            while(!pq.isEmpty() && start>= pq.peek().get(0)){
                maxProfit = Math.max(maxProfit,pq.peek().get(1));
                pq.remove();
            }
            ArrayList<Integer> arr = new ArrayList();
            arr.add(end);
            arr.add(profit+maxProfit);
            pq.add(arr);
        }
        while(!pq.isEmpty()){
            maxProfit = Math.max(maxProfit,pq.peek().get(1));
            pq.remove();
        }

        return maxProfit;
    }
    public static long maxTaxiEarnings(int n, int[][] rides) {
        if(n==0) return 0;
        int profit =0;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>((list1,list2)-> list2.get(0) - list1.get(0));

        for(int i=0;i<rides.length;i++){
            int start = rides[i][0];
            int end = rides[i][1];
            int tip =end-start+ rides[i][2];

            if(pq.isEmpty() || end > pq.peek().get(0)){
                ArrayList<Integer> arr = new ArrayList<>();
                if(i !=0 && (tip >= pq.peek().get(1) && end > pq.peek().get(0))){
                    pq.remove();
                }
                arr.add(end);
                arr.add(tip);
                pq.add(arr);
            }

        }
        while(!pq.isEmpty()){
            profit += pq.poll().get(1);
        }
        System.out.println(profit);
        return profit;

    }



}
















    //621,786,1834,373
//    public int[] kthSmallestPrimeFraction(int[] A, int k) {
//        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) ->
//                A[a[0]] * A[b[1]] - A[a[1]] * A[b[0]]);
//
//        for (:
//             ) {
//
//        }
//
//    }
