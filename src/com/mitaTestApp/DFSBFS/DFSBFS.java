package com.mitaTestApp.DFSBFS;

import javax.swing.*;
import java.lang.annotation.Target;
import java.util.*;


public class DFSBFS {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        //root.left.left = new TreeNode(null);
        //root.left.right = new TreeNode(8);
        // root.right.right = new TreeNode(5);
        //  root.right.left = new TreeNode(7);
        // root.right.right.left = new TreeNode(14);
        findSecondMinimumValue(root);
    }

    //Leetcode 100 - Same Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return true;
        if (p != null && q == null || q != null && p == null) return false;
        if (p.val != q.val) return false;
        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }

    //Leetcode 101 - Symmetric tree
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return isSimilar(root.left, root.right);
    }
    public static boolean isSimilar(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (right.val != left.val)
            return false;
        return isSimilar(left.left, right.right) && isSimilar(left.right, right.left);
    }

    //Leetcode 104 - Maximum Depth of Binary Tree
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    //Leetcode 110. Balanced Binary Tree
    private boolean result = true;
    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }
    public int maxDepthSol(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = maxDepthSol(node.left);
        int rightHeight = maxDepthSol(node.right);
        if (Math.abs(leftHeight - rightHeight) > 1) result = false;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    //Leetcode 111 - Minimum depth of binery tree
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 || right == 0) return 1 + left + right;
        else
            return 1 + Math.min(left, right);
    }

    //Leetcode 617. Merge Two Binary Trees
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

    //leetcode 543 - Diameter of a tree
    int diameter;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        diameter = 0;
        dfs(root);
        return diameter;
    }
    private int dfs(TreeNode root) {
        if (root == null)
            return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        diameter = Math.max(diameter, right + left);
        return Math.max(left, right) + 1;
    }

    //Leetcode 563. Binary Tree Tilt
    int tilt;
    public int findTilt(TreeNode root) {
        tilt = 0;
        if (root == null)
            return 0;
        calculateTilt(root);
        return tilt;
    }
    private int calculateTilt(TreeNode root) {
        if (root == null) return 0;
        int left = calculateTilt(root.left);
        int right = calculateTilt(root.right);
        int sum = Math.abs(left - right);
        tilt += sum;
        return root.val + left + right;


    }

    //Leetcode 226 - Invert Binary Tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }

    //Leetcode 700 -  Search in a Binary Search Tree
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val)
            return root;
        else if (root.val > val)
            return searchBST(root.left, val);
        else if (root.val < val)
            return searchBST(root.right, val);
        return root;
    }

    //Leetcode 653. Two Sum IV - Input is a BST
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet();
        return isTargetPresent(root, k, set);

    }
    private boolean isTargetPresent(TreeNode root, int k, Set<Integer> set) {
        if (root == null) return false;

        if (set.contains(k - root.val))
            return true;
        set.add(root.val);
        return isTargetPresent(root.left, k, set) || isTargetPresent(root.right, k, set);
    }

    //Leetcode 530- Minimum Absolute Difference in BST
    int output =Integer.MAX_VALUE;
    int prev =Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        if(root == null) return 0;
        helperII(root);
        return ans;
    }
    private void helperII (TreeNode root){
        if(root == null) return ;
        helperII(root.left);
        output = Math.min(output,Math.abs(prev-root.val));
        prev = root.val;
        helperII(root.right);
    }

    //Leetcode 938 - Range Sum BST
    int ans =0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) return 0;
        helper(root,low,high);
        return ans;
    }
    private void helper(TreeNode root, int low, int high){
        if(root!=null){
            if(root.val >= low && root.val <= high){
                ans+=root.val;
            }
            if(root.val> low){
                helper(root.left,low,high);
            }
            if(root.val < high){
                helper(root.right,low,high);
            }
        }

    }

    //Leetcode 450 - Delete Node in BST
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root== null) return null;

        if(root.val < key)
            root.right = deleteNode(root.right,key);
        else if(root.val>key)
            root.left = deleteNode(root.left,key);
        else{
            if(root.left!= null && root.right!=null){
                int max = findMax(root.left, Integer.MIN_VALUE);
                root.val = max;
                root.left = deleteNode(root.left,max);
                return root;
            }
            else if(root.right!= null)
                return root.right;
            else if(root.left != null)
                return root.left;
            else
                return null;
        }
        return root;
    }

    //Leetcode 669 - Trim a Binary Search Tree
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return null;
        if(root.val >= low  && root.val <= high ){
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
        else if(root.val <low){
            root.left = null;
            return trimBST(root.right, low, high);
        }
        else {
            root.right = null;
            return trimBST(root.left, low, high);
        }
    }

    //leetcode 701 - Insert in BST
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        } else
            root.left = insertIntoBST(root.left, val);
        return root;
    }

    //Leetcode 230- Kth Smallest Element in a BST
    List<Integer> list1;
    public int kthSmallest(TreeNode root, int k) {
        list1 = new ArrayList<Integer>();
        if(root ==null) return 0;
        helperIV(root);
        return list1.get(k-1);
    }
    private void helperIV(TreeNode root){
        if(root !=null){
            helperIV(root.left);
            list1.add(root.val);
            helperIV(root.right);
        }
    }

    //Leetcode 1305 - All Elements in Two Binary Search Trees
    List<Integer> res = new ArrayList<>();
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null)
            return null;
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        inorder(root1,list1);
        inorder(root2,list2);
        mergelist(list1,list2);
        return res;
    }
    private void inorder (TreeNode root, List<Integer>list){
        if(root!= null){
            inorder(root.left,list);
            list.add(root.val);
            inorder(root.right,list);
        }
    }
    private void mergelist(List<Integer>list1, List<Integer>list2){
        int i =0; int j =0;
        while(i < list1.size() && j<list2.size()){
            if(list1.get(i) < list2.get(j)){
                res.add(list1.get(i));
                i++;
            }
            else {
                res.add(list2.get(j));
                j++;
            }
        }
        while(i < list1.size()){
            res.add(list1.get(i));
            i++;
        }
        while(j < list2.size()){
            res.add(list2.get(j));
            j++;
        }

    }

    //Leetcode 297 - Serialize or Deserialize
    public String serialize(TreeNode root) {
        if (root == null) return "X";
        String right = serialize(root.right);
        String left = serialize(root.left);
        return root.val + "," + left + "," + right;
    }

    public TreeNode deserialize(String data) {
        if (data == null) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        TreeNode head = BuildTree(q);
        return head;
    }

    private TreeNode BuildTree(Queue<String> q) {
        if (q.size() <= 0) return null;
        String item = q.poll();
        if (item == "X") return null;
        TreeNode head = new TreeNode(Integer.parseInt(item));
        head.left = BuildTree(q);
        head.right = BuildTree(q);
        return head;
    }

    //Leetcode 272- Closest Binary Search Tree Value II
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        //List<Integer> list = new ArrayList<Integer>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> Math.abs(a - target) > Math.abs(b - target) ? -1 : 1);
        inorder(root, k, pq);
        return new ArrayList<>(pq);
    }

    private void inorder(TreeNode root, int k, PriorityQueue<Integer> pq) {
        if (root == null) return;
        inorder(root.left, k, pq);
        pq.add(root.val);
        if (pq.size() > k) {
            pq.remove();
        }
        inorder(root.right, k, pq);
    }



    private int findMax(TreeNode root, int max){
        while(root!=null){
            max = Math.max(max,root.val);
            root = root.right;
        }
        return max;
    }



    //Leetcode 1469 - Find All The Lonely Nodes
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        calculatenode(root, list);
        return list;
    }

    private void calculatenode(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        if (root.left != null && root.right == null) list.add(root.left.val);
        if (root.left == null && root.right != null) list.add(root.right.val);
        calculatenode(root.left, list);
        calculatenode(root.right, list);

    }




    //Leetcode 671 - 2 smallest element in Tree
    public static PriorityQueue<Integer> pq;

    public static int findSecondMinimumValue(TreeNode root) {
        pq = new PriorityQueue<Integer>((a, b) -> a - b);
        if (root == null) return -1;
        int num = 0;
        helper(root);
        int k = 2;
        while (k != 0 && !pq.isEmpty()) {
            num = pq.poll();
            k--;
            // System.out.println(pq.poll());
        }
        if (k != 0 && pq.isEmpty()) return -1;
        else
            return num;
    }

    private static void helper(TreeNode root) {
        if (root != null) {
            helper(root.left);
            if (!pq.isEmpty() && pq.peek() == root.val) {
                pq.poll();
            }
            pq.add(root.val);
            while (pq.size() > 2) {
                pq.poll();
            }
            helper(root.right);
        }
    }

    //2nd approach
    public static int findSecondMinimumI(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1;
        int left = root.left.val;
        int right = root.right.val;

        if (root.val == root.left.val) left = findSecondMinimumI(root.left);
        if (root.val == root.right.val) right = findSecondMinimumI(root.right);

        if (left == -1 && right == -1) return -1;
        if (left != -1) return left;
        else
            return right;


    }

    //Leetcode 98 - valid BST
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValid(TreeNode root, long min, long max) {
        if (root == null) return true;

        if (root.val >= max || root.val <= min)
            return false;

        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    //ListNode 366. Find Leaves of Binary Tree
    List<List<Integer>> list;

    public List<List<Integer>> findLeaves(TreeNode root) {
        list = new ArrayList<>();
        if (root == null) return list;
        helperLeaves(root);
        return list;
    }

    private int helperLeaves(TreeNode root) {
        if (root == null) return -1;
        int left = helperLeaves(root.left);
        int right = helperLeaves(root.right);
        int height = 1 + Math.max(left, right);
        if (list.size() == height)
            list.add(new ArrayList<>());
        this.list.get(height).add(root.val);
        return height;
    }

    //Leetcode 200 - Number of Islands
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    DFS(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void DFS(int i, int j, char[][] grid) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0 || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        DFS(i + 1, j, grid);
        DFS(i - 1, j, grid);
        DFS(i, j + 1, grid);
        DFS(i, j - 1, grid);
    }

    //Leetcode 339 - Nested List Weight Sum
    public interface NestedInteger {
        //NestedInteger();
        // public NestedInteger(int value);
        public boolean isInteger();

        public Integer getInteger();

        public void add(NestedInteger ni);

        public List<NestedInteger> getList();
    }

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int total = 0;
        for (NestedInteger newlist : nestedList) {
            if (newlist.isInteger()) {
                total += newlist.getInteger() * depth;
            } else {
                total += dfs(newlist.getList(), depth + 1);
            }

        }
        return total;

    }

    //Leetcode 364 -  Nested List Weight Sum II
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxDep = findMaxDepth(nestedList);
        return dfs(nestedList, 1, maxDep);
    }

    private int dfs(List<NestedInteger> nestedList, int depth, int max) {
        int result = 0;
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                result += nest.getInteger() * (max - depth + 1);
            } else {
                result += dfs(nest.getList(), depth + 1, max);
            }
        }
        return result;
    }

    private int findMaxDepth(List<NestedInteger> nestedList) {
        int max = 1;

        for (NestedInteger nested : nestedList) {
            if (!nested.isInteger()) {
                max = Math.max(max, 1 + findMaxDepth(nested.getList()));
            }
        }
        return max;
    }

    //Leetcode 695 - Max Area of island
    public int maxAreaOfIsland(int[][] grid) {
        int area = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                area = Math.max(DFS(grid, i, j), area);
            }
        }
        return area;
    }

    private int DFS(int[][] grid, int i, int j) {
        if (i >= grid.length || i < 0 || j >= grid[i].length || j < 0 || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = -1;
        return (1 + DFS(grid, i + 1, j) + DFS(grid, i - 1, j) + DFS(grid, i, j + 1) + DFS(grid, i, j - 1));
    }

    //Leetcode 212 - Word Search II
    public class TrieNode {
        public boolean isWord = false;
        public TrieNode[] child = new TrieNode[26];

        public TrieNode() {

        }
    }

    TrieNode root = new TrieNode();
    boolean[][] flag;

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        flag = new boolean[board.length][board[0].length];
        addToTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.child[board[i][j] - 'a'] != null) {
                    search(board, i, j, root, "", result);
                }
            }
        }
        return new LinkedList<>(result);
    }

    private void addToTrie(String[] words) {
        for (String word : words) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.child[ch - 'a'] == null) {
                    node.child[ch - 'a'] = new TrieNode();
                }
                node = node.child[ch - 'a'];
            }
            node.isWord = true;
        }
    }

    private void search(char[][] board, int i, int j, TrieNode node, String word, Set<String> result) {

        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || flag[i][j] || node.child[board[i][j] - 'a'] == null) {
            return;
        }
        flag[i][j] = true;
        node = node.child[board[i][j] - 'a'];
        if (node.isWord) {
            result.add(word + board[i][j]);
        }
        search(board, i, j + 1, node, word + board[i][j], result);
        search(board, i, j - 1, node, word + board[i][j], result);
        search(board, i + 1, j, node, word + board[i][j], result);
        search(board, i - 1, j, node, word + board[i][j], result);
        flag[i][j] = false;
    }

    //Leetcode 236 - Lowest Common Ancestor of a Binary Tree
    public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root)
            return root;
        TreeNode left = lowestCommonAncestorI(root.left, p, q);
        TreeNode right = lowestCommonAncestorI(root.right, p, q);
        if (left == p && right == q || left == q && right == p)
            return root;
        if (left == null) return right;
        else
            return left;
    }

    //Leetcode 235 - Lowest Common Ancestor of a Binary Search Tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;
        while (curr != null) {
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            } else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            } else
                return curr;
        }
        return null;
    }

    //1644
    TreeNode node;

    public TreeNode lowestCommonAncestorII(TreeNode root, TreeNode p, TreeNode q) {
        node = new TreeNode();
        isCommonAncestor(root, p, q, node);
        return node;
    }

    private boolean isCommonAncestor(TreeNode root, TreeNode p, TreeNode q, TreeNode node) {
        if (root == null || p == root || q == root)
            return false;
        boolean curr = root == p || root == q;
        boolean left = isCommonAncestor(root.left, p, q, node);
        boolean right = isCommonAncestor(root.right, p, q, node);
        if (curr && (left || right) || (left && right))
            this.node = root;
        return curr || left || right;
    }

    //Leetcode 655 - Print Binary tree
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> list = new ArrayList<>();
        if (root == null)
            return list;
        int height = calculateHeight(root);
        int row = height + 1;
        int col = (int) Math.pow(2, row) - 1;
        for (int i = 0; i < row; i++) {
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                temp.add("");
            }
            list.add(temp);
        }
        print(list, root, 0, col - 1, 0);
        return list;
    }

    private void print(List<List<String>> list, TreeNode root, int row, int col, int currrow) {
        if (root == null)
            return;
        int mid = row + (col - row) / 2;
        list.get(currrow).set(mid, String.valueOf(root.val));
        print(list, root.left, row, mid - 1, currrow + 1);
        print(list, root.right, mid + 1, col, currrow + 1);

    }

    private int calculateHeight(TreeNode root) {
        if (root == null)
            return 0;
        int left = calculateHeight(root.left);
        int right = calculateHeight(root.right);
        return 1 + Math.max(left, right);
    }

    //leetcode 124 - Path sum
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateSum(root);
        return max;
    }

    private int calculateSum(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(calculateSum(root.left), 0);
        int right = Math.max(calculateSum(root.right), 0);
        int pathSum = root.val + left + right;
        max = Math.max(max, pathSum);
        return root.val + Math.max(left, right);
    }

    //leetcode 515
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            list.add(max);

        }
        return list;
    }

    //leetcode 261 - Graph valid tree
    List<List<Integer>> adj;
    Set<Integer> set;

    public boolean validTree(int n, int[][] edges) {
        if (n == 0) return false;
        if (edges.length != n - 1) return false;
        adj = new ArrayList<>();
        set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        dfs(0);
        return (set.size() == n);
    }

    public void dfs(int root) {
        if (set.contains(root)) return;
        set.add(root);
        for (int node : adj.get(root)) {
            dfs(node);
        }
    }







    //Leetcode 2196 - Create Binary Tree From Descriptions
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int[] arr : descriptions) {
            int parent = arr[0];
            int child = arr[1];
            boolean isleft = (arr[2] == 1) ? true : false;
            if (!map.containsKey(parent))
                map.put(parent, new TreeNode(parent));
            TreeNode parentNode = map.get(parent);
            if (!map.containsKey(child))
                map.put(child, new TreeNode(child));
            TreeNode childNode = map.get(child);
            if (isleft) {
                parentNode.left = childNode;
            } else
                parentNode.right = childNode;
            set.add(child);
        }
        for (int[] desc : descriptions) {
            if (!set.contains(desc[0]))
                return map.get(desc[0]);
        }
        return null;
    }



    //Leetcode 979. Distribute Coins in Binary Tree
    int count =0;
    public int distributeCoins(TreeNode root) {
        if(root == null) return 0;
        dfsI(root);
        return count;
    }
    private int dfsI(TreeNode root){
        if(root == null) return 0;
        int left = dfsI(root.left);
        int right = dfsI(root.right);
        count += Math.abs(right)+ Math.abs(left);
        return left+root.val+right-1;
    }
}




