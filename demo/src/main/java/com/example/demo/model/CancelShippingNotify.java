package com.example.demo.model;

import java.time.LocalTime;

public class CancelShippingNotify extends Notification{
    CancelShippingNotify(Order order){
        customer=order.customer;
        LocalTime currentTime = LocalTime.now();
        time=currentTime;
        TypeOfMessage="SMS";
        StringBuilder output = new StringBuilder();
        output.append("Dear " + customer.realname + " the shipping for your order of the following items:");
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
