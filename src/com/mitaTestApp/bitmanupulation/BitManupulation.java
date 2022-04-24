package com.mitaTestApp.bitmanupulation;

public class BitManupulation {
    public static void main(String[] args) {
        minimumOneBitOperations(6);
        //shiftOp(6);
    }

    public static int minimumOneBitOperations(int n) {
        int output = 0;
        while(n > 0) {
            output ^= n;
            n = n >> 1;
        }
        return output;
    }

    public static void shiftOp (int n){
        int a = n>>1; //0110
        System.out.println(a);
    }



}
