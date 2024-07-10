package com.example.demo.model;
import com.example.demo.model.Order;
import com.example.demo.services.*;
import com.example.demo.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compoundorder extends Order {
    Map<Integer, List<Product>> mp = new HashMap<>();
    Compoundorder(Map<Integer, List<Product>> mp){
        this.mp=mp;
    }
    @Override
    public void makeOrder() {
        for (Map.Entry<Integer, List<Product>> entry : mp.entrySet()) {
            int id= entry.getKey();
            Customer currentCustomer=CustomerServices.getcustomerByid(id);
            double currentCustomerTotalPrice=0;
            List <Product>currentCustomerProducts=mp.get(id);
            for (int i=0; i<currentCustomerProducts.size(); i++){
                currentCustomerTotalPrice+=currentCustomerProducts.get(i).price;
                totalPrice+=currentCustomerProducts.get(i).price;
            }
            CustomerServices.updatebalance(id,currentCustomerTotalPrice);
        }
        OrderServices.addorder(this);
    }
    @Override
    public void cancelOrder(int orderId) {
        Order order=OrderServices.getorderbyId(orderId);
        for (Map.Entry<Integer, List<Product>> entry : mp.entrySet()) {
            int id= entry.getKey();
            Customer currentCustomer=CustomerServices.getcustomerByid(id);
            double currentCustomerTotalPrice=0;
            List <Product>currentCustomerProducts=mp.get(id);
            for (int i=0; i<currentCustomerProducts.size(); i++){
                currentCustomerTotalPrice+=currentCustomerProducts.get(i).price;
                totalPrice-=currentCustomerProducts.get(i).price;
            }
            currentCustomer.balance+=currentCustomerTotalPrice;
        }
        OrderServices.cancelorder(order);
    }

    @Override
    public void shipOrder() {
        for (Map.Entry<Integer, List<Product>> entry : mp.entrySet()) {
            for (int i=0; i<entry.getValue().size(); i++){
                shippingPrice+=0.1*entry.getValue().get(i).price;
            }
        }
        totalPrice+=shippingPrice;
        double shippingAmountForEachCustomer=shippingPrice/mp.size();
        for (Map.Entry<Integer, List<Product>> entry : mp.entrySet()) {
            int id= entry.getKey();
            Customer currentCustomer=CustomerServices.getcustomerByid(id);
            currentCustomer.balance-=shippingAmountForEachCustomer;
        }
    }
    @Override
    public void cancelShipping(int orderId) {
        Order order=OrderServices.getorderbyId(orderId);
        totalPrice-=shippingPrice;
        double shippingAmountForEachCustomer=shippingPrice/mp.size();
        for (Map.Entry<Integer, List<Product>> entry : mp.entrySet()) {
            int id= entry.getKey();
            Customer currentCustomer=CustomerServices.getcustomerByid(id);
            currentCustomer.balance+=shippingAmountForEachCustomer;
        }
    }
}
