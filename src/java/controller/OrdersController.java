/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Employees;
import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCustomer;
import model.DAOEmployees;
import model.DAOOrders;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "OrdersController", urlPatterns = { "/OrderURL" })
public class OrdersController extends HttpServlet {

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
        DAOOrders dao = new DAOOrders();
        DAOCustomer daoCus = new DAOCustomer();
        DAOEmployees daoEmp = new DAOEmployees();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listOrders";
            }
            if (service.equals("deleteOrders")) {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                int n = dao.deleteOrder(orderID);
                if (n > 0) {
                    response.sendRedirect("OrderURL");
                } else if (n == -1) {
                    out.println("Ràng buộc khóa ngoại");
                } else {
                    out.println("Error");
                }
            }
            if (service.equals("updateOrders")) {
                if (request.getParameter("submit") == null) {
                    int orderID = Integer.parseInt(request.getParameter("orderID"));
                    Orders o = dao.getOrders("SELECT * FROM Orders WHERE OrderID = " + orderID).get(0);
                    Vector<Customer> listCus = daoCus.getCustomer("SELECT * FROM Customers");
                    Vector<Employees> listEmp = daoEmp.getEmployee("SELECT * FROM Employees");
                    request.setAttribute("listCus", listCus);
                    request.setAttribute("listEmp", listEmp);
                    request.setAttribute("o", o);
                    request.getRequestDispatcher("/jsp/updateOrders.jsp").forward(request, response);
                } else {
                    int orderID = Integer.parseInt(request.getParameter("OrderID"));
                    String customerID = request.getParameter("CustomerID");
                    int employeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    Date orderDate = Date.valueOf(request.getParameter("OrderDate"));
                    Date requiredDate = Date.valueOf(request.getParameter("RequiredDate"));
                    Date shippedDate = Date.valueOf(request.getParameter("ShippedDate"));
                    int shipVia = Integer.parseInt(request.getParameter("ShipVia"));
                    double freight = Double.parseDouble(request.getParameter("Freight"));
                    String shipName = request.getParameter("ShipName");
                    String shipAddress = request.getParameter("ShipAddress");
                    String shipCity = request.getParameter("ShipCity");
                    String shipRegion = request.getParameter("ShipRegion");
                    String shipPostalCode = request.getParameter("ShipPostalCode");
                    String shipCountry = request.getParameter("ShipCountry");
                    int n = dao.updateOrder(
                            new Orders(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia,
                                    freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry));
                    response.sendRedirect("OrderURL");
                }
            }

            if (service.equals("insertOrders")) {
                if (request.getParameter("submit") == null) {
                    Vector<Customer> listCus = daoCus.getCustomer("SELECT * FROM Customers");
                    Vector<Employees> listEmp = daoEmp.getEmployee("SELECT * FROM Employees");
                    request.setAttribute("listCus", listCus);
                    request.setAttribute("listEmp", listEmp);
                    request.getRequestDispatcher("/jsp/insertOrders.jsp").forward(request, response);
                } else {
                    int orderID = Integer.parseInt(request.getParameter("OrderID"));
                    String customerID = request.getParameter("CustomerID");
                    int employeeID = Integer.parseInt(request.getParameter("EmployeeID"));
                    Date orderDate = Date.valueOf(request.getParameter("OrderDate"));
                    Date requiredDate = Date.valueOf(request.getParameter("RequiredDate"));
                    Date shippedDate = Date.valueOf(request.getParameter("ShippedDate"));
                    int shipVia = Integer.parseInt(request.getParameter("ShipVia"));
                    double freight = Double.parseDouble(request.getParameter("Freight"));
                    String shipName = request.getParameter("ShipName");
                    String shipAddress = request.getParameter("ShipAddress");
                    String shipCity = request.getParameter("ShipCity");
                    String shipRegion = request.getParameter("ShipRegion");
                    String shipPostalCode = request.getParameter("ShipPostalCode");
                    String shipCountry = request.getParameter("ShipCountry");
                    int n = dao.addOrder(
                            new Orders(orderID, customerID, employeeID, orderDate, requiredDate, shippedDate, shipVia,
                                    freight, shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipCountry));
                    response.sendRedirect("OrderURL");

                }

            }
            if (service.equals("listOrders")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    Vector<Orders> vector = dao.getOrders("SElect * from Orders");
                    String test = "TestController";
                    request.setAttribute("goi", test);
                    request.setAttribute("listOrders", vector);
                    request.getRequestDispatcher("/jsp/listOrders.jsp").forward(request, response);
                } else {
                    String customerID = request.getParameter("cID");
                    Vector<Orders> vector = dao
                            .getOrders("SElect * from Orders Where OrderID = " + customerID + "");
                    String test = "TestController";
                    request.setAttribute("goi", test);
                    request.setAttribute("listOrders", vector);
                    request.getRequestDispatcher("/jsp/listOrders.jsp").forward(request, response);
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
