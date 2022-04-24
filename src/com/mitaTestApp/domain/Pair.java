package com.mitaTestApp.domain;

public class Pair {
    private  int roll;
    private int age;

    public Pair(int roll,int age){ this.roll =roll; this.age=age;}

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
