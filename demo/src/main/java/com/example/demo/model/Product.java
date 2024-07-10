package com.example.demo.model;

public class Product {
    int serial;
    String name,vendor,category;
    double price;
    public Product(){}
    public Product(int serial, String name, String vendor, String category, double price){
        this.name=name;
        this.serial = serial;
        this.price=price;
        this.vendor =vendor;
        this.category=category;
    }
    public int getserial(){
        return this.serial;
    }
    public double getprice(){
        return this.price;
    }
    public String getname(){
        return this.name;
    }
    public String getvendor(){

        return this.vendor;
    }
    public String getCategory(){

        return this.category;
    }
}
