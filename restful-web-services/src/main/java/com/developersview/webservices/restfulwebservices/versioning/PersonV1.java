package com.developersview.webservices.restfulwebservices.versioning;

/**
 * @author pranoy.chakraborty
 * @Date 15/06/2023
 */
public class PersonV1 {
    private String name;

    public PersonV1(String name) {
        this.name = name;
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
                '}';
    }
}
