
import java.beans.Statement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asus
 */
public class CustomerInerfaceImpl extends UnicastRemoteObject implements CustomerInterface {

    CustomerInerfaceImpl() throws RemoteException {

        super();
    }

    @Override
    public List<Customer> getCustomer() throws RemoteException {
        List<Customer> list;
        DatabaseHandler d = new DatabaseHandler();
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }

        list = d.testQuery("Customer");
        for (Customer s : list) {
            System.out.println("Fname: " + s.getName());
            System.out.println("Femail: " + s.getEmail());
            System.out.println("Fpassword: " + s.getPassword());
        }

        d.closeDatabase();
        return list;
    }

    @Override
    public String sayHello(String name) throws RemoteException {

        return "hello " + name;

    }

    @Override
    public boolean loginCheck(String email, String password) throws RemoteException {

        boolean result = false;
        DatabaseHandler d = new DatabaseHandler();
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        try {
            result = d.loginMethod(email, password);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerInerfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean registerCustomer(String name, String email, String password) throws RemoteException {

        boolean result = false;
        DatabaseHandler d = new DatabaseHandler();
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        result = d.registrationMethod(name, email, password);
        return result;
    }

    @Override
    public ArrayList<Product> getProduct() throws RemoteException {
        ArrayList<Product> list = new ArrayList<Product>();
        DatabaseHandler d = new DatabaseHandler();
        ResultSet result;
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }

        result = d.getProduct("product");
        try {

            ArrayList<String> colName = new ArrayList<>();

            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                colName.add(result.getMetaData().getColumnName(i));
            }
            int cnt = 0;
            while (result.next()) {
                String ss = "", res;
                Product product = new Product();
                for (String col : colName) {

                    res = result.getString(col);
                    ss += " " + res;
                    switch (cnt) {
                        case 0:
                            product.setId(Integer.parseInt(res));
                            System.out.println("Hello : " + res);
                            break;
                        case 1:
                            product.setName(res);
                            break;
                        case 2:
                            product.setCategory(res);
                            break;
                        case 3:
                            product.setQuantity(Integer.parseInt(res));
                            break;
                        case 4:
                            product.setAvailability(res);
                            break;
                        case 5:
                            product.setDetails(res);
                            break;
                        case 6:
                            product.setPicture(res);
                            break;
                        default:
                            product.setPrice(res);

                            break;
                    }
                    cnt++;
                }
                cnt = 0;
                //    System.out.println("Helo " + ss);
                list.add(product);
            }
            list.stream().forEach((s) -> {
                System.out.println(s.getId());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        d.closeDatabase();
        return list;
    }
    @Override
      public ArrayList<Product> getCart(String email) throws RemoteException {
     ArrayList<Product> list = new ArrayList<Product>();
        DatabaseHandler d = new DatabaseHandler();
        ResultSet result;
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        int invoice = d.getInvoice(email);
        String sql = "SELECT * FROM product INNER JOIN cart ON product.PId = cart.p_id WHERE cart.c_email = '"+email+"' and invoice =" + String.valueOf(invoice); 
        result = d.getFavProduct(sql);
        System.out.println(sql);
        try {

            ArrayList<String> colName = new ArrayList<>();

            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                colName.add(result.getMetaData().getColumnName(i));
            }
            int cnt = 0;
            while (result.next()) {
                String ss = "", res;
                Product product = new Product();
                for (String col : colName) {

                    res = result.getString(col);
                    ss += " " + res;
                    System.out.println(col);
                    switch (col) {
                        case "PId":
                            product.setId(Integer.parseInt(res));
                            System.out.println("Hello : " + res);
                            break;
                        case "PName":
                            product.setName(res);
                            break;
                        case "Category":
                            product.setCategory(res);
                            break;
                        case "Quantity":
                            product.setQuantity(Integer.parseInt(res));
                            break;
                        case "Availability":
                            product.setAvailability(res);
                            break;
                        case "Details":
                            product.setDetails(res);
                            break;
                        case "PictureFile":
                            product.setPicture(res);
                            System.out.println("Hello : " + res);
                            break;
                        case "Price":

                            product.setPrice(res);

                            break;
                    }
                    cnt++;
                }
                cnt = 0;
                //    System.out.println("Helo " + ss);
                list.add(product);
            }
            list.stream().forEach((s) -> {
                System.out.println(s.getId());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        d.closeDatabase();
        return list;
      }
    @Override
      public void addToCart(String email, int pid) throws RemoteException{
            ArrayList<Product> list = new ArrayList<Product>();
        DatabaseHandler d = new DatabaseHandler();
        ResultSet result;
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        int invoice = d.getInvoice(email);
        d.insertData("INSERT INTO cart VALUES('" + email + "'," + String.valueOf(pid) + "," + "1" + "," + "'false'," + String.valueOf(invoice) + ")" );
        
        String s = "INSERT INTO cart VALUES(" + email + "," + String.valueOf(pid) + "," + "1" + "," + "'false','" + String.valueOf(invoice) + "')";
        System.out.println("I am from add : " + s);
        d.closeDatabase();
          
      }
    @Override
       public void addToFav(int pid, String email) throws RemoteException{
           
        DatabaseHandler d = new DatabaseHandler();
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        String sql = "INSERT INTO favorites VALUES("+ String.valueOf(pid) + ",'"+ email +  "')";
        boolean ans = d.insertData(sql);
        System.out.println(sql);
        d.closeDatabase();
           
           
       }
         @Override
       public void addToReview(int pid, String email,String review) throws RemoteException{
           
        DatabaseHandler d = new DatabaseHandler();
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        String sql = "INSERT INTO review VALUES("+ String.valueOf(pid) + ",'"+ email +  "','" + review + "')";
        boolean ans = d.insertData(sql);
        System.out.println(sql);
        d.closeDatabase();
           
           
       }
   
    @Override
       public ArrayList<Review>getReview(int pid) throws RemoteException{
           
        DatabaseHandler d = new DatabaseHandler();
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        String sql = "Select * from review where p_id = " + String.valueOf(pid);
        ResultSet result = d.getFavProduct(sql);
        ArrayList<Review> list = new ArrayList<Review>();
        System.out.println("Iam from review" + sql);
        try {

            ArrayList<String> colName = new ArrayList<>();

            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                colName.add(result.getMetaData().getColumnName(i));
            }
            int cnt = 0;
            while (result.next()) {
                String ss = "", res;
                Review product = new Review();
                for (String col : colName) {

                    res = result.getString(col);
                    ss += " " + res;
                    switch (cnt) {
                        case 0:
                            product.setPid(Integer.parseInt(res));
                            System.out.println("Hello : " + res);
                            break;
                        case 1:
                            product.setUser(res);
                            System.out.println("Hello : " + res);
                            break;
                        default:
                            product.setReview(res);

                            break;
                    }
                    cnt++;
                }
                cnt = 0;
                //    System.out.println("Helo " + ss);
                list.add(product);
            }
        //    list.stream().forEach((s) -> {
        //        System.out.println(s.getId());
       //     });

        } catch (Exception e) {
            e.printStackTrace();
        }
        d.closeDatabase();
        return list;

           
           
       }
    @Override
       public ArrayList<Product> getFavProduct(String email) throws RemoteException{
           
        ArrayList<Product> list = new ArrayList<Product>();
        DatabaseHandler d = new DatabaseHandler();
        ResultSet result;
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }

        String sql = "SELECT * FROM product INNER JOIN favorites ON product.PId = favorites.p_id WHERE favorites.u_id = '"+email+"'"; 
        result = d.getFavProduct(sql);
        System.out.println(sql);
        try {

            ArrayList<String> colName = new ArrayList<>();

            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                colName.add(result.getMetaData().getColumnName(i));
            }
            int cnt = 0;
            while (result.next()) {
                String ss = "", res;
                Product product = new Product();
                for (String col : colName) {

                    res = result.getString(col);
                    ss += " " + res;
                    switch (cnt) {
                        case 0:
                            product.setId(Integer.parseInt(res));
                            System.out.println("Hello : " + res);
                            break;
                        case 1:
                            product.setName(res);
                            break;
                        case 2:
                            product.setCategory(res);
                            break;
                        case 3:
                            product.setQuantity(Integer.parseInt(res));
                            break;
                        case 4:
                            product.setAvailability(res);
                            break;
                        case 5:
                            product.setDetails(res);
                            break;
                        case 6:
                            product.setPicture(res);
                            break;
                        default:
                            product.setPrice(res);

                            break;
                    }
                    cnt++;
                }
                cnt = 0;
                //    System.out.println("Helo " + ss);
                list.add(product);
            }
            list.stream().forEach((s) -> {
                System.out.println(s.getId());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        d.closeDatabase();
        return list;
           
       }
           
    @Override
       public void deleteFromFav(int pid, String email) throws RemoteException{
           
        DatabaseHandler d = new DatabaseHandler();
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }
        String sql = "Delete from favorites WHERE p_id = "+ String.valueOf(pid) + " and u_id = '"+ email +  "'";
        boolean ans = d.insertData(sql);
        System.out.println(sql);
        d.closeDatabase();
           
           
       }
    @Override
      public ArrayList<Product> getProductInfo(int pid) throws RemoteException{
          
          
                  
        ArrayList<Product> list = new ArrayList<Product>();
        DatabaseHandler d = new DatabaseHandler();
        ResultSet result;
        if (d.setConnection("shopmanagement", "root", "")) {
            System.out.println("Successfully Connected..");
        }

        String sql = "SELECT * FROM product WHERE Pid = " + pid; 
        result = d.getFavProduct(sql);
        System.out.println(sql);
        try {

            ArrayList<String> colName = new ArrayList<>();

            for (int i = 1; i <= result.getMetaData().getColumnCount(); i++) {
                colName.add(result.getMetaData().getColumnName(i));
            }
            int cnt = 0;
            while (result.next()) {
                String ss = "", res;
                Product product = new Product();
                for (String col : colName) {

                    res = result.getString(col);
                    ss += " " + res;
                    switch (cnt) {
                        case 0:
                            product.setId(Integer.parseInt(res));
                            System.out.println("Hello : " + res);
                            break;
                        case 1:
                            product.setName(res);
                            break;
                        case 2:
                            product.setCategory(res);
                            break;
                        case 3:
                            product.setQuantity(Integer.parseInt(res));
                            break;
                        case 4:
                            product.setAvailability(res);
                            break;
                        case 5:
                            product.setDetails(res);
                            break;
                        case 6:
                            product.setPicture(res);
                            break;
                        default:
                            product.setPrice(res);

                            break;
                    }
                    cnt++;
                }
                cnt = 0;
                //    System.out.println("Helo " + ss);
                list.add(product);
            }
            list.stream().forEach((s) -> {
                System.out.println(s.getId());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        d.closeDatabase();
        return list;
          
            
        }

}
