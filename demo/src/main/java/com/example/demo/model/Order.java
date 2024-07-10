package com.example.demo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Order {
     public int orderId;
     public Customer customer;
     public List<Integer>productSerial ;
     public List<Product> products;

     public int customerId;
     public double totalPrice;
     public double shippingPrice;
     public Order(){}
     public Order(int orderId,int customerId){
          this.orderId=orderId;
          this.customerId=customerId;
     }
     public abstract void makeOrder();
     public abstract void cancelOrder(int orderId);

     public abstract void shipOrder();
     public abstract void cancelShipping(int orderId);





}
