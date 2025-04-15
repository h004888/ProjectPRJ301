/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


/**
 *
 * @author ADMIN
 */
public class DAOCustomer extends DBConnect {

    public boolean loginCustomer(String user, String pass) {
        String sql = "select * from Customers where CustomerID=? "
                + " and CompanyName=?";
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            
        }
        return false;
    }
    public int updateCustomer(Customer customer) {
        int n = 0;
        String sql = """
                UPDATE [dbo].[Customers]
                SET [CompanyName] = ?
                   ,[ContactName] = ?
                   ,[ContactTitle] = ?
                   ,[Address] = ?
                   ,[City] = ?
                   ,[Region] = ?
                   ,[PostalCode] = ?
                   ,[Country] = ?
                   ,[Phone] = ?
                   ,[Fax] = ?
                WHERE [CustomerID] = ?
                """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCompanyName());
            pre.setString(2, customer.getContactName());
            pre.setString(3, customer.getContactTitle());
            pre.setString(4, customer.getAddress());
            pre.setString(5, customer.getCity());
            pre.setString(6, customer.getRegion());
            pre.setString(7, customer.getPostalCode());
            pre.setString(8, customer.getCountry());
            pre.setString(9, customer.getPhone());
            pre.setString(10, customer.getFax());
            pre.setString(11, customer.getCustomerID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeCustomer(String cID) {
        int n = 0;
        String sql = "DELETE FROM Customers WHERE CustomerID = '" + cID + "';";
        try {
            // check foreign key constrain
            String sqlSelect = "select CustomerID from [Orders] where CustomerID='" + cID + "';";
            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Customer> getCustomer(String sql) {
        Vector<Customer> vector = new Vector<>();

        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getString("CustomerID"));
                customer.setCompanyName(rs.getString("CompanyName"));
                customer.setContactName(rs.getString("ContactName"));
                customer.setContactTitle(rs.getString("ContactTitle"));
                customer.setAddress(rs.getString("Address"));
                customer.setCity(rs.getString("City"));
                customer.setRegion(rs.getString("Region"));
                customer.setPostalCode(rs.getString("PostalCode"));
                customer.setCountry(rs.getString("Country"));
                customer.setPhone(rs.getString("Phone"));
                customer.setFax(rs.getString("Fax"));
                vector.add(customer);
            }
            rs.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }

    public int addCustomer(Customer customer) {
        int n = 0;
        String sql = "INSERT INTO [Customers] "
                + "([CustomerID], [CompanyName], [ContactName], [ContactTitle], "
                + "[Address], [City], [Region], [PostalCode], [Country], [Phone], [Fax]) "
                + "VALUES ('" + customer.getCustomerID() + "'"
                + ", '" + customer.getCompanyName() + "'"
                + ", '" + customer.getContactName() + "'"
                + ", '" + customer.getContactTitle() + "'"
                + ", '" + customer.getAddress() + "'"
                + ", '" + customer.getCity() + "'"
                + ", '" + customer.getRegion() + "'"
                + ", '" + customer.getPostalCode() + "'"
                + ", '" + customer.getCountry() + "'"
                + ", '" + customer.getPhone() + "'"
                + ", '" + customer.getFax() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public int insertCustomer(Customer customer) {
        int n = 0;
        String sql = """
                INSERT INTO Customers
                           ([CustomerID]
                           ,[CompanyName]
                           ,[ContactName]
                           ,[ContactTitle]
                           ,[Address]
                           ,[City]
                           ,[Region]
                           ,[PostalCode]
                           ,[Country]
                           ,[Phone]
                           ,[Fax])
                     VALUES
                           (?
                           ,?
                           ,?
                           ,?
                           ,?
                           ,?
                           ,?
                           ,?
                           ,?
                           ,?
                           ,?)
                """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, customer.getCustomerID());
            pre.setString(2, customer.getCompanyName());
            pre.setString(3, customer.getContactName());
            pre.setString(4, customer.getContactTitle());
            pre.setString(5, customer.getAddress());
            pre.setString(6, customer.getCity());
            pre.setString(7, customer.getRegion());
            pre.setString(8, customer.getPostalCode());
            pre.setString(9, customer.getCountry());
            pre.setString(10, customer.getPhone());
            pre.setString(11, customer.getFax());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOCustomer daoCustomer = new DAOCustomer();
        // Customer customer = new Customer();
        // customer.setCustomerID("CUS03");
        // customer.setCompanyName("PhamXuanHung");
        // customer.setContactName("ABC");
        // customer.setContactTitle("ABC");
        // customer.setAddress("ABC");
        // customer.setCity("ABC");
        // customer.setRegion("ABC");
        // customer.setPostalCode("ABC");
        // customer.setCountry("ABC");
        // customer.setPhone("ABC");
        // customer.setFax("ABC");
        // int n = daoCustomer.insertCustomer(customer);
        // if (n > 0) {
        // System.out.println("Add success");
        // } else {
        // System.out.println("Add failed");
        // }

        int a = daoCustomer.removeCustomer("CUS03");
        if (a == -1) {
            System.out.println("Rang buoc khoa ngoai");
        } else {
            System.out.println("success");
        }

        Vector<Customer> vector = daoCustomer.getCustomer("SELECT * FROM Customers");
        for (Customer cus : vector) {
            System.out.println(cus);
        }
    }
}
