package com.mitaTestApp.domain;

public class Person
{
    private String name;
    private Integer age;

    public Person(String name, Integer age)
    {
        this.name = name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public int hashCode(){
        final int prime =31;
        int result =1;
        result = prime*result+getAge();
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(this.getClass() != obj.getClass()) return false;
        Person other = (Person)obj;
        if(getName() != other.getName()) return false;
        return true;

    }


}