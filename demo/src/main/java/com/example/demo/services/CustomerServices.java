package com.example.demo.services;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static com.example.demo.Common.customers;

@Service
public class CustomerServices {

    public static List<Customer> customers = new ArrayList<>(Arrays.asList());

    public Boolean addcustomer(Customer c) {
        for(Customer customer:customers){
            if(customer.getId()==c.getId()){
                return false;
            }
        }
        return customers.add(c);
    }

    public static Customer getcustomerByid(int id) {

        for(Customer c:customers){
            if(c.getId()==id){
                return c;
            }
        }

        return null;

    }

    public List<Customer> getCustomerss(){
        return customers;
    }
    public static double updatebalance(int id,double price){
        Customer c = getcustomerByid(id);
        if (c != null) {
            c.setNewbalance(c.getBalance()-price);
            return c.getBalance();
        }
        else {
            return -1;
        }
    }
}
