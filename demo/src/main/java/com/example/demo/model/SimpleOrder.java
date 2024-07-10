package com.example.demo.model;
import com.example.demo.services.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SimpleOrder extends Order {

    SimpleOrder(List<Integer> productSerial,int customerId,int orderId){
        this.productSerial=productSerial;
        this.customerId=customerId;
        this.customer=CustomerServices.getcustomerByid(customerId);
        this.orderId=orderId;
        products=new ArrayList<>();
        addProducts();
    }
    private void addProducts(){
        for (int i=0; i<productSerial.size(); i++){
            Product temp=Inventory.getProductBySerial(productSerial.get(i));
            if (temp!=null) {
                products.add(temp);
            }
        }
    }
    @Override
    public void makeOrder() {
        for(int i=0;i<products.size();i++){
            totalPrice+=products.get(i).price;
        }
        CustomerServices.updatebalance(customerId,totalPrice);
        for (int i=0; i<products.size(); i++){
            if (Objects.equals(products.get(i).category, "Books")){
                Inventory.decrementRemainingParts("Books");
            }
            else if (Objects.equals(products.get(i).category, "Devices")){
                Inventory.decrementRemainingParts("Devices");
            }
            else if (Objects.equals(products.get(i).category, "Games")){
                Inventory.decrementRemainingParts("Games");
            }
        }
        OrderServices.addorder(this);
    }

    @Override
    public void cancelOrder(int orderid) {
        Order order=OrderServices.getorderbyId(orderid);
        order.customer.balance+=order.totalPrice;
        for (int i=0; i<products.size(); i++){
            if (Objects.equals(products.get(i).category, "Books")){
                Inventory.incrementRemainingParts("Books");
            }
            else if (Objects.equals(products.get(i).category, "Devices")){
                Inventory.incrementRemainingParts("Devices");
            }
            else if (Objects.equals(products.get(i).category, "Games")){
                Inventory.incrementRemainingParts("Games");
            }
        }
        OrderServices.cancelorder(order);
    }
    @Override
    public void shipOrder() {
        shippingPrice=0.1*totalPrice;
        totalPrice+=shippingPrice;
        CustomerServices.updatebalance(customerId,totalPrice);
    }

    @Override
    public void cancelShipping(int orderid) {
        Order order=OrderServices.getorderbyId(orderid);
        totalPrice-=shippingPrice;
        order.customer.balance+=shippingPrice;
    }
}