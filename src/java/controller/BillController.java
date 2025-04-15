/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOBill;
import model.DAOOrders;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "BillController", urlPatterns = {"/billURL"})
public class BillController extends HttpServlet {

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
        String service = request.getParameter("service");
        DAOOrders daoo = new DAOOrders();
        DAOBill dAOBill = new DAOBill();
        if (service == null) {
            service = "listBill";
        }

        if ("updateBill".equals(service)) {
            String submit = request.getParameter("submit");
            boolean updateResult = false; // Khởi tạo updateResult để tránh lỗi biến chưa khởi tạo
            if (submit == null) {
                String orderID = request.getParameter("orID");
                
                request.setAttribute("orID", orderID);
                request.getRequestDispatcher("jsp/updateBill.jsp").forward(request, response);
            } else {
                String orderID = request.getParameter("orID");
                String select = request.getParameter("select");

                if (orderID == null || orderID.isEmpty()) {
                    request.setAttribute("message", "Order ID is required");
                    request.getRequestDispatcher("jsp/updateBill.jsp").forward(request, response);
                    return;
                }

                if (select == null || select.isEmpty()) {
                    request.setAttribute("message", "Please select a status");
                    request.getRequestDispatcher("jsp/updateBill.jsp").forward(request, response);
                    return;
                }

                try {
                    int parsedOrderID = Integer.parseInt(orderID);
                    switch (select) {
                        case "1":
                            updateResult = dAOBill.updateBillStatus(parsedOrderID, "done");
                            break;
                        case "2":
                            updateResult = dAOBill.updateBillStatus(parsedOrderID, "process");
                            break;
                        case "3":
                            updateResult = dAOBill.updateBillStatus(parsedOrderID, "wait");
                            break;
                        default:
                            request.setAttribute("message", "Invalid selection");
                            request.getRequestDispatcher("jsp/updateBill.jsp").forward(request, response);
                            return;
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Invalid Order ID format");
                    request.getRequestDispatcher("jsp/updateBill.jsp").forward(request, response);
                    return;
                }

                if (updateResult) {
                    request.setAttribute("message", "Update Success");
                } else {
                    request.setAttribute("message", "Update Failed");
                }
                request.getRequestDispatcher("jsp/updateBill.jsp").forward(request, response);
            }
        }

        if ("listBill".equals(service)) {
            Vector<Bill> list = dAOBill.getListBill();
            request.setAttribute("listBill", list);
            request.getRequestDispatcher("/jsp/listBill.jsp").forward(request, response);
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
