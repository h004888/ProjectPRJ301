/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Employees;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOEmployees;
import java.sql.Date;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EmployeesController", urlPatterns = {"/EmployeesURL"})
public class EmployeesController extends HttpServlet {

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
        DAOEmployees dao = new DAOEmployees();
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            if (service == null) {
                service = "listEmployees";
            }
            if (service.equals("loginEmployee")) {
                if (request.getParameter("submit") == null) {
                    request.getRequestDispatcher("jsp/loginEmployee.jsp").forward(request, response);
                } else {
                    String name = request.getParameter("username");
                    String pasword = request.getParameter("password");
                    boolean flag = dao.loginEmployee(name, pasword);
                    if (flag == true) {
                        session.setAttribute("username", name);
                        response.sendRedirect("ProductURL");
                    } else {

                        request.getRequestDispatcher("jsp/loginEmployee.jsp").forward(request, response);
                    }
                }
            }

            if (service.equals("removeEmployees")) {
                int empID = Integer.parseInt(request.getParameter("empID"));
                int n = dao.removeEmployee(empID);
                if (n == 0) {
                    out.print("delete fail");
                } else if (n == -1) {
                    out.print("foreign key constraint");
                } else {
                    response.sendRedirect("EmployeesURL");
                }

            }

//            if (service.equals("loginEmployee")) {
//                if (request.getParameter("submit") != null) {
//                    String username = request.getParameter("username");
//                    String password = request.getParameter("password");
//                    boolean flag = dao.loginEmployee(username, password);
//                    if (flag == true) {
//                        session.setAttribute("username", username);
//                        response.sendRedirect("ProductURL");
//                    } else {
//                        request.setAttribute("message", "Login faile");
//                        request.getRequestDispatcher("/jsp/loginEmployee.jsp").forward(request, response);
//                    }
//                } else {
//                    request.getRequestDispatcher("/jsp/loginEmployee.jsp").forward(request, response);
//                }
//            }
            if ("updateEmployees".equals(service)) {
                if (request.getParameter("submit") == null) {
                    // Đổi từ empID thành employeeID cho đúng tham số
                    int empID = Integer.parseInt(request.getParameter("empID"));

                    Vector<Employees> vector = dao.getEmployee("Select * from Employees where EmployeeID = " + empID);
                    request.setAttribute("Emp", vector.get(0));
                    request.setAttribute("goi", "Hello Linh Loi Nhoi");
                    request.getRequestDispatcher("/jsp/updateEmployee.jsp").forward(request, response);
                } else {
                    int EmployeeID = Integer.parseInt(request.getParameter("employeeID"));
                    String LastName = request.getParameter("LastName");
                    String FirstName = request.getParameter("FirstName");
                    String Title = request.getParameter("Title");
                    String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                    Date BirthDate = Date.valueOf(request.getParameter("BirthDate"));
                    Date HireDate = Date.valueOf(request.getParameter("HireDate"));
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String HomePhone = request.getParameter("HomePhone");
                    String Extension = request.getParameter("Extension");
                    byte[] Photo = request.getParameter("Photo").getBytes();

                    String Notes = request.getParameter("Notes");
                    int ReportsTo = Integer.parseInt(request.getParameter("reportsTo"));
                    String PhotoPath = request.getParameter("PhotoPath");

                    int n = dao.updateEmployee(new Employees(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy,
                            BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension,
                            Photo, Notes, ReportsTo, PhotoPath));
                    response.sendRedirect("EmployeesURL");
                }
            }

            if (service.equals("listEmployees")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    Vector<Employees> vector = dao.getEmployee("SElect * from Employees");
                    String test = "TestController";
                    request.setAttribute("goi", test);
                    request.setAttribute("listEmployee", vector);
                    request.getRequestDispatcher("/jsp/listEmployee.jsp").forward(request, response);
                } else {
                    String empName = request.getParameter("empName");
                    Vector<Employees> vector = dao
                            .getEmployee("SElect * from Employees Where FirstName like '%" + empName + "%'");
                    String test = "TestController";
                    request.setAttribute("goi", test);
                    request.setAttribute("listEmployee", vector);
                    request.getRequestDispatcher("/jsp/listEmployee.jsp").forward(request, response);
                }

            }
            if (service.equals("insertEmployee")) {
                if (request.getParameter("submit") == null) {
                    request.setAttribute("goi", "Hello Linh Loi Nhoi");
                    request.getRequestDispatcher("/jsp/insertEmployee.jsp").forward(request, response);
                } else {
                    String rs = request.getParameter("reportsTo");

                    int EmployeeID = Integer.parseInt(request.getParameter("employeeID"));
                    String LastName = request.getParameter("LastName");
                    String FirstName = request.getParameter("FirstName");
                    String Title = request.getParameter("Title");
                    String TitleOfCourtesy = request.getParameter("TitleOfCourtesy");
                    Date BirthDate = Date.valueOf(request.getParameter("BirthDate"));
                    Date HireDate = Date.valueOf(request.getParameter("HireDate"));
                    String Address = request.getParameter("Address");
                    String City = request.getParameter("City");
                    String Region = request.getParameter("Region");
                    String PostalCode = request.getParameter("PostalCode");
                    String Country = request.getParameter("Country");
                    String HomePhone = request.getParameter("HomePhone");
                    String Extension = request.getParameter("Extension");
                    byte[] Photo = request.getParameter("Photo").getBytes();

                    String Notes = request.getParameter("Notes");
                    int ReportsTo = Integer.parseInt(request.getParameter("reportsTo"));

                    String PhotoPath = request.getParameter("PhotoPath");
                    int n = dao.insertEmployee(new Employees(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy,
                            BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension,
                            Photo, Notes, ReportsTo, PhotoPath));
                    if (n > 0) {
                        response.sendRedirect("EmployeesURL");
                    } else {
                        out.print("Lỗi rồi");
                    }

                }
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
