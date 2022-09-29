package com.example.validatingforminput;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PersonForm {

    @NotNull // Does not allow a null value, which is what Spring MVC generates if the entry is empty.
    @Size(min=2, max=30) //  Allows names between 2 and 30 characters long.
    private String name;

    @NotNull
    @Min(18)//: Does not allow the age to be less than 18.
    private Integer age;

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}
