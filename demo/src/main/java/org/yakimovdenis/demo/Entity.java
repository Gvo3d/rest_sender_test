package org.yakimovdenis.demo;

public class Entity {
    private String data;

    public Entity(String data) {
        this.data = data;
    }

    public Entity() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "data='" + data + '\'' +
                '}';
    }
}
