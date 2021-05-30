/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
/**
 *
 * @author Asus
 */
public class RMIServer {
    
    public static void main(String[] args)
    {
        try{
            //Initializing the implementation class
           // HelloInterfaceImpl helloInterfaceImpl = new HelloInterfaceImpl();
            CustomerInerfaceImpl customerInerfaceImpl = new CustomerInerfaceImpl();
            
            
            //Binding the remote object in the registry
            Registry registry = LocateRegistry.createRegistry(40001);
            //registry.bind("Hello", (Remote) helloInterfaceImpl);
            registry.bind("Customer", (Remote) customerInerfaceImpl);
            System.err.println("Service ready");
            
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
