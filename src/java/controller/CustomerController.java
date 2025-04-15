/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOCustomer;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerURL"})
public class CustomerController extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listCustomers";
            }
            if (service.equals("logoutCustomer")) {
                session.invalidate();
                response.sendRedirect("home");
            }
            if (service.equals("loginCustomer")) {
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String userName = request.getParameter("userName");
                    String pass = request.getParameter("pass");
                    if (userName.equals("admin") && pass.equals("123456")) {
                        session.setAttribute("username", userName);
                         session.setAttribute("userRole", "admin"); // Gán quyền Admin
                        response.sendRedirect("admin");
                        return;
                    }

                    boolean flag = dao.loginCustomer(userName, pass);
                    if (flag == true) {
                        session.setAttribute("userRole", "user"); // Gán quyền User
                        session.setAttribute("username", userName);
                        response.sendRedirect("home");
                    } else {
                        request.setAttribute("message", "Login faile");
                        request.getRequestDispatcher("/jsp/loginCustomer.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("/jsp/loginCustomer.jsp").forward(request, response);
                }
            }
            if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String cid = request.getParameter("CustomerID");
                    String cname = request.getParameter("CompanyName");
                    String ccontactname = request.getParameter("ContactName");
                    String ccontacttitle = request.getParameter("ContactTitle");
                    String caddress = request.getParameter("Address");
                    String ccity = request.getParameter("City");
                    String cpostalcode = request.getParameter("PostalCode");
                    String cregion = request.getParameter("Region");
                    String ccountry = request.getParameter("Country");
                    String cphone = request.getParameter("Phone");
                    String cfax = request.getParameter("Fax");

                    Customer cus = new Customer(cid, cname, ccontactname, ccontacttitle,
                            caddress, ccity, cregion, cpostalcode,
                            ccountry, cphone, cfax);

                    int n = dao.updateCustomer(cus);
                    if (n > 0) {
                        response.sendRedirect("admin?service=listCustomer");
                    } else {
                        out.print("Update failed!");
                    }
                } else {
                    String cid = request.getParameter("cid");
                    getServletContext().log(cid);
                    String sql = "SELECT * FROM Customers WHERE CustomerID = '"+cid+"' ";
                    Customer cus = dao.getCustomer(sql).get(0);
                    request.setAttribute("cus", cus);
                    request.getRequestDispatcher("/jsp/updateCustomer.jsp").forward(request, response);
                }
            }
            

            if (service.equals("insertCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("/jsp/insertCustomer.jsp").forward(request, response);
                } else {
                    String cid = request.getParameter("CustomerID");
                    String cname = request.getParameter("CompanyName");
                    String ccontactname = request.getParameter("ContactName");
                    String ccontacttitle = request.getParameter("ContactTitle");
                    String caddress = request.getParameter("Address");
                    String ccity = request.getParameter("City");
                    String cpostalcode = request.getParameter("PostalCode");
                    String cregion = request.getParameter("Region");
                    String ccountry = request.getParameter("Country");
                    String cphone = request.getParameter("Phone");
                    String cfax = request.getParameter("Fax");

                    // Tạo đối tượng Customer mới
                    Customer cus = new Customer(cid, cname, ccontactname, ccontacttitle,
                            caddress, ccity, cregion, cpostalcode,
                            ccountry, cphone, cfax);

                    // Thực hiện insert
                    int n = dao.addCustomer(cus);
                    if (n > 0) {
                        session.setAttribute("username", cname);
                        response.sendRedirect("home");
                    } else {
                        out.print("Insert failed!");
                    }
                }
            }

            if (service.equals("deleteCustomer")) {
                String cid = request.getParameter("cid");
                int n = dao.removeCustomer(cid);
                if (n == 0) {
                    out.print("delete fail");
                } else if (n == -1) {
                    out.print("foreign key constraint");
                } else {
                    response.sendRedirect("admin?service=listCustomer");
                }
            }
            if (service.equals("listCustomers")) {
                String submit = request.getParameter("submit");

                Vector<Customer> vector;

                if (submit == null) {
                    vector = dao.getCustomer("Select * From Customers");

                } else {
                    String cname = request.getParameter("cname");
                    vector = dao.getCustomer("SELECT * FROM Customers WHERE CompanyName LIKE '%" + cname + "%';");
                }
                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/jsp/listCustomers.jsp").forward(request, response);

            }

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
