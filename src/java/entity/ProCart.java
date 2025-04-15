/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ADMIN
 */
public class ProCart {
    private int productID;
    private String productName;
    private int quantity;
    private double UnitPrice;
    private float discount;

    public ProCart(int productID, String productName, int quantity, double UnitPrice, float discount) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.UnitPrice = UnitPrice;
        this.discount = discount;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public ProCart() {
    }

    @Override
    public String toString() {
        return "ProCart{" + "productID=" + productID + ", productName=" + productName + ", quantity=" + quantity + ", UnitPrice=" + UnitPrice + ", discount=" + discount + '}';
    }

    
    
    
    
}
