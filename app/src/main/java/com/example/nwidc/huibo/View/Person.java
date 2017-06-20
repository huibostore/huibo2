package com.example.nwidc.huibo.View;

/**
 * Created by hello on 2017/5/26.
 */

public class Person {
    private String name;
    private int age;
    private String address;
    private String no;
    private String teacher;

    public Person(String name, int age, String address, String no, String teacher) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.no = no;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
