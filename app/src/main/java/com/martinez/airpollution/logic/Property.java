package com.martinez.airpollution.logic;

public class Property {
    private String id;
    private String value;

    public Property(String id, String value){
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
