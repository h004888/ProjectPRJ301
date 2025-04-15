/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Products;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOProducts;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductController", urlPatterns = { "/ProductURL" })
public class ProductController extends HttpServlet {

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
        DAOProducts dao = new DAOProducts();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listProducts";
            }
            if (service.equals("deleteProduct")) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                int n = dao.removeProduct(pid);
                if (n == 0) {
                    out.print("delete fail");
                } else if (n == -1) {
                    out.print("foreign key constraint");
                } else {
                    response.sendRedirect("ProductURL");
                }
            }
            if (service.equals("updateProducts")) {
                String submit = request.getParameter("submit");

                if (submit == null) {
                    int pid = Integer.parseInt(request.getParameter("pid"));
                    Vector<Products> vector = dao.getProducts("SELECT * FROM Products WHERE ProductID = " + pid);
                    request.setAttribute("vector", vector);
                    ResultSet rsCate = dao.getData("select CategoryID, CategoryName from Categories");
                    ResultSet rsSupp = dao.getData("select SupplierID, CompanyName from Suppliers");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("rsSupp", rsSupp);
                    request.getRequestDispatcher("/jsp/updateProduct.jsp").forward(request, response);
                } else {
                    int ProductID = Integer.parseInt(request.getParameter("ProductID"));
                    String ProductName = request.getParameter("ProductName");
                    int SupplierID = Integer.parseInt(request.getParameter("SupplierID")),
                            CategoryID = Integer.parseInt(request.getParameter("CategoryID"));
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    double UnitPrice = Double.parseDouble(request.getParameter("UnitPrice"));
                    int UnitsInStock = Integer.parseInt(request.getParameter("UnitsInStock")),
                            UnitsOnOrder = Integer.parseInt(request.getParameter("UnitsOnOrder")),
                            ReorderLevel = Integer.parseInt(request.getParameter("ReorderLevel"));
                    boolean Discontinued = (Integer.parseInt(
                            request.getParameter("Discontinued")) == 1 ? true : false);
                    Products pro = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit,
                            UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                    dao.updateProduct(pro);
                    response.sendRedirect("admin");
                }

            }
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    ResultSet rsCate = dao.getData("select CategoryID, CategoryName from Categories");
                    ResultSet rsSupp = dao.getData("select SupplierID, CompanyName from Suppliers");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("rsSupp", rsSupp);
                    request.getRequestDispatcher("/jsp/insertProduct.jsp").forward(request, response);
                } else {
                    int ProductID = Integer.parseInt(request.getParameter("ProductID"));
                    String ProductName = request.getParameter("ProductName");
                    int SupplierID = Integer.parseInt(request.getParameter("SupplierID")),
                            CategoryID = Integer.parseInt(request.getParameter("CategoryID"));
                    String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                    double UnitPrice = Double.parseDouble(request.getParameter("UnitPrice"));
                    int UnitsInStock = Integer.parseInt(request.getParameter("UnitsInStock")),
                            UnitsOnOrder = Integer.parseInt(request.getParameter("UnitsOnOrder")),
                            ReorderLevel = Integer.parseInt(request.getParameter("ReorderLevel"));
                    boolean Discontinued = (Integer.parseInt(
                            request.getParameter("Discontinued")) == 1 ? true : false);
                    Products pro = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit,
                            UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                    dao.addProduct(pro);
                    response.sendRedirect("ProductURL");
                }

            }
            if (service.equals("listProducts")) {

                Vector<Products> vector;
                String submit = request.getParameter("submit");
                if (submit == null) {
                    vector = dao.getProducts("select* from Products");
                } else {
                    String pname = request.getParameter("pname");
                    vector = dao.getProducts("select* from Products where ProductName like '%" + pname + "%'");
                }

                // set data view jsp
                request.setAttribute("productData", vector);
                request.setAttribute("titleTable", "Product table");
                // select view (jsp)
                RequestDispatcher dis = request.getRequestDispatcher("/jsp/listProduct.jsp");
                // run
                dis.forward(request, response);
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
