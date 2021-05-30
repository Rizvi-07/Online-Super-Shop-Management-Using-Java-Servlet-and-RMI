/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class Cart implements java.io.Serializable{
    
    private String email,checkOut;
    int pid,quantity,invoice;
    public String getEmail() {
        return email;
    }

    public int getId() {
        return pid;
    }

    public int getQuantity(){
        return quantity;
    }
    public String checkOut(){
        return checkOut;
    }
    public void setEmail(String email) {
        this.email = email;
    }
     public void setId(int id) {
        this.pid = id;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setCheckout(String checkout){
        this.checkOut = checkout;
    }

    
}
