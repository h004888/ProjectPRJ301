/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.OrderDetails;
import entity.Orders;
import entity.ProCart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import entity.Products;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOProducts;
import java.util.Enumeration;
import model.DAOCustomer;
import model.DAOOrderDetails;
import model.DAOOrders;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartURL"})
public class CartController extends HttpServlet {

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
        DAOProducts dao = new DAOProducts();
        DAOOrders dAOOrders = new DAOOrders();
        DAOOrderDetails dadetail = new DAOOrderDetails();
        DAOCustomer daoCus = new DAOCustomer();
        String service = request.getParameter("service");
        try (PrintWriter out = response.getWriter()) {
            if (service.equals("showCart")) {
                Vector<ProCart> vector = new Vector<ProCart>();
                Enumeration<String> keyList = session.getAttributeNames();
                while (keyList.hasMoreElements()) {
                    String key = keyList.nextElement();
                    Object obj = session.getAttribute(key);
                    if (obj instanceof ProCart) {
                        ProCart proCart = (ProCart) obj;
                        vector.add(proCart);
                    }
                }

                double subtotal = 0;
                double total = 0;
                for (ProCart proCart : vector) {
                    subtotal = (proCart.getQuantity() * proCart.getUnitPrice()) - (proCart.getDiscount() * proCart.getUnitPrice() * proCart.getDiscount());
                    total += subtotal;
                }

                request.setAttribute("total", total);
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);
            }
            if (service.equals("removePro")) {
                int pid = Integer.parseInt(request.getParameter("pID"));
                ProCart proCart = (ProCart) session.getAttribute(pid + "");

                if (proCart != null) {
                    int currentQuantity = proCart.getQuantity();
                    if (currentQuantity > 1) {
                        proCart.setQuantity(currentQuantity - 1);
                        session.setAttribute(pid + "", proCart);
                    } else {
                        session.removeAttribute(pid + "");
                    }
                }

                Vector<ProCart> vector = new Vector<ProCart>();
                Enumeration<String> keyList = session.getAttributeNames();
                while (keyList.hasMoreElements()) {
                    String key = keyList.nextElement();
                    Object obj = session.getAttribute(key);
                    if (obj instanceof ProCart) {
                        vector.add((ProCart) obj);
                    }
                }

                double subtotal = 0;
                double total = 0;
                for (ProCart ProCart : vector) {
                    subtotal = (ProCart.getQuantity() * ProCart.getUnitPrice()) - (ProCart.getDiscount() * ProCart.getUnitPrice() * ProCart.getDiscount());
                    total += subtotal;
                }

                request.setAttribute("total", total);
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/jsp/showCart.jsp").forward(request, response);
            }

