/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class CartServlet extends HttpServlet {

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
        List<Product> product = new ArrayList<>();
        HttpSession session=request.getSession();
        String user = (String) session.getAttribute("name");
         ArrayList<Product> list = new ArrayList<>();
        try{
            Registry registry = LocateRegistry.getRegistry(40001);
            CustomerInterface stub = (CustomerInterface) registry.lookup("Customer");
            product = stub.getCart(user);  
           
          
        }catch(Exception ex)
        {
            ex.printStackTrace();
        }
        String pid = request.getParameter("pid");
        System.out.println("Parameter = " + pid);
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
            out.println("<title>Aroma Shop - Cart</title>");
            out.println("<link rel='icon' href='img/Fevicon.png' type='image/png'>");
            out.println("<link rel='stylesheet' href='vendors/bootstrap/bootstrap.min.css'>");
            out.println("<link rel='stylesheet' href='vendors/fontawesome/css/all.min.css'>");
            out.println("<link rel='stylesheet' href='vendors/themify-icons/themify-icons.css'>");
            out.println("<link rel='stylesheet' href='vendors/linericon/style.css'>");
            out.println("<link rel='stylesheet' href='vendors/owl-carousel/owl.theme.default.min.css'>");
            out.println("<link rel='stylesheet' href='vendors/owl-carousel/owl.carousel.min.css'>");
            out.println("<link rel='stylesheet' href='vendors/nice-select/nice-select.css'>");
            out.println("<link rel='stylesheet' href='vendors/nouislider/nouislider.min.css'>");
            out.println("");
            out.println("<link rel='stylesheet' href='css/style.css'>");
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
            out.println("<!--================ End Header Menu Area =================-->");
            out.println("");
            out.println("<!-- ================ start banner area ================= -->");
            out.println("<section class='blog-banner-area' id='category'>");
            out.println("<div class='container h-100'>");
            out.println("<div class='blog-banner'>");
            out.println("<div class='text-center'>");
            out.println("<h1>Shopping Cart</h1>");
            out.println("<nav aria-label='breadcrumb' class='banner-breadcrumb'>");
            out.println("<ol class='breadcrumb'>");
            out.println("<li class='breadcrumb-item'><a href='#'>Home</a></li>");
            out.println("<li class='breadcrumb-item active' aria-current='page'>Shopping Cart</li>");
            out.println("</ol>");
            out.println("</nav>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<!-- ================ end banner area ================= -->");
            out.println("");
            out.println("");
            out.println("");
            out.println("<!--================Cart Area =================-->");
            out.println("<section class='cart_area'>");
            out.println("<div class='container'>");
            out.println("<div class='cart_inner'>");
            out.println("<div class='table-responsive'>");
            out.println("<table class='table'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th scope='col'>Product</th>");
            out.println("<th scope='col'>Price</th>");
            out.println("<th scope='col'>Quantity</th>");
            out.println("<th scope='col'>Total</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            for (Product singleProduct : product)
            {
            out.println("<tr>");
            out.println("<td>");
            out.println("<div class='media'>");
            out.println("<div class='d-flex'>");
            out.println("<img src='"+singleProduct.getPicture()+"' alt=''>");
            out.println("</div>");
            out.println("<div class='media-body'>");
            out.println("<p>"+singleProduct.getName() +"</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</td>");
            out.println("<td>");
            out.println("<h5>$"+singleProduct.getPrice()+"</h5>");
            out.println("</td>");
            out.println("<td>");
            out.println("<div class='product_count'>");
            out.println("<input type='text' name='qty' id='"+ String.valueOf(singleProduct.getId()) +"' maxlength='12' value='1' title='Quantity:'");
            out.println("class='input-text qty'>");
            out.println("<button onclick=myFunction("+String.valueOf(singleProduct.getId())+")");
            out.println("class='increase items-count' type='button'><i class='lnr lnr-chevron-up'></i></button>");
            out.println("<button onclick=myFunction2("+String.valueOf(singleProduct.getId())+")");
            out.println("class='reduced items-count' type='button'><i class='lnr lnr-chevron-down'></i></button>");
            out.println("</div>");
            out.println("</td>");
            out.println("<td>");
            int price =Integer.parseInt(singleProduct.getPrice());
            out.println("<h5>"+ price+"</h5>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<script type=\"text/javascript\">");
            out.println("function myFunction(str) {");
            out.println("var result = document.getElementById(str); var sst = result.value; if( !isNaN( sst )) result.value++;return false;}");
            out.println("function myFunction2(str) {");
            out.println("var result = document.getElementById(str); var sst = result.value; if( !isNaN( sst ) && sst > 0) result.value--;return false;}");    // out.println("alert('the session did time out, please reconnect');");
                // out.println("logout();}");
            out.println("</script>");
            out.println("</body>");
            out.println("</html>");
            }
            out.println("<tr class='bottom_button'>");
            out.println("<td>");
            out.println("<a class='button' href='#'>Update Cart</a>");
            out.println("</td>");
            out.println("<td>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("<div class='cupon_text d-flex align-items-center'>");
            out.println("<input type='text' placeholder='Coupon Code'>");
            out.println("<a class='primary-btn' href='#'>Apply</a>");
            out.println("<a class='button' href='#'>Have a Coupon?</a>");
            out.println("</div>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("<h5>Subtotal</h5>");
            out.println("</td>");
            out.println("<td>");
            out.println("<h5>$2160.00</h5>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr class='shipping_area'>");
            out.println("<td class='d-none d-md-block'>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("<h5>Shipping</h5>");
            out.println("</td>");
            out.println("<td>");
            out.println("<div class='shipping_box'>");
            out.println("<ul class='list'>");
            out.println("<li><a href='#'>Flat Rate: $5.00</a></li>");
            out.println("<li><a href='#'>Free Shipping</a></li>");
            out.println("<li><a href='#'>Flat Rate: $10.00</a></li>");
            out.println("<li class='active'><a href='#'>Local Delivery: $2.00</a></li>");
            out.println("</ul>");
            out.println("<h6>Calculate Shipping <i class='fa fa-caret-down' aria-hidden='true'></i></h6>");
            out.println("<select class='shipping_select'>");
            out.println("<option value='1'>Bangladesh</option>");
            out.println("<option value='2'>India</option>");
            out.println("<option value='4'>Pakistan</option>");
            out.println("</select>");
            out.println("<select class='shipping_select'>");
            out.println("<option value='1'>Select a State</option>");
            out.println("<option value='2'>Select a State</option>");
            out.println("<option value='4'>Select a State</option>");
            out.println("</select>");
            out.println("<input type='text' placeholder='Postcode/Zipcode'>");
            out.println("<a class='gray_btn' href='#'>Update Details</a>");
            out.println("</div>");
            out.println("</td>");
            out.println("</tr>");
            out.println("<tr class='out_button_area'>");
            out.println("<td class='d-none-l'>");
            out.println("");
            out.println("</td>");
            out.println("<td class=''>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("");
            out.println("</td>");
            out.println("<td>");
            out.println("<div class='checkout_btn_inner d-flex align-items-center'>");
            out.println("<a class='gray_btn' href='#'>Continue Shopping</a>");
            out.println("<a class='primary-btn ml-2' href='#'>Proceed to checkout</a>");
            out.println("</div>");
            out.println("</td>");
            out.println("</tr>");
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<!--================End Cart Area =================-->");
            out.println("");
            out.println("");
            out.println("");
            out.println("<!--================ Start footer Area  =================-->");
            out.println("<footer>");
            out.println("<div class='footer-area footer-only'>");
            out.println("<div class='container'>");
            out.println("<div class='row section_gap'>");
            out.println("<div class='col-lg-3 col-md-6 col-sm-6'>");
            out.println("<div class='single-footer-widget tp_widgets '>");
            out.println("<h4 class='footer_title large_title'>Our Mission</h4>");
            out.println("<p>");
            out.println("So seed seed green that winged cattle in. Gathering thing made fly you're no");
            out.println("divided deep moved us lan Gathering thing us land years living.");
            out.println("</p>");
            out.println("<p>");
            out.println("So seed seed green that winged cattle in. Gathering thing made fly you're no divided deep moved");
            out.println("</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='offset-lg-1 col-lg-2 col-md-6 col-sm-6'>");
            out.println("<div class='single-footer-widget tp_widgets'>");
            out.println("<h4 class='footer_title'>Quick Links</h4>");
            out.println("<ul class='list'>");
            out.println("<li><a href='#'>Home</a></li>");
            out.println("<li><a href='#'>Shop</a></li>");
            out.println("<li><a href='#'>Blog</a></li>");
            out.println("<li><a href='#'>Product</a></li>");
            out.println("<li><a href='#'>Brand</a></li>");
            out.println("<li><a href='#'>Contact</a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='col-lg-2 col-md-6 col-sm-6'>");
            out.println("<div class='single-footer-widget instafeed'>");
            out.println("<h4 class='footer_title'>Gallery</h4>");
            out.println("<ul class='list instafeed d-flex flex-wrap'>");
            out.println("<li><img src='img/gallery/r1.jpg' alt=''></li>");
            out.println("<li><img src='img/gallery/r2.jpg' alt=''></li>");
            out.println("<li><img src='img/gallery/r3.jpg' alt=''></li>");
            out.println("<li><img src='img/gallery/r5.jpg' alt=''></li>");
            out.println("<li><img src='img/gallery/r7.jpg' alt=''></li>");
            out.println("<li><img src='img/gallery/r8.jpg' alt=''></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='offset-lg-1 col-lg-3 col-md-6 col-sm-6'>");
            out.println("<div class='single-footer-widget tp_widgets'>");
            out.println("<h4 class='footer_title'>Contact Us</h4>");
            out.println("<div class='ml-40'>");
            out.println("<p class='sm-head'>");
            out.println("<span class='fa fa-location-arrow'></span>");
            out.println("Head Office");
            out.println("</p>");
            out.println("<p>123, Main Street, Your City</p>");
            out.println("");
            out.println("<p class='sm-head'>");
            out.println("<span class='fa fa-phone'></span>");
            out.println("Phone Number");
            out.println("</p>");
            out.println("<p>");
            out.println("+123 456 7890 <br>");
            out.println("+123 456 7890");
            out.println("</p>");
            out.println("");
            out.println("<p class='sm-head'>");
            out.println("<span class='fa fa-envelope'></span>");
            out.println("Email");
            out.println("</p>");
            out.println("<p>");
            out.println("free@infoexample.com <br>");
            out.println("www.infoexample.com");
            out.println("</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("");
            out.println("<div class='footer-bottom'>");
            out.println("<div class='container'>");
            out.println("<div class='row d-flex'>");
            out.println("<p class='col-lg-12 footer-text text-center'>");
            out.println("<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->");
            out.println("Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class='fa fa-heart' aria-hidden='true'></i> by <a href='https://colorlib.com' target='_blank'>Colorlib</a>");
            out.println("<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</footer>");
            out.println("<!--================ End footer Area  =================-->");
            out.println("");
            out.println("");
            out.println("");
            out.println("<script src='vendors/jquery/jquery-3.2.1.min.js'></script>");
            out.println("<script src='vendors/bootstrap/bootstrap.bundle.min.js'></script>");
            out.println("<script src='vendors/skrollr.min.js'></script>");
            out.println("<script src='vendors/owl-carousel/owl.carousel.min.js'></script>");
            out.println("<script src='vendors/nice-select/jquery.nice-select.min.js'></script>");
            out.println("<script src='vendors/jquery.ajaxchimp.min.js'></script>");
            out.println("<script src='vendors/mail-script.js'></script>");
            out.println("<script src='js/main.js'></script>");
       


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
