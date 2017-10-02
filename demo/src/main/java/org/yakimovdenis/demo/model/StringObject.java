package org.yakimovdenis.demo.model;

public class StringObject implements RequestTask {
    private String data;

    public StringObject(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StringObject{" +
                "data='" + data + '\'' +
                '}';
    }
}
