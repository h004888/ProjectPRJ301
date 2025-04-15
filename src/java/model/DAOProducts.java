/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
//Database Access Object

import entity.ProCart;
import entity.Products;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DAOProducts extends DBConnect {

    // DBConnect dbconn=new DBConnect();
    public int removeProduct(int pId) {
        int n = 0;
        String sql = "Delete from Products where ProductID=" + pId;
        try {
            //check foreign key constrain
            String sqlSelect
                    = "select ProductID from [Order Details] where ProductID=" + pId;
            ResultSet rs = getData(sqlSelect);
            if (rs.next()) {
                return -1;
            }
            n = conn.createStatement().executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
//        dao.removeProduct(1);
////        int n = dao.addProduct(
////                new Products(0, "New product",
////                        1, 1, "Cai",
////                        10, 1, 1,
////                        1, true));
////        int n = dao.insertProduct(
////                new Products(0, "SamSung",
////                        1, 1, "Cai",
////                        10, 1, 1,
////                        1, true));
//        int n = dao.updateProduct(
//                new Products(79, "Iphone",
//                        1, 1, "Chiec",
//                        10, 1, 1,
//                        1, true));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        Vector<Products> vector
                = dao.getProducts("select * from products ");
        for (Products products : vector) {
            System.out.println(products);
        }
    }

    public int updateProduct(Products pro) {
        int n = 0;
        String sql = """
                   UPDATE [Products]
                      SET [ProductName] = ? ,[SupplierID] = ?,
                     [CategoryID] = ?,[QuantityPerUnit] = ? ,[UnitPrice] = ?
                         ,[UnitsInStock] = ? ,[UnitsOnOrder] = ?
                         ,[ReorderLevel] = ? ,[Discontinued] = ?
                    WHERE ProductID=?
                   """;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.isDiscontinued() == true ? 1 : 0);
            pre.setInt(10, pro.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int insertProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [Products] ([ProductName],"
                + "[SupplierID],[CategoryID]\n"
                + "           ,[QuantityPerUnit],[UnitPrice],[UnitsInStock]\n"
                + "           ,[UnitsOnOrder],[ReorderLevel],[Discontinued])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        //         ? is parameter for fields
//         ? start from 1
//         number of ? base on amount of fields
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.isDiscontinued() == true ? 1 : 0);

            n = pre.executeUpdate();
            //            pre.setDataType(indexOf?,para);
//            dataType of dataType of field;
//            indexOf? start 1
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [Products] ([ProductName],[SupplierID],[CategoryID]\n"
                + "           ,[QuantityPerUnit],[UnitPrice],[UnitsInStock]\n"
                + "           ,[UnitsOnOrder],[ReorderLevel],[Discontinued])\n"
                + "     VALUES('" + pro.getProductName() + "'\n"
                + "           ," + pro.getSupplierID() + "\n"
                + "           ," + pro.getCategoryID() + "\n"
                + "           ,'" + pro.getQuantityPerUnit() + "'\n"
                + "           ," + pro.getUnitPrice() + "\n"
                + "           ," + pro.getUnitsInStock() + "\n"
                + "           ," + pro.getUnitsOnOrder() + "\n"
                + "           ," + pro.getReorderLevel() + "\n"
                + "           ," + (pro.isDiscontinued() == true ? 1 : 0) + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<ProCart> getProductCart(String sql) {
        Vector<ProCart> vector = new Vector<ProCart>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                double UnitPrice = rs.getDouble("UnitPrice");
                ProCart proCart
                        = new ProCart(ProductID, ProductName,
                                1, UnitPrice, 0);
                vector.add(proCart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Products> getProducts(String sql) {
        Vector<Products> vector = new Vector<Products>();
        try {
//            ArrayList: no threadSafe
//           vector: synchronized/Thread safe
//state: thuc thi cau lenh SQL: gui SQL cho Server va nhan lai KQ
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ProductID = rs.getInt(1);
                // int ProductID=rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int SupplierID = rs.getInt("SupplierID"),
                        CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock"),
                        UnitsOnOrder = rs.getInt("UnitsOnOrder"),
                        ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued
                        = (rs.getInt("Discontinued") == 1 ? true : false);
                Products pro = new Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                vector.add(pro);
            }
//            while(rs.previous()){ //TYPE_SCROLL_SENSITIVE
//                
//            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

}
