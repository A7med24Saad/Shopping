package com.example.demo.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NotificationManager{
    static List<Notification>notificationsQueue;
    NotificationManager(){
        notificationsQueue=new ArrayList<>();
    }
    public static void addNotification(Notification obj){
        notificationsQueue.add(obj);
    }
    public static void removeNotification(Notification obj){
        notificationsQueue.remove(obj);
    }
    public static List<Notification> printNotificationQueue(){
        return notificationsQueue;
    }
}
