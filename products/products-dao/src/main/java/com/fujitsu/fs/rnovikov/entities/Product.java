package com.fujitsu.fs.rnovikov.entities;

public class Product {

    private int product_id;
    private String name;

    public Product(String name) {
        this.name = name;
    }




    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
