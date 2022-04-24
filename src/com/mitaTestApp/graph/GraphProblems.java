package com.mitaTestApp.graph;
import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {

    }
    static int[] topoSort(ArrayList<ArrayList<Integer>> adj, int n) {
        if(n ==0) return null;
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        for(int i =0;i< n;i++){
            if(visited[i] == false){
                visited[i] = true;
                topocal(visited,i,stack,adj);
            }
        }
        int[] ans = new int[n];int k=0;
        while(!stack.isEmpty()){
            ans[k++] = stack.pop();
        }
        return ans;
    }
    private static void topocal(boolean[] visited, int i, Stack<Integer> stack, ArrayList<ArrayList<Integer>> adj) {
        for(int x : adj.get(i)){
            visited[x] = true;
            topocal(visited,x,stack,adj);
        }
        stack.push(i);
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer,List<Integer>> map =new HashMap<>();

        for(int[] relation:prerequisites ){
            if(map.containsKey(relation[1])){
                map.get(relation[1]).add(relation[0]);
            }
            else
                map.put(relation[1],new ArrayList<Integer>(relation[0]));
        }
        boolean[] checked = new boolean[numCourses];
        boolean[] path = new boolean[numCourses];

        for(int i =0;i<numCourses;i++){
            if(isCyclic(map,checked, path,i))
                return false;
        }

        return true;

    }

    private boolean isCyclic(Map<Integer, List<Integer>> map, boolean[] checked, boolean[] path, int curr) {

        if(checked[curr]) return false;
        if(path[curr]) return true;
        if(!map.containsKey(curr))
            return false;

        path[curr] = true;
        boolean result = false;
        for(int num :map.get(curr)){
            result = isCyclic(map,checked,path,num);
            if(result){
                break;
            }
        }
        
        path[curr]= false;
        checked[curr] = true;
        return result;

    }

    //Leetcdoe 323 - No of Connected componensts
    public int countComponents(int n, int[][] edges) {
        if (n <= 1)
            return n;
        int count = 0;

        int[] visited = new int[n];
        List<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++)
            adj[i] =  new ArrayList();

        for (int[] a : edges)
        {
            adj[a[0]].add(a[1]);
            adj[a[1]].add(a[1]);
        }
        for (int i = 0; i < n; i++)
        {
            if (visited[i] ==0)
            {
                DFS(adj, visited, i);
                count++;
            }
        }
        return count;
    }
    private static void DFS(List<Integer>[] adjList, int[] visited, int startNode) {
        visited[startNode] = 1;
        for (int j : adjList[startNode])
        {
            if (visited[j] == 0)
            {
                DFS(adjList, visited, adjList[startNode].get(j));
            }
        }
    }
    

    //Leetcode 212 -  Word search 2
    public class TrieNode
    {
        public boolean isWord = false;
        public TrieNode[] child = new TrieNode[26];
        public TrieNode(){}
    }
    Boolean[][] flag;
    TrieNode root = new TrieNode();
    public List<String> FindWords(char[][] board, String[] words) {
        HashSet<String> hset = new HashSet<String>();
        flag = new Boolean[board.length][board[0].length];
        AddToTrie(words);
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (root.child[board[i][j] - 'a'] != null)
                    Search(board, i, j, hset, root, "");
            }
        }
        return new LinkedList<>(hset);
    }
    private void Search(char[][] board, int i, int j, HashSet<String> hset, TrieNode node, String word) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || flag[i][j] || node.child[board[i][j] - 'a'] == null)
        return;
        flag[i][j] = true;
        node = node.child[board[i][j] - 'a'];
        if (node.isWord)
            hset.add(word + board[i][j]);
        Search(board, i + 1, j, hset, node, word + board[i][j]);
        Search(board, i - 1, j, hset, node, word + board[i][j]);
        Search(board, i, j + 1, hset, node, word + board[i][j]);
        Search(board, i, j - 1, hset, node, word + board[i][j]);
        flag[i][j] = false;
    }
    private void AddToTrie(String[] words) {
        for(String word : words)
        {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++)
            {
                char ch = word.charAt(i);
                if (node.child[ch - 'a'] == null)
                {
                    node.child[ch - 'a'] = new TrieNode();
                }
                node = node.child[ch - 'a'];
            }
            node.isWord = true;
        }
    }
}
