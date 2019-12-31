package com.diousk.serializationsample.model;

public class MockDataJava {
    Long id;
    String name;
    int age = -1;
    Child child;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "MockDataJava{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", child=" + child +
                '}';
    }
}
