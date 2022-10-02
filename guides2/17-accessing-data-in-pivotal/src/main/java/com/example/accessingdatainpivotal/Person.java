package com.example.accessingdatainpivotal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Region(value = "People")
public class Person {

    @Id
    private final String name;

    private final int age;

    @PersistenceConstructor
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s is %d years old", getName(), getAge());
    }

}
