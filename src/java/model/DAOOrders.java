/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOOrders extends DBConnect {

    public Vector<Orders> getOrders(String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            // Create statement to execute SQL query
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int OrderID = rs.getInt("OrderID");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                Date OrderDate = rs.getDate("OrderDate");
                Date RequiredDate = rs.getDate("RequiredDate");
                Date ShippedDate = rs.getDate("ShippedDate");
                int ShipVia = rs.getInt("ShipVia");
                double Freight = rs.getDouble("Freight");
                String ShipName = rs.getString("ShipName");
                String ShipAddress = rs.getString("ShipAddress");
                String ShipCity = rs.getString("ShipCity");
                String ShipRegion = rs.getString("ShipRegion");
                String ShipPostalCode = rs.getString("ShipPostalCode");
                String ShipCountry = rs.getString("ShipCountry");

                Orders order = new Orders(OrderID, CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate,
                        ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry);
                vector.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public int updateOrder(Orders orderDetail) {
        int n = 0;
        String sql = """
                             UPDATE [dbo].[Orders]
                SET [CustomerID] = ?
                   ,[EmployeeID] = ?
                   ,[OrderDate] = ?
                   ,[RequiredDate] = ?
                   ,[ShippedDate] = ?
                   ,[ShipVia] = ?
                   ,[Freight] = ?
                   ,[ShipName] = ?
                   ,[ShipAddress] = ?
                     ,[ShipCity] = ?
                   ,[ShipRegion] = ?
                   ,[ShipPostalCode] = ?
                   ,[ShipCountry] = ?
                   WHERE OrderID = ?
                             """;

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, orderDetail.getCustomerID());
            pre.setInt(2, orderDetail.getEmployeeID());
            pre.setDate(3, orderDetail.getOrderDate());
            pre.setDate(4, orderDetail.getRequiredDate());
            pre.setDate(5, orderDetail.getShippedDate());
            pre.setInt(6, orderDetail.getShipVia());
            pre.setDouble(7, orderDetail.getFreight());
            pre.setString(8, orderDetail.getShipName());
            pre.setString(9, orderDetail.getShipAddress());
            pre.setString(10, orderDetail.getShipCity());
            pre.setString(11, orderDetail.getShipRegion());
            pre.setString(12, orderDetail.getShipPostalCode());
            pre.setString(13, orderDetail.getShipCountry());
            pre.setInt(14, orderDetail.getOrderID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

  public int addOrder(Orders order) {
    int generatedOrderID = 0;

    String sql = "INSERT INTO [Orders] "
            + "([CustomerID], [EmployeeID], [OrderDate], [RequiredDate], [ShippedDate], "
            + "[ShipVia], [Freight], [ShipName], [ShipAddress], [ShipCity], "
            + "[ShipRegion], [ShipPostalCode], [ShipCountry]) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setString(1, order.getCustomerID());
        ps.setInt(2, order.getEmployeeID());
        ps.setDate(3, order.getOrderDate());
        ps.setDate(4, order.getRequiredDate());
        ps.setDate(5, order.getShippedDate());
        ps.setInt(6, order.getShipVia());
        ps.setDouble(7, order.getFreight());
        ps.setString(8, order.getShipName());
        ps.setString(9, order.getShipAddress());
        ps.setString(10, order.getShipCity());
        ps.setString(11, order.getShipRegion());
        ps.setString(12, order.getShipPostalCode());
        ps.setString(13, order.getShipCountry());

        ps.executeUpdate();

        // Lấy OrderID vừa insert
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            generatedOrderID = rs.getInt(1);
        }

        rs.close();
        ps.close();

    } catch (SQLException ex) {
        Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
    }

    return generatedOrderID; // trả về OrderID vừa được tạo
}

    public int deleteOrder(int OrderID) {
        int n = 0;
        String sql = "DELETE FROM Orders WHERE OrderID = " + OrderID;

        try {
            String sqlSelect = "Select OrderID from [Order Details] where OrderID = " + OrderID;
            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {
                return -1;
            }

            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();
        // int n = dao.addOrder(new Orders(99999, "RATTC", 1, Date.valueOf(""), new
        // Date(), new Date(), 1, 12.34, "Nguyễn Văn A",
        // "123 Nguyễn Văn A", "Hà Nội", "Hà Nội", "100000", "Việt Nam"));
        // if (n > 0) {
        // System.out.println("Thêm thành công!");
        // } else {
        // System.out.println("Thêm thất bại!");
        // }

        // int n = dao.updateOrder(new Orders(11078, "100", 1, new Date(), new Date(),
        // new Date(), 1, 12.34, "Pham Xuan Hung",
        // "123 Nguyễn Văn A", "Hà Nội", "Hà Nội", "100000", "Việt Nam"));
        // if (n > 0) {
        // System.out.println("Sửa thành công!");
        // } else {
        // System.out.println("Sửa thất bại!");
        // }

        // Test hàm deleteOrder
        // int n = dao.deleteOrder(11084); // Thay 10248 bằng OrderID bạn muốn xóa
        // if (n > 0) {
        // System.out.println("Xóa thành công!");
        // } else {
        // System.out.println("Xóa thất bại!");
        // }

        // Kiểm tra lại danh sách đơn hàng sau khi xóa
        Vector<Orders> orders = dao.getOrders("SELECT * FROM Orders where OrderID = 10248");
        System.out.println("Danh sách đơn hàng sau khi xóa:");
        for (Orders order : orders) {
            System.out.println(order);
        }
    }
}
