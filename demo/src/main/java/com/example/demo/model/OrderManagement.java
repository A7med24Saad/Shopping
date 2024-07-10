package com.example.demo.model;

public class OrderManagement {
    ShippingManagement shmanagement;
    Order order ;
    public OrderManagement(Order order){
        this.order=order;
        shmanagement=new ShippingManagement();
    }
    public void makeorder(){
        order.makeOrder();
        shmanagement.Shiporder(order);
        MakeOrderNotify notf=new MakeOrderNotify(order);
        NotificationManager.addNotification(notf);
        ShippingNotify shnotf=new ShippingNotify(order);
        NotificationManager.addNotification(shnotf);
    }
    public void cancelorder(int id){
        order.cancelOrder(id);
        shmanagement.CancelShipping(order,id);
        CancelOrderNotify notf=new CancelOrderNotify(order);
        NotificationManager.removeNotification(notf);
        CancelShippingNotify shnotf=new CancelShippingNotify(order);
        NotificationManager.removeNotification(shnotf);
    }
}
