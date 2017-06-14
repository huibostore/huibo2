package com.example.nwidc.huibo;

/**
 * Created by hello on 2017/6/7.
 */

public class Person {
    private String id;
    private String name;
    private String age;
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setResult(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getAge(){
        return this.age;
    }
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name + "~年方：" + this.age;
    }
}
