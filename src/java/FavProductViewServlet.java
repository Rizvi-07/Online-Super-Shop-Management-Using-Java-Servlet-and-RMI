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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class FavProductViewServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);

        // Check if this is new comer on your web page.
        if (session.isNew()) {
            response.sendRedirect(request.getContextPath() + "/login.html");
        } 
       // HttpSession session=request.getSession();
        String user = (String) request.getSession(false).getAttribute("name");
         ArrayList<Product> list = new ArrayList<>();
        try{
            Registry registry = LocateRegistry.getRegistry(40001);
            CustomerInterface stub = (CustomerInterface) registry.lookup("Customer");
             list = stub.getFavProduct(user);  
           
          
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
            out.println("<title>Aroma Shop - Category</title>");
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
            out.println("");
            out.println("");
            out.println("");
            out.println("<!-- ================ category section start ================= -->");
            out.println("<section class='section-margin--small mb-5'>");
            out.println("<div class='container'>");
            out.println("<div class='row'>");
            out.println("");
            out.println("<div class='col-xl-9 col-lg-8 col-md-8'>");
            out.println("<!-- Start Filter Bar -->");
            out.println("");
            out.println("<!-- End Filter Bar -->");
            out.println("<!-- Start Best Seller -->");
            out.println("<section class='lattest-product-area pb-40 category-list'>");
            out.println("<div class='row'>");
            for (Product s: list){ 
            out.println("<div class='col-md-6 col-lg-4'>");
            out.println("<div class='card text-center card-product'>");
            out.println("<div class='card-product__img'>");
            out.println("<img class='card-img' src='"+s.getPicture()+"'alt=''>");
            out.println("<ul class='card-product__imgOverlay'>");
            out.println("<li><a href = FavDeleteServlet?fid="+ String.valueOf(s.getId())+"><button><i class='ti-remove'>Remove</i></button></a></li>");
            out.println("<li><button><i class='ti-shopping-cart'></i></button></li>");
            out.println("<li><button><i class='ti-heart'></i></button></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("<div class='card-body'>");
            out.println("<p>"+s.getCategory()+"</p>");
            out.println("<h4 class='card-product__title'><a href='#'>" +s.getName()+"'</a></h4>");
            out.println("<p class='card-product__price>$'"+s.getPrice()+"</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            }
            out.println("");
            out.println("");
            out.println("</div>");
            out.println("</section>");
            out.println("<!-- End Best Seller -->");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<!-- ================ category section end ================= -->");
            out.println("");
            out.println("<!-- ================ top product area start ================= -->");
            out.println("<section class='related-product-area'>");
            out.println("<div class='container'>");
            out.println("<div class='section-intro pb-60px'>");
            out.println("<p>Popular Item in the market</p>");
            out.println("<h2>Top <span class='section-intro__style'>Product</span></h2>");
            out.println("</div>");
            out.println("<div class='row mt-30'>");
            out.println("<div class='col-sm-6 col-xl-3 mb-4 mb-xl-0'>");
            out.println("<div class='single-search-product-wrapper'>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-1.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-2.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-3.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("");
            out.println("<div class='col-sm-6 col-xl-3 mb-4 mb-xl-0'>");
            out.println("<div class='single-search-product-wrapper'>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-4.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-5.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-6.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("");
            out.println("<div class='col-sm-6 col-xl-3 mb-4 mb-xl-0'>");
            out.println("<div class='single-search-product-wrapper'>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-7.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-8.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-9.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("");
            out.println("<div class='col-sm-6 col-xl-3 mb-4 mb-xl-0'>");
            out.println("<div class='single-search-product-wrapper'>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-1.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-2.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class='single-search-product d-flex'>");
            out.println("<a href='#'><img src='img/product/product-sm-3.png' alt=''></a>");
            out.println("<div class='desc'>");
            out.println("<a href='#' class='title'>Gray Coffee Cup</a>");
            out.println("<div class='price'>$170.00</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<!-- ================ top product area end ================= -->");
            out.println("");
            out.println("<!-- ================ Subscribe section start ================= -->");
            out.println("<section class='subscribe-position'>");
            out.println("<div class='container'>");
            out.println("<div class='subscribe text-center'>");
            out.println("<h3 class='subscribe__title'>Get Update From Anywhere</h3>");
            out.println("<p>Bearing Void gathering light light his eavening unto dont afraid</p>");
            out.println("<div id='mc_embed_signup'>");
            out.println("<form target='_blank' action='https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01' method='get' class='subscribe-form form-inline mt-5 pt-1'>");
            out.println("<div class='form-group ml-sm-auto'>");
            out.println("<input class='form-control mb-1' type='email' name='EMAIL' placeholder='Enter your email' onfocus='this.placeholder = ''' onblur='this.placeholder = 'Your Email Address '' >");
            out.println("<div class='info'></div>");
            out.println("</div>");
            out.println("<button class='button button-subscribe mr-auto mb-1' type='submit'>Subscribe Now</button>");
            out.println("<div style='position: absolute; left: -5000px;'>");
            out.println("<input name='b_36c4fd991d266f23781ded980_aefe40901a' tabindex='-1' value='' type='text'>");
            out.println("</div>");
            out.println("");
            out.println("</form>");
            out.println("</div>");
            out.println("");
            out.println("</div>");
            out.println("</div>");
            out.println("</section>");
            out.println("<!-- ================ Subscribe section end ================= -->");
            out.println("");
            out.println("");
            out.println("<!--================ Start footer Area  =================-->");
            out.println("<footer>");
            out.println("<div class='footer-area'>");
            out.println("<div class='container'>");
            out.println("<div class='row section_gap'>");
            out.println("<div class='col-lg-3 col-md-6 col-sm-6'>");
            out.println("<div class='single-footer-widget tp_widgets'>");
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
            out.println("<script src='vendors/nouislider/nouislider.min.js'></script>");
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
