/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class DAOOrderDetails extends DBConnect {

    public Vector<OrderDetails> getOrderDetails(String sql) {
        Vector<OrderDetails> vector = new Vector<>();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int productID = rs.getInt("ProductID");
                double unitPrice = rs.getDouble("UnitPrice");
                int quantity = rs.getInt("Quantity");
                double discount = rs.getDouble("Discount");

                OrderDetails od = new OrderDetails(orderID, productID, unitPrice, quantity, discount);
                vector.add(od);
            }
        } catch (SQLException e) {
            System.out.println("getOrderDetails: " + e.getMessage());
        }
        return vector;
    }

    public int deleteOrderDetail(int OrderID, int ProductID) {
        int n = 0;
        String sql = "DELETE FROM [Order Details] WHERE OrderID = ? AND ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, OrderID);
            pre.setInt(2, ProductID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addOrderDetail(OrderDetails orderDetail) {
        int n = 0;
        String sql = "INSERT INTO [Order Details]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Quantity]\n"
                + "           ,[Discount])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderDetail.getOrderID());
            pre.setInt(2, orderDetail.getProductID());
            pre.setDouble(3, orderDetail.getUnitPrice());
            pre.setInt(4, orderDetail.getQuantity());
            pre.setDouble(5, orderDetail.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    // UPDATE [dbo].[Order Details]
    // SET [OrderID] = <OrderID, int,>
    // ,[ProductID] = <ProductID, int,>
    // ,[UnitPrice] = <UnitPrice, money,>
    // ,[Quantity] = <Quantity, smallint,>
    // ,[Discount] = <Discount, real,>
    public int updateOrderDetail(OrderDetails orderDetail) {
        int n = 0;
        String sql = "UPDATE [Order Details] SET [OrderID] = ?, [ProductID] = ?, [UnitPrice] = ?, [Quantity] = ?, [Discount] = ? WHERE OrderID = ? AND ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderDetail.getOrderID());
            pre.setInt(2, orderDetail.getProductID());
            pre.setDouble(3, orderDetail.getUnitPrice());
            pre.setInt(4, orderDetail.getQuantity());
            pre.setDouble(5, orderDetail.getDiscount());
            pre.setInt(6, orderDetail.getOrderID());
            pre.setInt(7, orderDetail.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOOrderDetails dao = new DAOOrderDetails();
        // int n = dao.addOrderDetail(new OrderDetails(10250, 5, 10.0, 5, 0.1));
        // if (n > 0) {
        // System.out.println("inserted");
        // }

        // int n = dao.deleteOrderDetail(10625, 42);
        // if (n > 0) {
        // System.out.println("deleted");
        // }
        int n = dao.updateOrderDetail(new OrderDetails(10248, 11, 200, 5, 0.1));
        if (n > 0) {
            System.out.println("updated");
        }
        Vector<OrderDetails> orderDetails = dao.getOrderDetails("SELECT * FROM [Order Details]");
        // Debug dữ liệu - In danh sách chi tiết đơn hàng ra Console
        System.out.println("Danh sách chi tiết đơn hàng từ cơ sở dữ liệu:");
        for (OrderDetails detail : orderDetails) {
            System.out.println(detail);
        }
    }
}
