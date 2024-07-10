//Product services
package com.example.demo.services;

import org.springframework.stereotype.Service;
import com.example.demo.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class Inventory {
    public static List<Product> productslist =new ArrayList<>(Arrays.asList());
    static int BookRemainingParts=0;
    static int DeviceRemainingParts=0;
    static int GamesRemainingParts=0;
    public
    List<Product> getAllProducts(){
        return productslist;
    }
    public List<Product> getAllProductsInCategory(String cat){
        List<Product>temp=new ArrayList<>();
        for (int i=0; i<productslist.size(); i++){
            if (Objects.equals(productslist.get(i).getCategory(), cat)){
                temp.add(productslist.get(i));
            }
        }
        return temp;
    }
    public Product getProductByCategoryAndName(String cat, String name){
        for(Product p:productslist){
            if(Objects.equals(p.getCategory(), cat) && Objects.equals(p.getname(), name)){
                return p;
            }
        }
        return null;
    }
    public static void decrementRemainingParts(String cat){
        if (Objects.equals(cat, "Books")){
            BookRemainingParts--;
        }
        else if (Objects.equals(cat, "Games")){
            GamesRemainingParts--;
        }
        else if (Objects.equals(cat, "Devices")){
            DeviceRemainingParts--;
        }
    }
    public static void incrementRemainingParts(String cat){
        if (Objects.equals(cat, "Books")){
            BookRemainingParts++;
        }
        else if (Objects.equals(cat, "Games")){
            GamesRemainingParts++;
        }
        else if (Objects.equals(cat, "Devices")){
            DeviceRemainingParts++;
        }
    }
    public int getRemainingParts(String cat){
        if (Objects.equals(cat, "Books")){
            return BookRemainingParts;
        }
        else if (Objects.equals(cat, "Games")){
            return GamesRemainingParts;
        }
        else if (Objects.equals(cat, "Devices")){
            return DeviceRemainingParts;
        }
        return -1;
    }

    public  Boolean add(Product p){
        incrementRemainingParts(p.getCategory());
        return productslist.add(p);
    }
    public static Product getProductBySerial(int serial) {
        for (int i=0; i<productslist.size(); i++){
            if (productslist.get(i).getserial()==serial){
                return productslist.get(i);
            }
        }
        return null;
    }
}
