/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Employees;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class DAOEmployees extends DBConnect {
    
    
    public boolean loginEmployee(String name , String pass){
        String sql = "select * from Employees where LastName = ? "
                + " and FirstName= ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int updateEmployee(Employees emp) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employees] SET "
                + "[LastName] = ?, "
                + "[FirstName] = ?, "
                + "[Title] = ?, "
                + "[TitleOfCourtesy] = ?, "
                + "[BirthDate] = ?, "
                + "[HireDate] = ?, "
                + "[Address] = ?, "
                + "[City] = ?, "
                + "[Region] = ?, "
                + "[PostalCode] = ?, "
                + "[Country] = ?, "
                + "[HomePhone] = ?, "
                + "[Extension] = ?, "
                + "[Photo] = ?, "
                + "[Notes] = ?, "
                + "[ReportsTo] = ?, "
                + "[PhotoPath] = ? "
                + "WHERE [EmployeeID] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setDate(5, emp.getBirthDate());
            pre.setDate(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setBytes(14, emp.getPhoto());
            pre.setString(15, emp.getNotes());
            pre.setInt(16, emp.getReportsTo());
            pre.setString(17, emp.getPhotoPath());
            pre.setInt(18, emp.getEmployeeID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    
    

//    public boolean loginEmployee(String user, String pass) {
//        String sql = "select * from Employees where LastName = ? "
//                + " and FirstName= ?";
//        PreparedStatement pre;
//        try {
//            pre = conn.prepareStatement(sql);
//            pre.setString(1, user);
//            pre.setString(2, pass);
//            ResultSet rs = pre.executeQuery();
//            if (rs.next()) {
//                return true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }

    public int removeEmployee(int eid) {
        int n = 0;
        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
        try {

            ResultSet rs2 = getData("Select EmployeeID from EmployeeTerritories where EmployeeID = " + eid);

            if (rs2.next()) {
                return -1;
            }
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, eid);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int insertEmployee(Employees emp) {
        int n = 0;
        String sql = """
                    INSERT INTO [dbo].[Employees]
                               ([LastName]
                               ,[FirstName]
                               ,[Title]
                               ,[TitleOfCourtesy]
                               ,[BirthDate]
                               ,[HireDate]
                               ,[Address]
                               ,[City]
                               ,[Region]
                               ,[PostalCode]
                               ,[Country]
                               ,[HomePhone]
                               ,[Extension]
                               ,[Photo]
                               ,[Notes]
                               ,[ReportsTo]
                               ,[PhotoPath])
                         VALUES
                               (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

                """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setDate(5, emp.getBirthDate());
            pre.setDate(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setBytes(14, emp.getPhoto());
            pre.setString(15, emp.getNotes());
            pre.setInt(16, emp.getReportsTo());
            pre.setString(17, emp.getPhotoPath());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }

    public Vector<Employees> getEmployee(String sql) {
        Vector<Employees> vector = new Vector<Employees>();
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                int EmployeeID = rs.getInt("EmployeeID");
                String LastName = rs.getString("LastName");
                String FirstName = rs.getString("FirstName");
                String Title = rs.getString("Title");
                String TitleOfCourtesy = rs.getString("TitleOfCourtesy");
                Date BirthDate = rs.getDate("BirthDate");
                Date HireDate = rs.getDate("HireDate");
                String Address = rs.getString("Address");
                String City = rs.getString("City");
                String Region = rs.getString("Region");
                String PostalCode = rs.getString("PostalCode");
                String Country = rs.getString("Country");
                String HomePhone = rs.getString("HomePhone");
                String Extension = rs.getString("Extension");
                byte[] Photo = rs.getBytes("Photo");
                String Notes = rs.getString("Notes");
                int ReportsTo = rs.getInt("ReportsTo");
                String PhotoPath = rs.getString("PhotoPath");

                vector.add(new Employees(EmployeeID, LastName, FirstName, Title, TitleOfCourtesy, BirthDate,
                        HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Photo, Notes,
                        ReportsTo, PhotoPath));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public static void main(String[] args) {
        DAOEmployees dao = new DAOEmployees();
        // Employees emp = new Employees(34, "Smith", "John", "Manager", "Mr.",
        // "1985-07-10", "2020-01-15",
        // "123 Main St", "New York", "NY", "10001", "USA",
        // "123-456-7890", "1234",
        // null, "Experienced Manager", 2, "path1.jpg");
        // int n = dao.removeEmployee(32);
        // if (n < 0) {
        // System.out.println("Ràng buộc khóa ngoại");
        // }

        // Tạo đối tượng Employees mới
        // Employees newEmployee = new Employees(
        // 0, // EmployeeID - tự động tăng
        // "Pham", // LastName
        // "Hung", // FirstName
        // "Sales Manager", // Title
        // "Mr.", // TitleOfCourtesy
        // "1985-07-10", // BirthDate
        // "2020-01-15", // HireDate
        // "123 Nguyen Hue", // Address
        // "Ho Chi Minh", // City
        // "South", // Region
        // "700000", // PostalCode
        // "Vietnam", // Country
        // "028-3822-5678", // HomePhone
        // "123", // Extension - Đã sửa lại độ dài cho phù hợp
        // null, // Photo - để trống
        // "Nhân viên xuất sắc, có nhiều kinh nghiệm quản lý", // Notes
        // 1, // ReportsTo - ID của người quản lý
        // "photos/hung.jpg" // PhotoPath
        // );
        //
        // Thực hiện thêm nhân viên
        // int result = dao.insertEmployee(newEmployee);
        //
        // Kiểm tra và thông báo kết quả
        // if (result > 0) {
        // System.out.println("Thêm nhân viên thành công!");
        // } else {
        // System.out.println("Thêm nhân viên thất bại!");
        // }

        boolean flag = dao.loginEmployee("Linh", "Hung");
        if (flag == true) {
            System.out.println("Dang nhap thanh cong");
        } else {
            System.out.println("Fail");
        }
        // Vector<Employees> vector = dao.getEmployee("SElect * from Employees");
        // for (Employees employees : vector) {
        // System.out.println(employees);
        // }
    }

}
