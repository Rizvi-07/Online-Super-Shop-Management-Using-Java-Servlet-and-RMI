/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class Product implements java.io.Serializable{
    private String name,picture,availabilty,price,category,details;
    int id,quantity;
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getAvailability(){
        return availabilty;
    }
    public String getPrice(){
        return price;
    }
    public String getCategory(){
        return category;
    }
    public String getDetails(){
        return details;
    }
    public void setName(String name) {
        this.name = name;
    }
     public void setId(int id) {
        this.id = id;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setAvailability(String availabilty){
        this.availabilty = availabilty;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public void setDetails(String details){
        this.details = details;
    }
    

    
}
