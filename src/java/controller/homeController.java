/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategories;
import model.DAOProducts;
import entity.Categories;
import entity.Products;

/**
 *
 * @author ADMIN
 */
public class homeController extends HttpServlet {

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
        DAOProducts dao = new DAOProducts();
        DAOCategories daoCat = new DAOCategories();

        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {

                // Truy vấn tất cả sản phẩm nếu không chọn category
                Vector<Products> vectorPro = dao.getProducts("SELECT * FROM Products");
                Vector<Categories> vectorCat = daoCat.getCategories("SELECT * FROM Categories");

                request.setAttribute("vectorCat", vectorCat);
                request.setAttribute("vectorPro", vectorPro);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else if (service.equals("ProductByCid")) {
                // Kiểm tra nếu status null hoặc không phải "1"
                String cid = request.getParameter("cid");
                if (cid != null) { // Kiểm tra cid tránh lỗi SQL
                    String sqlSelect = "Select * from Products where CategoryID = " + cid;
                    Vector<Products> vectorPro = dao.getProducts(sqlSelect);
                    Vector<Categories> vectorCat = daoCat.getCategories("SELECT * FROM Categories");

                    request.setAttribute("tag", cid);
                    request.setAttribute("vectorCat", vectorCat);
                    request.setAttribute("vectorPro", vectorPro);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }

            }

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
