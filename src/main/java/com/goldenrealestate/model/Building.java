package com.goldenrealestate.model;

import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * User: Poyan Gerami
 * Email: poyan.gerami@eniro.com
 * Date: 16/06/16
 */
public class Building implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
