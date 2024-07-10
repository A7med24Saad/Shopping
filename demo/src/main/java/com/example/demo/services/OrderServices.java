package com.example.demo.services;

import com.example.demo.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class OrderServices {
    public static List<Order> orders = new ArrayList<>(Arrays.asList());
    public static void addorder(Order order){
        orders.add(order);

    }
    //remove order from list of orders
    public static void cancelorder(Order order){
        orders.remove(order);
    }
    public static Order getorderbyId(int orderid){
        for(Order order:orders){
            if(order.orderId==orderid){
                return order;
            }
        }
        return  null;
    }
}
