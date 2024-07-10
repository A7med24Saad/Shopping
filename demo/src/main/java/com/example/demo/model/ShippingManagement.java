package com.example.demo.model;

public class ShippingManagement {

    public void Shiporder(Order order){
        order.shipOrder();
    }
    public void CancelShipping(Order order,int id){
        order.cancelShipping(id);
    }

}
