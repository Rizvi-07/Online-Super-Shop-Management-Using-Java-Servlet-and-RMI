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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
public class ProductDetailsServlet extends HttpServlet {

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
        
        String pid = request.getParameter("vid");
        System.out.println("Parameter = " + pid);
        List<Product> list = new ArrayList<>();
        List<Review> review = new ArrayList<>();
        Product s = null;
        try {
            Registry registry = LocateRegistry.getRegistry(40001);
            CustomerInterface stub = (CustomerInterface) registry.lookup("Customer");
            // Calling the remote method using the obtained object 
            list = (List) stub.getProductInfo(Integer.parseInt(pid));
            review = (List) stub.getReview(Integer.parseInt(pid));
            //  fromServer = stub.sayHello("Oishee");
            //     fromServer = stub.getProduct();
            System.out.println("Fname: ");
            s = list.get(0);

          
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
            out.println("");
            out.println("<link rel='stylesheet' href='css/style.css'>");
            out.println("</head>");
            out.println("<body>");
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
            out.println("<div class='product_image_area'>");
            out.println("<div class='container'>");
            out.println("<div class='row s_product_inner'>");
            out.println("<div class='col-lg-6'>");
            out.println("<div class='owl-carousel owl-theme s_Product_carousel'>");
            out.println("<div class='single-prd-item'>");
            out.println("<img class='img-fluid' src='"+ s.getPicture() + "' alt=''>");
            out.println("</div>");
            out.println("<!-- <div class='single-prd-item'>");
            out.println("<img class='img-fluid' src='img/category/s-p1.jpg' alt=''>");
            out.println("</div>");
            out.println("<div class='single-prd-item'>");
            out.println("<img class='img-fluid' src='img/category/s-p1.jpg' alt=''>");
            out.println("</div> -->");
            out.println("</div>");
            out.println("</div>");
            
            //Product Details
            out.println("<div class='col-lg-5 offset-lg-1'>");
            for(Product p : list)
            {
            out.println("<div class='s_product_text'>");
            out.println("<h3>"+p.getName()+"</h3>");
            out.println("<h2>$"+p.getPrice()+"</h2>");
            out.println("<ul class='list'>");
            out.println("<li><a class='active' href='#'><span>"+ p.getCategory()+"</span>  </a></li>");
            out.println("<li><a href='#'><span>Availibility</span> : In Stock</a></li>");
            out.println("</ul>");
            out.println("<p>Mill Oil is an innovative oil filled radiator with the most modern technology. If you are looking for");
            out.println("something that can make your interior look awesome, and at the same time give you the pleasant warm feeling");
            out.println("during the winter.</p>");
            out.println("<div class='product_count'>");
            out.println("<label for='qty'>Quantity: "+ p.getQuantity()+"</label>");
            }
           // out.println("<button onclick='var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;'");
           // out.println("class='increase items-count' type='button'><i class='ti-angle-left'></i></button>");
            //out.println("<input type='text' name='qty' id='sst' size='2' maxlength='12' value='1' title='Quantity:' class='input-text qty'>");
           // out.println("<button onclick='var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;'");
            //out.println("class='reduced items-count' type='button'><i class='ti-angle-right'></i></button>");
           // out.println("<a class='button primary-btn' href='#'>Add to Cart</a>");
            out.println("</div>");
          
            out.println("<div class='card_area d-flex align-items-center'>");
            out.println("<a class='icon_btn' href='#'><i class='lnr lnr lnr-diamond'></i></a>");
            out.println("<a class='icon_btn' href='#'><i class='lnr lnr lnr-heart'></i></a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            
            out.println("</div>");
            out.println("<!--================End Single Product Area =================-->");
            
            out.println("");
            out.println("<!--================Product Description Area =================-->");
            out.println("<section class='product_description_area'>");
            out.println("<div class='container'>");
            out.println("<ul class='nav nav-tabs' id='myTab' role='tablist'>");
            out.println("");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link active' id='review-tab' data-toggle='tab' href='#review' role='tab' aria-controls='review'");
            out.println("aria-selected='false'>Reviews</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("<div class='tab-content' id='myTabContent'>");
            out.println("<div class='tab-pane fade' id='contact' role='tabpanel' aria-labelledby='contact-tab'>");
            out.println("<div class='row'>");
            out.println("");
            out.println("<div class='col-lg-6'>");
            out.println("");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='tab-pane fade show active' id='review' role='tabpanel' aria-labelledby='review-tab'>");
            out.println("<div class='row'>");
            out.println("<div class='col-lg-6'>");
            out.println("<div class='row total_rate'>");
            out.println("<div class='col-6'>");
            out.println("<div class='box_total'>");
            out.println("<h5>Overall</h5>");
            out.println("<h6>(03 Reviews)</h6>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='review_list'>");
            for(Review r : review)
            {
            out.println("<div class='review_item'>");
            out.println("<div class='media'>");
            out.println("");
            out.println("<div class='media-body'>");
            out.println("<h4>"+r.getUser()+"</h4>");
            out.println("</div>");
            out.println("</div>");
            out.println("<p>"+r.getReview()+"</p>");
            out.println("</div>");
        }
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='col-lg-6'>");
            out.println("<div class='review_box'>");
            out.println("<h4>Add a Review</h4>");
            out.println("<form action='ReviewServlet?pid="+ String.valueOf(s.getId()) + "' method = POST class='form-contact form-review mt-3'>");
            out.println("<div class='form-group'>");
            out.println("<input class='form-control' name='name' type='text' placeholder='Enter your name' required>");
            out.println("</div>");
            out.println("<div class='form-group'>");
            out.println("<div class='form-group'>");
            out.println("<textarea class='form-control different-control w-100' name='textarea' id='textarea' cols='30' rows='5' placeholder='Enter Message'></textarea>");
            out.println("</div>");
            out.println("<div class='form-group text-center text-md-right mt-3'>");
            out.println("<button type='submit' class='button button--active button-review'>Submit Now</button>");
            out.println("</div>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<footer>");
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
