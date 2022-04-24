package com.mitaTestApp.microsoft;

import java.util.*;

import com.mitaTestApp.DFSBFS.DFSBFS.TreeNode;

//https://leetcode.com/discuss/interview-question/1383740/microsoft-online-assessment-part-ii-till-july-2021
//https://leetcode.com/discuss/interview-question/1915190/Microsoft-or-OA-or-Redmond-WA-or-Get-Maximum-Distance-of-Same-Elements-in-an-Array
//zhttps://leetcode.com/discuss/interview-question/1770045/Microsoft-or-OA-or-MaximumSum
public class MicrosoftQuestions {
    public static void main(String[] args) {
//        int[] arr = new int[]{4,3,2,1,0};
//        maxChunksToSorted(arr);
        //System.out.println( maxValue("356",5));
        //ValidIPAddress("256.256.256.256");
    }
    //Leetcode 1647 - Minimum Deletions to Make Character Frequencies Unique
    public int minDeletions(String s) {
        int[] frequency = new int[26];
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            frequency[ch-'a']++;
        }
        PriorityQueue<Integer> pq= new PriorityQueue<>((a, b)-> b-a);
        for(int i=0;i<frequency.length;i++){
            if(frequency[i]>0){
                pq.add(frequency[i]);
            }
        }
        int delelecount =0;
        while(!pq.isEmpty()){
            int top = pq.remove();
            if( pq.size()>0 && pq.peek() == top){
                if(top -1 > 0){
                    pq.add(top-1);
                }
                delelecount++;
            }
        }
        return delelecount;
    }

    //https://leetcode.com/discuss/interview-question/1935240/Microsoft-or-OA-or-Greedy-%2B-Digit-manipulation
    //Example: A = {4, 2, 3, 7}, X=2, Y=2  x- no of sessions,
    public static int getMinVal(int[] cost, int x , int y){
        if(cost.length ==0 || x> cost.length){
            return -1;
        }
        int min =0;
        int slow =0 ; int fast = slow+1;
        while(fast < cost.length){
            if(fast -slow == y){
                min = Math.min(min, (cost[fast] + cost[slow]));
                slow++;
            }
            fast++;
        }
        return min;
    }

    //Leetcode 769 - Max Chunks To Make Sorted
    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length; int max =0; int ans =0;
        for(int i=0;i<n;i++){
            max = Math.max(max, arr[i]);
            if(max ==i)
                ans++;
        }
        System.out.println(ans);
        return ans;
    }

    //Leetcode 768 - Max Chunks To Make Sorted II
    public int maxChunksToSortedII(int[] arr) {
        if(arr.length ==0) return 0;
        int n = arr.length;
        int[] lmax = new int[n];
        int[] rmin = new int[n+1];
        int count =0;
        lmax[0]= arr[0];
        for(int i=1;i<n;i++){
            lmax[i] =Math.max(arr[i],lmax[i-1]);
        }
        rmin[n]= Integer.MAX_VALUE;
        for(int i = n-1;i>=0;i--){
            rmin[i] = Math.min(rmin[i+1], arr[i]);
        }
        for(int i=0;i<n;i++){
            if(lmax[i] <= rmin[i+1])
                count++;
        }
        return count;

    }

    //Leetcode 1186 - Maximum Number of Balloons
    public int maxNumberOfBalloons(String text) {
        int[] frequency = new int[26];
        int len  = text.length();
        int min =0;
        for(int i =0;i<len;i++){
            char ch = text.charAt(i);
            frequency[ch-'a']++;
        }
        min = frequency['b'-'a']; //b
        min = Math.min(min,frequency['a'-'a'] );//a
        min = Math.min(min,frequency['n'-'a'] );//n
        min = Math.min(min,frequency['l'-'a']/2 );//l
        min = Math.min(min,frequency['o'-'a']/2 );//o

        return min;

    }

    //Leetcode 1448 - Count Good Nodes in Binary Tree
    int count ;
    public int goodNodes(TreeNode root) {
          return   helper(root,Integer.MIN_VALUE);
    }
    private int helper(TreeNode root, int max){
        if(root == null) return 0;
        if(max<= root.val){
            max = root.val;
            count++;
        }
        int left = helper(root.left, max);
        int right = helper(root.right,max);
        return count;
    }

    //Maximum possible value by inserting '5'
    public static String maxValue(String n, int x) {
        boolean sign = n.charAt(0) == '-'?true:false;
        String s = "";
        for (int i = (n.charAt(0) == '-' ? 1 : 0); i < n.length(); i++)
        {
            char ch = n.charAt(i);
            if ((sign && x < ch - '0') || (!sign && x > ch - '0'))
            {
                s = n.substring(0, i) + x + n.substring(i);
                return s;
            }
        }
        return n + x;
    }

    //Leetcode 253 - Meeting Rooms II
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length ==0)
            return 0;
        int max =0;
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        int i =0;
        for(int[] interval : intervals){
            start[i] = interval[0];
            end[i] = interval[1];
            i++;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int s =0; int e =0; int count =0;
        while(s < start.length){
            if(start[s] < end[e]){
                count++;
                s++;
            }
            else{
                e++;
                count--;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    //leetcode 468 -Valid IP address
    public static  String ValidIPAddress(String IP) {
        if (IP.length() == 0) return "Neither";
        if (IsValidIpv4(IP)) {
            return "IPv4";
        }
        if (IsValidIpv6(IP)) {
            return "IPv6";
        }
        return "Neither";
    }
    private static boolean IsValidIpv4(String str) {
        String[] arr = str.split("\\.");
        if (arr.length != 4) return false;
        else
        {
            for (String item : arr)
            {
                if(Integer.parseInt(item) >255) return false;
                    if(Integer.parseInt(item) <0 )
                    return false;
            }
        }
        return true;
    }
    private  static boolean IsValidIpv6(String strIp) {
        String[] arr = strIp.split("\\:");
        if (arr.length != 8) return false;
        else
        {
            int j = 0;
            while (j < arr.length)
            {
                if (arr[j].length() > 4 || !arr[j].equals("")) return false;
                for (Character str : arr[j].toCharArray()) {
                    if(!((str>='0' && str <='9') || (str>='a' && str<='f') || (str >='A' && str <='F')))
                        return false;
                }
                j++;
            }

        }
        return true;
    }

    //Leetcode 724- Find Pivot Index
    public int pivotIndex(int[] nums) {
        int sum =0;
        int i=0;
        while(i<nums.length){
            sum += nums[i];
            i++;
        }
        int leftsum =0;
        i=0;
        while(i<nums.length){
            if(sum - nums[i] == leftsum){
                return i;
            }
            else{
                leftsum += nums[i];
                sum -=nums[i];
            }
            i++;
        }
        return -1;
    }

    //116. Populating Next Right Pointers in Each Node -solved

    //Leetcode 1763 - Longest Nice Substring EG-“azABaabza” returns “ABaab”
    public String longestNiceSubstring(String s) {
        int[] sub = longestNiceSubstring(s, 0, s.length());
        return s.substring(sub[0], sub[1]);
    }
    private int[] longestNiceSubstring(String s, int left, int right) {
        Set<Character> charSet = getCharSet(s, left, right);
        for (int i = left; i < right; i++)
            if (!charSet.contains(Character.toLowerCase(s.charAt(i))) || !charSet.contains(Character.toUpperCase(s.charAt(i)))) {
                int[] prefix = longestNiceSubstring(s, left, i);
                int[] suffix = longestNiceSubstring(s, i + 1, right);
                return prefix[1] - prefix[0] >= suffix[1] - suffix[0] ? prefix : suffix;
            }
        return new int[]{left, right};
    }
    private Set<Character> getCharSet(String s, int left, int right) {
        Set<Character> charSet = new HashSet<Character>();
        for (int i = left; i < right; i++)
            charSet.add(s.charAt(i));
        return charSet;
    }

    //https://leetcode.com/discuss/interview-question/781066/Microsoft-or-OA-or-Codility
    public static int JumpFrog(int[] blocks) {
        int[] leftHigh = new int[blocks.length];
        int[] rightHigh = new int[blocks.length];

        for (int i = 1; i < blocks.length; i++)
        {
            if (blocks[i] <= blocks[i - 1])
            {
                leftHigh[i] = leftHigh[i - 1] + 1;
            }
        }
        for (int i = blocks.length - 2; i >= 0; i--)
        {
            if (blocks[i] <= blocks[i + 1])
            {
                rightHigh[i] = rightHigh[i + 1] + 1;
            }
        }
        int max = 0;
        for (int i = 0; i < blocks.length; i++)
        {
            max = Math.max(leftHigh[i] + rightHigh[i] + 1, max);
        }
        return max;
    }

    //Leetcode 687 - Longest Univalue Path
    int ans;
    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }
    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    //https://leetcode.com/discuss/interview-question/1768686/Microsoft-OA-Codility
    //Company revenue reallocate
    public static int revenue (int[] balance) {
        int sum = 0;
        int relocate = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i =0;i<balance.length;i++) {
            sum +=balance[i];
            if(balance[i]<0)
                pq.add(balance[i]);
            if(sum<0) {
                sum+=pq.poll()*-1;
                relocate++;
            }
        }
        return relocate;
    }
}

