package com.mitaTestApp.DFSBFS;
import java.util.*;

public class TreeTraversal {
    //No fo nodes at a level is 2^l
    //max nodes 2^h-1
    //minimum possible height -> Log2 (n+1)
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(15);
        root.right.left = new TreeNode(12);
        root.right.right.left = new TreeNode(14);
        printRighSide(root);
    }

    //Leetcode 145. Binary Tree Postorder Traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list =new ArrayList<>();
        helper(root,list);
        return list;
    }
    private void helper(TreeNode root,List<Integer> res){
        if(root!=null){
            helper(root.left,res);
            helper(root.right,res);
            res.add(root.val);
        }
    }
    private List<Integer> postorderT(TreeNode root){
        List<Integer>lst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;

        while (p != null || stack.size() > 0)
        {
            if (p != null)
            {
                lst.add(0,p.val);
                stack.push(p);
                p = p.right;
            }
            else
            {
                TreeNode node = stack.pop();
                p = node.left;
            }
        }
        return lst;
    }

    //Leetcode 94 - Inorder Traversal Recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list =new ArrayList<>();
        helperIn(root,list);
        return list;
    }
    private void helperIn(TreeNode root,List<Integer> res){
        if(root!=null){
            helperIn(root.left,res);
            res.add(root.val);
            helperIn(root.right,res);
        }
    }
    //liner using stack
    private List<Integer> inorderT(TreeNode node){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while(p!=null && !stack.isEmpty()){
            if(p!=null){
                stack.push(p);
                p =p.left;
            }
            else {
                TreeNode node1 = stack.pop();
                list.add(node1.val);
                p = node1.right;
            }
        }
        return list;
    }

    //Leetcode 144 - PreOrder Traversal
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list =new ArrayList<>();
        helperPre(root,list);
        return list;
    }
    private void helperPre(TreeNode root,List<Integer> res){
        if(root!=null){
            res.add(root.val);
            helperPre(root.left,res);
            helperPre(root.right,res);
        }
    }
    private List<Integer> preorderT(TreeNode node){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = node;
        while(p!=null && !stack.isEmpty()){
            if(p!=null){
                list.add(p.val);
                stack.push(p);
                p =p.left;
            }
            else {
                TreeNode node1 = stack.pop();
                p = node1.right;
            }
        }
        return list;
    }

    //Leetcode 102 - level order traversal
    List<List<Integer>> list;
    public List<List<Integer>> levelOrder(TreeNode root) {
        list= new ArrayList<>();
        if(root == null) return list;
        helper(root,0);
        return list;
    }
    private void helper(TreeNode root, int levels){
        if(list.size() == levels)
            list.add(new ArrayList<>());

        list.get(levels).add(root.val);

        if(root.left!=null)
            helper(root.left,levels+1);
        if(root.right!=null)
            helper(root.right,levels+1);
    }
    public List<List<Integer>> levelT (TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> listLevels = new ArrayList<>();
            while (size--> 0){
                TreeNode node = queue.poll();
                listLevels.add(node.val);
                if(node.left!= null) queue.add(node.left);
                if(node.right!= null) queue.add(node.right);
            }
            list.add(listLevels);
        }
        return list;


    }

    //Leetcode 103. Binary Tree Zigzag Level Order Traversal
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        list =new ArrayList<>();
        if(root== null) return list;
        helperZigOrder(root,0);
        return list;
    }
    private void helperZigOrder(TreeNode root,int level){
        if(level == list.size())
            list.add(new ArrayList<>());
        if(level%2 ==0){
            list.get(level).add(root.val);
        }
        else{
            list.get(level).add(0,root.val);
        }
        if(root.left!=null)
            helperZigOrder(root.left,level+1);
        if(root.right!=null)
            helperZigOrder(root.right,level+1);
    }
    public List<List<Integer>> ZigZaglevelT (TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level =0;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> listLevels = new ArrayList<>();

            while (size--> 0){
                TreeNode node = queue.poll();
                listLevels.add(node.val);
                if(node.left!= null) queue.add(node.left);
                if(node.right!= null) queue.add(node.right);
            }
            if(level%2 ==0)
                list.add(listLevels);
            else
                list.add(0,listLevels);
            level++;
        }
        return list;


    }

    //Print left tree
    static int maxlevel =0;
    static  List<Integer> listLeft;
    public static List<Integer> printleftSide(TreeNode root){
        listLeft = new ArrayList<>();
        if(root == null)
            return null;
        dfsLeft(root,1);
        for (int num:listLeft) {
            System.out.println(num);
        }
        return listLeft;
    }
    private static void dfsLeft (TreeNode root, int level){
        if(root == null)
            return ;

        if(maxlevel < level){
            maxlevel = level;
            listLeft.add(root.val);

        }
        dfsLeft(root.left, level+1);
        dfsLeft(root.right, level+1);
    }

    //Leetcode 199 - Print right side
    public static List<Integer> printRighSide(TreeNode root){
        listLeft = new ArrayList<>();
        if(root == null)
            return null;
        dfsRight(root,1);
        for (int num:listLeft) {
            System.out.println(num);
        }
        return listLeft;
    }
    private static void dfsRight (TreeNode root, int level){
        if(root == null)
            return ;

        if(maxlevel < level){
            maxlevel = level;
            listLeft.add(root.val);

        }
        dfsRight(root.right, level+1);
        dfsRight(root.left, level+1);

    }


    public static List<Integer> PostorderTraversalI(TreeNode root) {
        List<Integer>lst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;

        while (p != null || stack.size() > 0)
        {
            if (p != null)
            {
                lst.add(0,p.val);
                stack.push(p);
                p = p.right;
            }
            else
            {
                TreeNode node = stack.pop();
                p = node.left;
            }
        }
        return lst;
    }


}
