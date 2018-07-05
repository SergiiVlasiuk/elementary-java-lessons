package org.vl.example.dto;

import org.springframework.beans.factory.annotation.Lookup;
import org.vl.example.lookup.SomethingUseful;

public class LookupDto {

    private String name;
    private Integer age;

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

    @Lookup
    public SomethingUseful getMyHelper() {
        return () -> "lookupFromDto";
    }

}
