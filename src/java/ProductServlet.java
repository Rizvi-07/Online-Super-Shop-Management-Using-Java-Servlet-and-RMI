/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<Product> list = new ArrayList<>();
        try{
            Registry registry = LocateRegistry.getRegistry(40001);
            CustomerInterface stub = (CustomerInterface) registry.lookup("Customer");
            // Calling the remote method using the obtained object 
             list = stub.getProduct(); 
          //  fromServer = stub.sayHello("Oishee");
         //     fromServer = stub.getProduct();
           System.out.println("Fname: "); 
           for (Product s: list){ 
            System.out.println("Fname: " +s.getPrice()); 
            }  
           
          
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
            out.println("<title>Aroma Shop - Product Details</title>");
            out.println("<link rel='icon' href='img/Fevicon.png' type='image/png'>");
            out.println("<link rel='stylesheet' href='vendors/bootstrap/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='vendors/fontawesome/css/all.min.css'>");
            out.println("<link rel='stylesheet' href='vendors/themify-icons/themify-icons.css'>");
            out.println("<link rel='stylesheet' href='vendors/linericon/style.css'>");
            out.println("<link rel='stylesheet' href='vendors/nice-select/nice-select.css'>");
            out.println("<link rel='stylesheet' href='vendors/owl-carousel/owl.theme.default.min.css'>");
            out.println("<link rel='stylesheet' href='vendors/owl-carousel/owl.carousel.min.css'>");
            out.println("<link rel='stylesheet' href='product.css'>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<!--================ Start Header Menu Area =================-->");
            out.println("<header class='header_area'>");
            out.println("<div class='main_menu'>");
            out.println("<nav class='navbar navbar-expand-lg navbar-light'>");
            out.println("<div class='container'>");
            out.println("<a class='navbar-brand logo_h' href='index.html'><img src='img/logo.png' alt=''></a>");
            out.println("<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarSupportedContent'");
            out.println("aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>");
            out.println("<span class='icon-bar'></span>");
            out.println("<span class='icon-bar'></span>");
            out.println("<span class='icon-bar'></span>");
            out.println("</button>");
            out.println("<div class='collapse navbar-collapse offset' id='navbarSupportedContent'>");
            out.println("<ul class='nav navbar-nav menu_nav ml-auto mr-auto'>");
            out.println("<li class='nav-item'><a class='nav-link' href='home.html'>Home</a></li>");
            out.println("<li class='nav-item active submenu dropdown'>");
            out.println("<a href='ProductServlet' class='nav-link dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true'");
            out.println("aria-expanded='false'>Product</a>");
       
            out.println("</li>");
            out.println("<li class='nav-item submenu dropdown'>");
            out.println("<a href='#' class='nav-link dropdown-toggle' data-toggle='dropdown' role='button' aria-haspopup='true'");
            out.println("aria-expanded='false'>My Shop</a>");
            out.println("<ul class='dropdown-menu'>");
            out.println("<li class='nav-item'><a class='nav-link' href='FavProductViewServlet'>Favorites</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='single-blog.html'>Tracking</a></li>");
            out.println("</ul>");
            out.println("</li>");
            
            out.println("<li class='nav-item'><a class='nav-link' href='contact.html'>Contact</a></li>");
            out.println("</ul>");
            out.println("<ul class='nav-shop'>");
            out.println("<li class='nav-item'><button><i class='ti-search'></i></button></li>");
    out.println("<li class='nav-item'><a href = 'CartServlet'> <button><i class='ti-shopping-cart' ></i><span class='nav-shop__circle'>3</span></button></a> </li>");
            out.println("<li class='nav-item'><a class='button button-header' href='CustomerLogoutServelet'>Logout</a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");
            out.println("</div>");
            out.println("</header>");
            out.println("<div class='container'>");
            out.println("<div class='row'>");
            //Adding product one by one
             for (Product s: list)
            {           
            out.println("<div class='col-md-4'>");
            out.println("<div class='product-item'>");
            out.println("<div class='pi-img-wrapper'>");
            out.println("<img src='" +  Paths.get(s.getPicture()) + "' class='img-responsive' height=\"300\" width=\"300\" alt='Berry Lace Dress'>");
            out.println("<div>");
            out.println("<a href='ProductDetailsServlet?vid="+ String.valueOf(s.getId())+"' class='btn'>View</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("<h3><a href='shop-item.html'>" + s.getName() + "</a></h3>");
            out.println("<div class='pi-price'>$" + s.getPrice() + "</div>");
            // This will go to cart
            out.println("<a href='AddToCartServlet?pid="+ String.valueOf(s.getId()) + "' class='btn add2cart'>Add to cart</a>");
            out.println("<a href='FavoriteProduct?pid="+ String.valueOf(s.getId()) + "' class='btn add2cart'>Add to Favorite</a>");
            System.out.println(s.getId());
            out.println("<div class='sticker sticker-new'></div>");
            out.println("</div>");
            out.println("</div>");
            }
            out.println("</div>");
            out.println("</div>");
            out.println("<br>");
            out.println("<br>");
            out.println("<center>");
            out.println("<strong>Powered by <a href='http://j.mp/metronictheme' target='_blank'>KeenThemes</a></strong>");
            out.println("</center>");
            out.println("<br>");
            out.println("<br>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
