package com.example.demo.controller;
import com.example.demo.model.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/c")
public  class CustomerController {


    @Autowired
    public Inventory inventory ;

    @Autowired
    public CustomerServices customerServices;
    @Autowired
    public OrderServices orderServices;
    //Add products
    @PostMapping("/add-products")
    public  String addproduct(@RequestBody Product p){
        inventory.add(p);
        return "success";
    }
    //get All Products
    @GetMapping("/get-all-products")
    public List<Product> getAllproducts(){
        return inventory.getAllProducts();
    }
    //Get Product by name and category
//    @GetMapping("/get-product-by-nameAndCategory/{category}/{name}")
//    public Product getproductByNameCategory(@PathVariable String category,@PathVariable String name){
//        return inventory.getProductByCategoryAndName(category,name);
//    }

    //get products by category
   @GetMapping("/get-allProducts-in-category/{category}")
    public List <Product> getproductByCategory(@PathVariable String category){
       return inventory.getAllProductsInCategory(category);
    }
    //get remaining
    @GetMapping ("/get-remaining-parts-in-a-category/{category}")
    public int getRemainingParts(@PathVariable String category){
        return inventory.getRemainingParts(category);
    }
    //add Customer
    @PostMapping("/add-customers")
    public Customer addcustomer(@RequestBody Customer customer){
        if(customerServices.addcustomer(customer))
            return customer;
        else
            return null;
    }
    //get all customers
    @GetMapping("/get-customers")
    public List<Customer> getcustomers(){
        return customerServices.getCustomerss();
    }
    //get customer by id
    @GetMapping("/get-customer/{id}")
    public Customer getcustomer(@PathVariable int id){
         return customerServices.getcustomerByid(id);
    }

    //Add orders

    @PostMapping("/add-simple-order")
    public List<Notification> addSimpleOrder(@RequestBody SimpleOrder order){
        OrderManagement orderMang=new OrderManagement(order);
        orderMang.makeorder();
        return NotificationManager.printNotificationQueue();
    }
    @DeleteMapping("/cancel-simple-order/{id}")
    public List<Notification> cancelSimpleOrder(@PathVariable int id){
        Order o=OrderServices.getorderbyId(id);
        OrderManagement orderMang=new OrderManagement(o);
        orderMang.cancelorder(o.orderId);
        return NotificationManager.printNotificationQueue();
    }
    @PostMapping("/add-compound-order")
    public List<Notification> addCompoundOrde(@PathVariable Compoundorder compoundorder){
        OrderManagement orderMang=new OrderManagement(compoundorder);
        orderMang.makeorder();
        return NotificationManager.printNotificationQueue();
    }
    @DeleteMapping("/cancel-compound-order/{id}")
    public List<Notification> cancelCompoundOrder(@PathVariable int id){
        Order o=OrderServices.getorderbyId(id);
        OrderManagement orderMang=new OrderManagement(o);
        orderMang.cancelorder(o.orderId);
        return NotificationManager.printNotificationQueue();
    }
}
