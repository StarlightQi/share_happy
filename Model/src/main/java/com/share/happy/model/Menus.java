package com.share.happy.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Menus {
    private String id;
    private String authName;
    private String path;
    private ArrayList<Menus> children;
    public Menus() {
    }

    public Menus(String id, String authName, String path, ArrayList<Menus> children) {
        this.id = id;
        this.authName = authName;
        this.path = path;
        this.children = children;
    }
}