package com.goldenrealestate.model;

import java.io.Serializable;

/**
 * User: Poyan Gerami
 * Email: poyan.gerami@eniro.com
 * Date: 16/06/16
 */
public class Employee implements Serializable {
    private String name;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
