
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asus
 */
public interface CustomerInterface extends Remote {

    public List<Customer> getCustomer() throws RemoteException;

    public String sayHello(String name) throws RemoteException;

    public boolean loginCheck(String email, String password) throws RemoteException;

    public boolean registerCustomer(String name, String email, String password) throws RemoteException;

    public ArrayList<Product> getProduct() throws RemoteException;
    public ArrayList<Product> getProductInfo(int pid) throws RemoteException;
    public ArrayList<Product> getCart(String email) throws RemoteException;
    
    public void addToFav(int pid, String email) throws RemoteException;
    public void deleteFromFav(int pid, String email) throws RemoteException;
    public ArrayList<Product>  getFavProduct(String email) throws RemoteException;
    public void addToReview(int pid, String email,String review) throws RemoteException;
    public ArrayList<Review> getReview(int pid) throws RemoteException;
    public void addToCart(String email, int pid) throws RemoteException;
}
