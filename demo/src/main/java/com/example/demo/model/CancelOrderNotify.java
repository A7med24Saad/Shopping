package com.example.demo.model;
import com.example.demo.model.Order;

import java.time.LocalTime;

public class CancelOrderNotify extends Notification{
    CancelOrderNotify(Order order){
        customer=order.customer;
        LocalTime currentTime = LocalTime.now();
        time=currentTime;
        TypeOfMessage="Email";
        StringBuilder output = new StringBuilder();
        output.append("Dear " + customer.realname + " your booking of the following items:");
        output.append("\n");
        for (int i=0; i<order.products.size(); i++){
            output.append(order.products.get(i).name);
            output.append("\n");
        }
        output.append("has been canceled");
        Text= output.toString();
        NotificationManager.addNotification(this);
    }
}
