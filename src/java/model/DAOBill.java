package model;

import entity.Bill;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class DAOBill extends DBConnect {

    public Vector<Bill> getListBill() {
        Vector<Bill> list = new Vector<>();
        String sql = """
                SELECT
                    o.OrderID,
                    o.CustomerID,
                    o.OrderDate,
                    o.RequiredDate,
                    o.ShippedDate,
                    c.CompanyName as CustomerName,
                    (SELECT SUM(UnitPrice * Quantity * (1 - Discount))
                     FROM [Order Details] od
                     WHERE od.OrderID = o.OrderID) as Total
                FROM Orders o
                JOIN Customers c ON o.CustomerID = c.CustomerID;
                """;

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setOrderID(rs.getInt("OrderID"));
                bill.setCustomerID(rs.getString("CustomerID"));
                bill.setOrderDate(rs.getDate("OrderDate"));
                bill.setRequiredDate(rs.getDate("RequiredDate"));
                bill.setShippedDate(rs.getDate("ShippedDate"));
                bill.setStatus(mapOrderStatusToBillStatus(rs));  // Mapping the status
                bill.setTotal(rs.getDouble("Total"));
                list.add(bill);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("getListBill: " + e.getMessage());
        }
        return list;
    }

    private String mapOrderStatusToBillStatus(ResultSet rs) throws SQLException {
        Date shippedDate = rs.getDate("ShippedDate");
        Date requiredDate = rs.getDate("RequiredDate");
        Date orderDate = rs.getDate("OrderDate");

        // Determine the status based on the date fields
        if (shippedDate != null) {
            return "done";  // Order is completed if it has a shipped date
        } else if (requiredDate != null) {
            return "process";  // Order is processing if it has a required date but no shipped date
        } else if (orderDate != null) {
            return "wait";  // Order is pending if it has an order date but no required or shipped date
        } else {
            return "Unknown";  // If no date information is available
        }
    }

    public Vector<Bill> getBillsByStatus(String status) {
        Vector<Bill> allBills = getListBill();
        Vector<Bill> filteredBills = new Vector<>();

        for (Bill bill : allBills) {
            if (bill.getStatus().equalsIgnoreCase(status)) {
                filteredBills.add(bill);
            }
        }

        return filteredBills;
    }

    public boolean updateBillStatus(int orderID, String status) {
        String sql = "";
        switch (status.toLowerCase()) {
            case "done":
                sql = "UPDATE Orders SET ShippedDate = GETDATE() WHERE OrderID = ?";
                break;
            case "process":
                sql = "UPDATE Orders SET RequiredDate = GETDATE(), ShippedDate = NULL WHERE OrderID = ?";
                break;
            case "wait":
                sql = "UPDATE Orders SET RequiredDate = NULL, ShippedDate = NULL WHERE OrderID = ?";
                break;
            default:
                return false;
        }

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, orderID);
            int count = pst.executeUpdate();
            pst.close();
            return count > 0;
        } catch (SQLException e) {
            System.out.println("updateBillStatus: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // Create instance of DAOBill
        DAOBill dao = new DAOBill();

        // Test getListBill()
//        System.out.println("Testing getListBill():");
//        Vector<Bill> allBills = dao.getListBill();
//        for (Bill bill : allBills) {
//            System.out.println(bill.toString());
//        }

        
//
//        // Test updateBillStatus()
//        System.out.println("\nTesting updateBillStatus(10248, 'Completed'):");
//        boolean updateResult = dao.updateBillStatus(1, "Completed");
//        System.out.println("Update result: " + updateResult);
        // Test getBillsByStatus()
        System.out.println("\nTesting getBillsByStatus('process'):");
        Vector<Bill> completedBills = dao.getBillsByStatus("process");
        for (Bill bill : completedBills) {
            System.out.println(bill.toString());
        }
    }
}
