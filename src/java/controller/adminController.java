/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Employees;
import entity.Orders;
import entity.Products;
import jakarta.servlet.RequestDispatcher;
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
import model.DAOProducts;
import model.DAOOrderDetails;
import entity.OrderDetails;
import entity.Bill;
import model.DAOBill;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "adminController", urlPatterns = {"/admin"})
public class adminController extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer();
        DAOProducts daoPro = new DAOProducts();
        DAOOrders daoOrd = new DAOOrders();
        DAOEmployees daoEmp = new DAOEmployees();
        DAOOrderDetails daoOdt = new DAOOrderDetails();
        DAOBill dAOBill = new DAOBill();
        // Lấy tham số "service" từ request
        String service = request.getParameter("service");

        // Nếu service == null, điều hướng về trang dashboard
        if (service == null) {
            request.getRequestDispatcher("jsp/adminDash.jsp").forward(request, response);
            return; // Dừng việc thực thi để tránh lỗi
        }

        // Kiểm tra service không bị null trước khi so sánh
        if ("listCustomer".equals(service)) {
            request.setAttribute("service", "listCustomer");
            String submit = request.getParameter("submit");

            Vector<Customer> vector;

            if (submit == null) {
                vector = dao.getCustomer("Select * From Customers");

            } else {
                String cname = request.getParameter("cname");
                vector = dao.getCustomer("SELECT * FROM Customers WHERE CompanyName LIKE '%" + cname + "%';");
            }
            request.setAttribute("vector", vector);

            request.getRequestDispatcher("jsp/adminDash.jsp").forward(request, response);

        }

        if ("listBill".equals(service)) {
            Vector<Bill> list = dAOBill.getListBill();
            request.setAttribute("listBill", list);
            request.setAttribute("service", "listBill");
            request.getRequestDispatcher("/jsp/adminDash.jsp").forward(request, response);
        }
        if ("listProduct".equals(service)) {
            Vector<Products> vector = daoPro.getProducts("Select * From Products");
            request.setAttribute("vector", vector);
            request.setAttribute("service", "listProduct");
            request.setAttribute("test", "tesst");
            request.getRequestDispatcher("/jsp/adminDash.jsp").forward(request, response);
        }

        if ("listEmployee".equals(service)) {
            Vector<Employees> vector = daoEmp.getEmployee("SElect * from Employees");
            String test = "TestController";
            request.setAttribute("goi", test);
            request.setAttribute("listEmployee", vector);
            request.setAttribute("service", "listEmployee");

            request.getRequestDispatcher("/jsp/adminDash.jsp").forward(request, response);
        }
        if ("listOrders".equals(service)) {
            Vector<Orders> vector = daoOrd.getOrders("Select * from Orders");
            request.setAttribute("vector", vector);
            request.setAttribute("service", "listOrders");
            request.getRequestDispatcher("/jsp/adminDash.jsp").forward(request, response);

        }

        if ("listOrderDetails".equals(service)) {
            Vector<OrderDetails> vector = daoOdt.getOrderDetails("SELECT * FROM [Order Details]");
            request.setAttribute("vector", vector);
            request.setAttribute("service", "listOrderDetails");
            request.getRequestDispatcher("/jsp/adminDash.jsp").forward(request, response);
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