            if (service.equals("removeAll")) {
                Vector<ProCart> vector = new Vector<ProCart>();
                Enumeration<String> keyList = session.getAttributeNames();
                while (keyList.hasMoreElements()) {
                    String key = keyList.nextElement();
                    Object obj = session.getAttribute(key);
                    if (obj instanceof ProCart) {
                        session.removeAttribute(key);
                    }
                }

                double subtotal = 0;
                double total = 0;
                for (ProCart proCart : vector) {
                    subtotal = (proCart.getQuantity() * proCart.getUnitPrice()) - (proCart.getDiscount() * proCart.getUnitPrice() * proCart.getDiscount());
                    total += subtotal;
                }

                request.setAttribute("total", total);
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);
            }

            if (service.equals("updateQuantity")) {
                if (request.getParameter("submit") == null) {
                    Vector<ProCart> vector = new Vector<ProCart>();
                    Enumeration<String> keyList = session.getAttributeNames();
                    while (keyList.hasMoreElements()) {
                        String key = keyList.nextElement();
                        Object obj = session.getAttribute(key);
                        if (obj instanceof ProCart) {
                            ProCart proCart = (ProCart) obj;
                            vector.add(proCart);
                        }
                    }

                    double subtotal = 0;
                    double total = 0;
                    for (ProCart proCart : vector) {
                        subtotal = proCart.getQuantity() * proCart.getUnitPrice() * (1 - proCart.getDiscount());
                        total += subtotal;
                    }

                    request.setAttribute("total", total);
                    request.setAttribute("vector", vector);
                    request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);
                } else {
                    Vector<ProCart> vector = new Vector<ProCart>();
                    Enumeration<String> keyList = session.getAttributeNames();
                    while (keyList.hasMoreElements()) {
                        String key = keyList.nextElement();
                        Object obj = session.getAttribute(key);
                        if (obj instanceof ProCart) {
                            ProCart proCart = (ProCart) obj;
                            vector.add(proCart);
                        }
                    }
                    Enumeration<String> namePacket = request.getParameterNames();
                    while (namePacket.hasMoreElements()) {
                        String name = namePacket.nextElement();
                        if (name.startsWith("quantity__")) {
                            int pid = Integer.parseInt(name.substring("quantity__".length()));
                            int newQuantity = Integer.parseInt(request.getParameter("quantity__" + name.substring("quantity__".length())));

                            if (newQuantity == 0) {
                                session.removeAttribute(String.valueOf(pid));
                                for (ProCart proCart : vector) {
                                    if (proCart.getProductID()== pid) {
                                        vector.remove(proCart);
                                        break;
                                    }
                                }
                            } else {
                                for (ProCart proCart : vector) {
                                    if (proCart.getProductID() == pid) {
                                        proCart.setQuantity(newQuantity);
                                        session.setAttribute(String.valueOf(pid), proCart);
                                        break;
                                    }
                                }
                            }

                        }
                    }

                    double subtotal = 0;
                    double total = 0;
                    for (ProCart proCart : vector) {
                        subtotal = proCart.getQuantity() * proCart.getUnitPrice() * (1 - proCart.getDiscount());

                        total += subtotal;
                    }

                    request.setAttribute("total", total);
                    request.setAttribute("vector", vector);
                    request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);

                }
            }

            if (service.equals("checkout")) {

                String username = (String) session.getAttribute("username");
                if (username == null || username.trim().isEmpty()) {
                    request.setAttribute("message", "Bạn cần đăng nhập trước khi thanh toán!");
                    request.getRequestDispatcher("jsp/loginCustomer.jsp").forward(request, response);
                    return; // dừng xử lý tiếp theo
                }
                Vector<ProCart> vector = new Vector<>();
                Enumeration<String> keyList = session.getAttributeNames();
                while (keyList.hasMoreElements()) {
                    String key = keyList.nextElement();
                    Object obj = session.getAttribute(key);
                    if (obj instanceof ProCart) {
                        ProCart proCart = (ProCart) obj;
                        vector.add(proCart);
                    }
                }

                if (vector.isEmpty()) {
                    request.setAttribute("message", "Your cart is empty!");
                    request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);
                    return;
                }

                // Lấy thông tin khách hàng
                
                String sql = "SELECT * FROM Customers WHERE CustomerID = '" + username + "'";
                Customer cus = daoCus.getCustomer(sql).get(0);

                // Đặt hàng
                String CustomerID = cus.getCustomerID();
                String ShipAddress = cus.getAddress();
                String ShipCity = cus.getCity();
                String ShipRegion = cus.getRegion();
                String ShipPostalCode = cus.getPostalCode();
                String ShipCountry = cus.getCountry();

                Date OrderDate = new Date(System.currentTimeMillis());
                LocalDate currentDate = LocalDate.now();
                Date RequiredDate = Date.valueOf(currentDate.plusDays(10));
                Date ShippedDate = Date.valueOf(currentDate.plusDays(7));

                Orders od = new Orders(0, CustomerID, 1, OrderDate, RequiredDate, ShippedDate, 1, 0,
                        ShipCity, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);

                // Lưu đơn hàng và nhận OrderID
                int orderId = dAOOrders.addOrder(od); // Giả sử hàm này trả về OrderID vừa tạo
                od.setOrderID(orderId); // Cập nhật lại orderID cho đơn hàng

                // Lưu chi tiết đơn hàng
                double total = 0;
                for (ProCart proCart : vector) {
                    double subtotal = proCart.getQuantity() * proCart.getUnitPrice() * (1 - proCart.getDiscount());
                    total += subtotal;

                    dadetail.addOrderDetail(new OrderDetails(orderId, proCart.getProductID(),
                            proCart.getUnitPrice(), proCart.getQuantity(), proCart.getDiscount()));
                }

                // Xoá riêng các ProCart khỏi session
                keyList = session.getAttributeNames();
                List<String> toRemove = new ArrayList<>();
                while (keyList.hasMoreElements()) {
                    String key = keyList.nextElement();
                    if (session.getAttribute(key) instanceof ProCart) {
                        toRemove.add(key);
                    }
                }
                for (String key : toRemove) {
                    session.removeAttribute(key);
                }

                // Set thông tin phản hồi cho JSP
                request.setAttribute("message", "Checkout successful! Your Order ID is: " + orderId);
                request.setAttribute("total", total);
                request.setAttribute("vector", vector);

                // Forward request MỘT lần duy nhất ở cuối cùng
                request.getRequestDispatcher("jsp/showCart.jsp").forward(request, response);
            }

//            if (service.equals("showcart")) {
//                Vector<ProCart> vector = new Vector<ProCart>();
//                Enumeration<String> keyList = session.getAttributeNames();
//                while (keyList.hasMoreElements()) {
//                    String key = keyList.nextElement();
//                    Object obj = session.getAttribute(key);
//                    if (obj instanceof ProCart) {
//                        ProCart proCart = (ProCart) obj;
//                        vector.add(proCart);
//                    }
//                }
//                double total = 0;
//                for (ProCart proCart : vector) {
//                    total += proCart.getUnitPrice() * proCart.getQuantity();
//                }
//                request.setAttribute("total", total);
//                request.setAttribute("vectorCart", vector);
//                request.getRequestDispatcher("/jsp/showCart.jsp").forward(request, response);
//            }
            if (service.equals("add2cart")) {

                int pid = Integer.parseInt(request.getParameter("pid"));
                ProCart proCart = (ProCart) session.getAttribute(pid + "");
                if (proCart == null) { // firstTime --> create new ProCart
                    proCart = dao.getProductCart("select * from products "
                            + " where ProductID=" + pid).get(0);
                } else {
                    proCart.setQuantity(proCart.getQuantity() + 1);
                }
                session.setAttribute(pid + "", proCart);
                response.setContentType("text/plain");
                response.getWriter().write("success");
            }
