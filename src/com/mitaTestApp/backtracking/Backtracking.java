package com.mitaTestApp.backtracking;
import javax.swing.*;
import java.util.*;

public class Backtracking {
    public static void main(String[] args) {
        int[] subsets = new int[]{1,2,3};
        subsets(subsets);
    }

    public static  List<List<Integer>> list;
    public static  List<String> result;
    public static  List<List<String>> listComb;
    public static  List<String[]> listResult;


    //Leetcode 78 - Subsets
    public static List<List<Integer>> subsets(int[] nums) {
        list= new ArrayList<>();
       list.add(new ArrayList<>());
       backtrackSubsets(nums,0, new ArrayList<Integer>());
       return list;
    }
    private static void backtrackSubsets(int[] nums, int start, ArrayList<Integer> integers) {
        if(start >= nums.length) return;
        for(int i =start;i<nums.length;i++){
            integers.add(nums[i]);
            list.add(new ArrayList(integers));
            backtrackSubsets(nums,i+1,integers);
            integers.remove(integers.size()-1);
        }
    }

    //Leetcode 90 - Subsets II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        list = new ArrayList<>();
        list.add(new ArrayList<>());
        Arrays.sort(nums);
        backtrackSubsetsII(nums,0,new ArrayList<Integer>());
        return list;

    }
    private void backtrackSubsetsII(int[] nums, int start, ArrayList<Integer> integers) {
        if(start >= nums.length) return;
        for(int i=start;i<nums.length;i++){
            if(i != start && nums[i] == nums[i-1]){
                continue;
            }
            integers.add(nums[i]);
            list.add(new ArrayList<>(integers));
            backtrackSubsetsII(nums, i+1, integers);
            integers.remove(integers.size()-1);
        }

    }

    //Leetcode 46- Permutations
    public List<List<Integer>> permute(int[] nums) {
        list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        BacktrackSubsetsIII(nums, new ArrayList<Integer>(), visited);
        return list;
    }
    private void BacktrackSubsetsIII(int[] nums, ArrayList<Integer> integers,boolean[] visited) {
        if(integers.size() == nums.length){
            list.add(new ArrayList(integers)); return;
        }
        for(int i = 0;i<nums.length;i++){
            if(visited[i] == true) continue;
            else{
                integers.add(nums[i]);
                visited[i]= true;
                BacktrackSubsetsIII(nums,integers,visited);
                integers.remove(integers.size()-1);
                visited[i]=false;
            }
        }

    }

    //Leetcode 47 - PermutationsII
    public List<List<Integer>> permuteUnique(int[] nums) {
        list = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        BacktrackSubsetsIV(nums, new ArrayList<Integer>(), visited);
        return list;
    }
    private void BacktrackSubsetsIV(int[] nums, ArrayList<Integer> integers, boolean[] visited) {

        if(integers.size() == nums.length){
            if(!list.contains(integers)) {
                list.add(new ArrayList(integers));
            }
        }
        for(int i = 0;i<nums.length;i++){
            if(visited[i] == true) continue;
            else{
                integers.add(nums[i]);
                visited[i]= true;
                BacktrackSubsetsIV(nums,integers,visited);
                integers.remove(integers.size()-1);
                visited[i]=false;
            }
        }


    }

    //Leetcode 39 - Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        list = new ArrayList<>();
        Backtrack(candidates,new ArrayList<Integer>(),target,0);
        return list;
    }
    private void Backtrack(int[] candidates, ArrayList<Integer> integers,int target,int start) {
        if(target ==0){
            list.add(new ArrayList(integers));
            return;
        }
        else if(target <=0) return;
        for(int i =start;i<candidates.length;i++){

            integers.add(candidates[i]);
            Backtrack(candidates,integers,target-candidates[i], i);
            integers.remove(integers.size()-1);
        }

    }

    //Leetcode 40  Combination SumII
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackcombinationSum2(candidates,target, 0, new ArrayList<>());
        return list;
    }
    private void backtrackcombinationSum2(int[] candidates, int target, int start, List<Integer> res) {
        if (target == 0) {
            //if (!list.contains(res)) {
                list.add(new ArrayList(res));
            //}
            return;
        } else if (target <= 0)
            return;
        int prev = -1;
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] == prev)
                continue;
            res.add(candidates[i]);
            backtrackcombinationSum2(candidates, target - candidates[i], i + 1, res);
            res.remove(res.size() - 1);
            prev = candidates[i];
        }
    }

    //Leetcode 22 - Generate Parentheses
    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        if(n ==0) return null;
        generateComb(n,n, "");
        return result;
    }
    private static void generateComb(int close, int open, String op){
        if(open ==0 && close ==0){
            result.add(op);
            return;
        }
        if(open!=0){
            String comb = op;
            comb = comb+ "(";
            generateComb(close,open-1, comb);
        }
        if(close > open){
            String comb = op;
            comb = comb+ ")";
            generateComb(close-1,open, comb);
        }
    }

    //Leetcode 131 - Palindrome Partitioning
    public List<List<String>> partition(String s) {
        listComb = new ArrayList<>();
        BacktrackPalindromes(s,0,new ArrayList<String>());
        return  listComb;
    }
    private void BacktrackPalindromes(String s, int start, ArrayList<String> strings) {
        if(start >= s.length()) {
            listComb.add(new ArrayList<>(strings));
            return;
        }
        for(int i =start;i<s.length();i++){
            if(Palindrome(s,start,i)){
                strings.add(s.substring(start, i+1));
                BacktrackPalindromes(s,i+1,strings);
                strings.remove(strings.size()-1);
            }
        }
    }
    private boolean Palindrome(String s, int low, int high) {
        while(low<high){
            if(s.charAt(low++)!= s.charAt(high--))
                return false;
        }
        return true;
    }

    //Leetcode 79 - Word Search
    public boolean exist(char[][] board, String word) {
        if(word.length() ==0) return false;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j] == word.charAt(0) && DFS(i,j,word,0,board)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean DFS(int i, int j, String word, int count, char[][]board){
        if(count == word.length())
            return true;
        if(i<0||j<0|| i>=board.length||j>=board[0].length|| board[i][j] != word.charAt(count))
            return false;
        Character temp = board[i][j];
        board[i][j] = '*';
        Boolean isFound = (DFS(i+1,j,word,count+1,board)
                ||DFS(i- 1,j,word,count+1,board)||DFS(i,j+1,word,count+1,board)||DFS(i,j-1,word,count+1,board));
        board[i][j] = temp;
        return isFound;
    }

    //Leetcode 17 - Letter Combination
    private Map<Character,String> map;
    private String phoneComb;
    public List<String> letterCombinations(String digits) {
        map = new HashMap<>();
        map.put('2',"abc"); map.put('3',"def");
        map.put('4',"ghi"); map.put('5',"jkl");
        map.put('6',"mno"); map.put('7',"pqrs");
        map.put('8',"tuv"); map.put('9',"wxyz");
        result = new ArrayList<>();
        if(digits.length() ==0 || digits == "1" || digits =="0") return result;
        phoneComb = digits;
        BackTrackPhoneComb(0, new StringBuilder());
        return result;
    }
    private void BackTrackPhoneComb (int start, StringBuilder stringBuilder) {
        if(start == phoneComb.length())
        {
            result.add(stringBuilder.toString());
            return;
        }
        char combination = phoneComb.charAt(start);
        String str = map.getOrDefault(combination,null);
        for(int i =0; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i));
            BackTrackPhoneComb(start + 1, stringBuilder);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

    }

    //Leetcode 254 - Factor Combinations
    public List<List<Integer>> getFactors(int n) {
        list = new ArrayList<>();
        backtrackFactor(n,2,new ArrayList<>());
        return list;
    }
    private void backtrackFactor(int n,int start, List<Integer> tempAns) {
        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                tempAns.add(i);
                tempAns.add(n / i);
                list.add(new ArrayList<Integer>(tempAns));
                tempAns.remove(tempAns.size() - 1);
                backtrackFactor(n / i, i, tempAns);
                tempAns.remove(tempAns.size() - 1);
            }
        }
    }

    //Leetcode 526- Beautiful Arrangement
    int count =0;
    public int countArrangement(int n) {
        boolean[] visited = new boolean[n+1];
        backtrackArrangement(n,1,visited);
        return count;

    }
    private void backtrackArrangement(int n,int start,boolean[] visited){
        if(start> n){
            count++;
        }
        for(int i=1;i<=n;i++){
            if(!visited[i] && (start%i ==0 || i%start ==0)){
                visited[i] = true;
                backtrackArrangement(n,start+1,visited);
                visited[i] = false;
            }
        }
    }

        //Leetcode 51 - N-Queens
    public List<List<String>> solveNQueens(int n) {
        listComb = new ArrayList<>();
        char[][] board = new char[n][n];

        for(int i =0;i<board.length;i++){
            for(int j =0;j<board[0].length;j++){
                board[i][j]='.';
            }
        }
        backtrack(board,0);
        return listComb;

    }
    private void backtrack(char[][] board, int start){
        if(start == board.length) {
            listComb.add(construct(board));
            return;
        }
        for(int i=0;i<board.length;i++){
            if(isValid(board,start,i)){
                board[start][i] = 'Q';
                backtrack( board, start + 1);
                board[start][i] = '.';
            }
        }
    }
    public List<String> construct(char[][] chess) {
        //change the array to list
        List<String> list1 = new ArrayList<>();
        for(int i = 0; i < chess.length; i++){
            list1.add(new String(chess[i]));
        }
        return list1;
    }
    public boolean isValid(char[][] chess, int row, int col) {
        for(int i = 0; i < row; i++) {
            if(chess[i][col] == 'Q'){
                return false;
            }
        }
        //judge whether we have queen on the right corner
        for(int i = row - 1, j = col + 1; i >= 0 && j < chess[i].length; i--, j++) {
            if(chess[i][j] == 'Q') {
                return false;
            }
        }
        //judge whether we have queen on the left corner
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if(chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    //Leetcode 52 - N queen


    //




}
