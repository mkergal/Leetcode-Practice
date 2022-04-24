package com.mitaTestApp.dp;

public class StringDP {
    public static void main(String[] args) {

        longestCommonSubsequence("intention", "execution");
    }

    //Leetcode 1143 - LCS
    public int LCSRecursion(String text1, String text2) {
        int m = text1.length();
        int n= text2.length();
        if(m ==0 || n==0)
            return 0;
        return LCSRecur(text1,text2,m,n);
    }
    public int LCSRecur(String text1, String text2, int m, int n) {
        if(m==0 || n==0)
            return 0;

        if(text1.charAt(m-1) == text2.charAt(n-1)){
            return 1+ LCSRecur(text1, text2, m-1,n-1);
        }
        else{
            return Math.max(LCSRecur(text1, text2, m-1,n), LCSRecur(text1, text2, m,n-1));
        }
    }
    //DP
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
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
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    t[i][j] = 1 + t[i - 1][j - 1];
                else
                    t[i][j] = Math.max(t[i][j - 1], t[i - 1][j]);
            }
        }
        System.out.println("t[m][n] = " + t[m][n]);
        return t[m][n];

    }


}