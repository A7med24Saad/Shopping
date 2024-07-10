package com.example.demo.model;

public class Customer {
    public double balance;
    public int Password;
   public String email;
    public String address;
    int id;
    public String realname;
    public Customer (){}
    public Customer(double balance,int id,String realname,int password,String email,String address){

        this.balance=balance;
        this.id=id;
        this.realname=realname;
        this.address=address;
        this.email=email;
        this.Password=password;
    }

    public  void setNewbalance(double newbalance){
        this.balance=newbalance;
    }
    public double getBalance(){
        return this.balance;
    }
    public int getId(){
        return this.id;
    }
    public String getrealname(){return this.realname;}
}

