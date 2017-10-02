package org.yakimovdenis.demo.model;

public class IntObject implements RequestTask {
    private Integer data;

    public IntObject(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "IntObject{" +
                "data=" + data +
                '}';
    }
}
