/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.OrderDetails;
import entity.Orders;
import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;
import model.DAOOrderDetails;
import model.DAOOrders;
import model.DAOProducts;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "OrderDetailsController", urlPatterns = { "/OrderDetailsURL" })
public class OrderDetailsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOOrderDetails dao = new DAOOrderDetails();
        DAOOrders daoOrders = new DAOOrders();
        DAOProducts daoProducts = new DAOProducts();

        String service = request.getParameter("service");
        if (service == null) {
            service = "listOrderDetails";
        }
        if (service.equals("listOrderDetails")) {
            if (request.getParameter("submit") == null) {
                Vector<OrderDetails> orderDetails = dao.getOrderDetails("SELECT * FROM [Order Details]");
                request.setAttribute("orderDetails", orderDetails);
                request.getRequestDispatcher("/jsp/listOrderDetails.jsp").forward(request, response);
            } else {
                String orderID = request.getParameter("orID");
                Vector<OrderDetails> orderDetails = dao
                        .getOrderDetails("SELECT * FROM [Order Details] where OrderID = " + orderID);
                getServletContext().log(orderDetails.get(0).toString());
                request.setAttribute("orderDetails", orderDetails);
                request.getRequestDispatcher("/jsp/listOrderDetails.jsp").forward(request, response);
            }
        }
        if (service.equals("insertOdt")) {
            if (request.getParameter("submit") == null) {
                Vector<Orders> orders = daoOrders.getOrders("SELECT * FROM [Orders]");
                Vector<Products> products = daoProducts.getProducts("SELECT * FROM [Products]");
                request.setAttribute("orders", orders);
                request.setAttribute("products", products);
                request.getRequestDispatcher("/jsp/insertOrderDetail.jsp").forward(request, response);
            } else {
                String orderID = request.getParameter("OrderID");
                String productID = request.getParameter("ProductID");
                String unitPrice = request.getParameter("UnitPrice");
                String quantity = request.getParameter("Quantity");
                String discount = request.getParameter("Discount");
                int n = dao.addOrderDetail(new OrderDetails(Integer.parseInt(orderID), Integer.parseInt(productID),
                        Double.parseDouble(unitPrice), Integer.parseInt(quantity), Double.parseDouble(discount)));
                if (n > 0) {
                    response.sendRedirect("OrderDetailsURL");
                }
            }
        }
        if (service.equals("deleteOdt")) {
            String orderID = request.getParameter("OrderID");
            String productID = request.getParameter("ProductID");
            int n = dao.deleteOrderDetail(Integer.parseInt(orderID), Integer.parseInt(productID));
            if (n > 0) {
                response.sendRedirect("admin?service=listOrderDetails");
            }
        }

        if ("serachByOID".equals(service)) {
            String oid = request.getParameter("oid");
            Vector<OrderDetails> orderDetails = dao
                    .getOrderDetails("SELECT * FROM [Order Details] where OrderID = " + oid);
            getServletContext().log(orderDetails.get(0).toString());
            int count = 0;
            double total = 0;
            for (OrderDetails orderDetail : orderDetails) {
                count++;
                total += (double) (orderDetail.getUnitPrice() * orderDetail.getQuantity()
                        * (double) (1 - orderDetail.getDiscount()));
            }
            NumberFormat nf = new DecimalFormat();
            String totalfomat = nf.format(total);
            request.setAttribute("total", totalfomat);
            request.setAttribute("count", count);
            request.setAttribute("orderDetails", orderDetails);
            request.getRequestDispatcher("/jsp/listOrderDetails.jsp").forward(request, response);

        }
        if (service.equals("updateOdt")) {
            if (request.getParameter("submit") == null) {
                Vector<Orders> orders = daoOrders.getOrders("SELECT * FROM [Orders]");
                Vector<Products> products = daoProducts.getProducts("SELECT * FROM [Products]");

                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int productID = Integer.parseInt(request.getParameter("productID"));

                Vector<OrderDetails> orderDetails = dao.getOrderDetails(
                        "SELECT * FROM [Order Details] WHERE OrderID = " + orderID +
                                " AND ProductID = " + productID);

                if (orderDetails != null && !orderDetails.isEmpty()) {
                    request.setAttribute("orders", orders);
                    request.setAttribute("products", products);
                    request.setAttribute("ordetail", orderDetails.get(0));
                    request.getRequestDispatcher("/jsp/updateOrdersDetails.jsp").forward(request, response);
                } else {
                    response.sendRedirect("admin?service=listOrderDetails");
                }
            } else {
                String orderID = request.getParameter("OrderID");
                String productID = request.getParameter("ProductID");
                String unitPrice = request.getParameter("UnitPrice");
                String quantity = request.getParameter("Quantity");
                String discount = request.getParameter("Discount");

                try {
                    int oID = Integer.parseInt(orderID);
                    int pID = Integer.parseInt(productID);
                    double uPrice = Double.parseDouble(unitPrice);
                    int qty = Integer.parseInt(quantity);
                    double disc = Double.parseDouble(discount);

                    int n = dao.updateOrderDetail(new OrderDetails(oID, pID, uPrice, qty, disc));
                    if (n > 0) {
                        // Kiểm tra giá trị discount
                        if (Double.parseDouble(discount) < 0 || Double.parseDouble(discount) > 1) {
                            request.setAttribute("errorMessage",
                                    "Giá trị giảm giá không hợp lệ. Vui lòng nhập giá trị từ 0 đến 1.");
                            request.getRequestDispatcher("/jsp/updateOrdersDetails.jsp").forward(request, response);
                            return;
                        }
                        response.sendRedirect("admin?service=listOrderDetails");
                    } else {
                        request.setAttribute("errorMessage", "Cập nhật không thành công");
                        request.getRequestDispatcher("/jsp/updateOrdersDetails.jsp").forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Dữ liệu không hợp lệ");
                    request.getRequestDispatcher("/jsp/updateOrdersDetails.jsp").forward(request, response);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