//            if (service.equals("removeCart")) {
//                int pid = Integer.parseInt(request.getParameter("pid"));
//                ProCart proCart = (ProCart) session.getAttribute(pid + "");
//
//                if (proCart != null) {
//                    int currentQuantity = proCart.getQuantity();
//                    if (currentQuantity > 1) {
//                        proCart.setQuantity(currentQuantity - 1);
//                        session.setAttribute(pid + "", proCart);
//                    } else {
//                        session.removeAttribute(pid + "");
//                    }
//                }
//
//                Vector<ProCart> vector = new Vector<ProCart>();
//                Enumeration<String> keyList = session.getAttributeNames();
//                while (keyList.hasMoreElements()) {
//                    String key = keyList.nextElement();
//                    Object obj = session.getAttribute(key);
//                    if (obj instanceof ProCart) {
//                        vector.add((ProCart) obj);
//                    }
//                }
//
//                double total = 0;
//                for (ProCart cartItem : vector) {
//                    total += cartItem.getUnitPrice() * cartItem.getQuantity();
//                }
//
//                request.setAttribute("total", total);
//                request.setAttribute("vectorCart", vector);
//                request.getRequestDispatcher("/jsp/showCart.jsp").forward(request, response);
//            }
//            if (service.equals("removeCartALL")) {
//                // Duyệt qua tất cả attribute trong session
//                Enumeration<String> keyList = session.getAttributeNames();
//                while (keyList.hasMoreElements()) {
//                    String key = keyList.nextElement();
//                    Object obj = session.getAttribute(key);
//                    // Nếu attribute là đối tượng ProCart thì loại bỏ nó khỏi session
//                    if (obj instanceof ProCart) {
//                        session.removeAttribute(key);
//                    }
//                }
//
//                // Tạo vector rỗng để hiển thị giỏ hàng trống
//                Vector<ProCart> vector = new Vector<ProCart>();
//                double total = 0;
//
//                request.setAttribute("total", total);
//                request.setAttribute("vectorCart", vector);
//                request.getRequestDispatcher("/jsp/showCart.jsp").forward(request, response);
//            }

//            if (service.equals("updateCart")) {
//                String submit = request.getParameter("submit");
//                // Nếu form chưa bấm nút Update, có thể xử lý khác hoặc redirect
//                if (submit == null) {
//                    response.sendRedirect("/jsp/showCart.jsp");
//                    return;
//                }
//
//                // Bước 1: Lấy toàn bộ cart từ session
//                Vector<ProCart> vector = new Vector<>();
//                Enumeration<String> keyList = session.getAttributeNames();
//                while (keyList.hasMoreElements()) {
//                    String key = keyList.nextElement();
//                    Object obj = session.getAttribute(key);
//                    if (obj instanceof ProCart) {
//                        vector.add((ProCart) obj);
//                    }
//                }
//
//                // Bước 2: Duyệt tất cả param "quantity_..."
//                Enumeration<String> paramNames = request.getParameterNames();
//                while (paramNames.hasMoreElements()) {
//                    String paramName = paramNames.nextElement();
//                    if (paramName.startsWith("quantity_")) {
//                        // Lấy productID từ tên tham số, vd: "quantity_3" -> pidStr = "3"
//                        String pidStr = paramName.substring("quantity_".length());
//                        int pid = Integer.parseInt(pidStr);
//
//                        // Lấy quantity mới
//                        String quantityStr = request.getParameter(paramName);
//                        int newQuantity = Integer.parseInt(quantityStr);
//
//                        // Tìm đối tượng ProCart trong vector có productID = pid, rồi setQuantity
//                        for (ProCart proCart : vector) {
//                            if (proCart.getProductID() == pid) {
//                                proCart.setQuantity(newQuantity);
//                                // Nếu bạn đang lưu từng ProCart trong session với key=pid
//                                // thì cập nhật session luôn:
//                                session.setAttribute(String.valueOf(pid), proCart);
//                                break;
//                            }
//                        }
//                    }
//                }
//
//                // Bước 3: Tính tổng
//                double total = 0;
//                for (ProCart proCart : vector) {
//                    total += proCart.getUnitPrice() * proCart.getQuantity();
//                }
//
//                // Bước 4: Set attribute để hiển thị
//                request.setAttribute("total", total);
//                request.setAttribute("vectorCart", vector);
//
//                // Bước 5: Forward về trang showCart.jsp
//                request.getRequestDispatcher("/jsp/showCart.jsp").forward(request, response);
//            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
