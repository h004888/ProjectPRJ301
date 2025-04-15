<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- Document :
showCart Created on : Mar 15, 2025, 4:48:08 PM Author : ADMIN --%> <%@page
contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Shopping Cart</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <style>
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      body {
        font-family: "Segoe UI", Arial, sans-serif;
        background-color: #f4f6f9;
        color: #333;
        padding: 20px;
        line-height: 1.6;
      }

      .container {
        max-width: 1200px;
        margin: 0 auto;
        background: white;
        padding: 25px;
        border-radius: 8px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
      }

      .shopping-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 25px;
        padding-bottom: 15px;
        border-bottom: 1px solid #eee;
      }

      .shopping-title {
        font-size: 24px;
        font-weight: 600;
        color: #2c3e50;
      }

      .continue-link {
        text-decoration: none;
        color: #3498db;
        font-weight: 600;
        padding: 10px 15px;
        background-color: #f8f9fa;
        border-radius: 5px;
        transition: all 0.3s;
        display: inline-block;
      }

      .continue-link:hover {
        background-color: #e9ecef;
        color: #2980b9;
      }

      table {
        width: 100%;
        border-collapse: collapse;
        border-radius: 5px;
        overflow: hidden;
        margin-bottom: 20px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.05);
      }

      th {
        background-color: #3498db;
        color: white;
        padding: 12px 15px;
        text-align: left;
        font-weight: 600;
      }

      td {
        padding: 12px 15px;
        border-bottom: 1px solid #eee;
      }

      tr:last-child td {
        border-bottom: none;
      }

      tr:hover {
        background-color: #f8f9fa;
      }

      input[type="number"] {
        width: 70px;
        padding: 8px;
        border: 1px solid #ddd;
        border-radius: 4px;
        text-align: center;
      }

      input[type="submit"],
      button {
        background-color: #3498db;
        color: white;
        border: none;
        padding: 10px 16px;
        border-radius: 4px;
        cursor: pointer;
        font-weight: 600;
        transition: background-color 0.3s;
      }

      input[type="submit"]:hover,
      button:hover {
        background-color: #2980b9;
      }

      a {
        text-decoration: none;
        color: #3498db;
        font-weight: 500;
        transition: color 0.3s;
      }

      a:hover {
        color: #2980b9;
      }

      button a {
        color: white;
        text-decoration: none;
      }

      .cart-actions {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 20px;
        flex-wrap: wrap;
        gap: 10px;
      }

      .left-actions {
        display: flex;
        gap: 10px;
      }

      .right-actions {
        display: flex;
        align-items: center;
        gap: 20px;
      }

      .total {
        font-size: 18px;
        font-weight: 600;
        color: #2c3e50;
      }

      .total-value {
        font-weight: 700;
        color: #3498db;
      }

      .checkout-btn {
        background-color: #2ecc71;
        padding: 12px 20px;
        font-size: 16px;
        border-radius: 5px;
        border: none;
        box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
        cursor: pointer;
        transition: background-color 0.3s, transform 0.3s;
      }

      .checkout-btn:hover {
        background-color: #27ae60;
        transform: translateY(-2px);
      }

      .remove-link {
        color: #e74c3c;
        font-weight: 500;
      }

      .remove-link:hover {
        color: #c0392b;
      }

      .message {
        padding: 12px 15px;
        background-color: #d4edda;
        color: #155724;
        border-radius: 4px;
        margin-top: 20px;
        animation: fadeIn 0.5s;
      }

      @keyframes fadeIn {
        from {
          opacity: 0;
        }
        to {
          opacity: 1;
        }
      }

      .checkout-form {
        margin-top: 20px;
        text-align: right;
      }

      /* Responsive fixes */
      @media (max-width: 768px) {
        .cart-actions {
          flex-direction: column;
          align-items: stretch;
        }

        .right-actions {
          margin-top: 15px;
          justify-content: space-between;
        }

        table {
          display: block;
          overflow-x: auto;
        }
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="shopping-header">
        <h2 class="shopping-title">Shopping Cart</h2>
        <p>
          <a href="home" class="continue-link"
            ><i class="fas fa-arrow-left"></i> Continue Shopping</a
          >
        </p>
      </div>

      <!-- Form cập nhật giỏ hàng -->
      <form action="CartURL" method="post">
        <input type="hidden" name="service" value="updateQuantity" />

        <table>
          <thead>
            <tr>
              <th>Product ID</th>
              <th>Product Name</th>
              <th>Quantity</th>
              <th>Unit Price</th>
              <th>Discount</th>
              <th>Subtotal</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="cart" items="${vector}">
              <tr>
                <td>${cart.productID}</td>
                <td>${cart.productName}</td>
                <td>
                  <input
                    type="number"
                    name="quantity__${cart.productID}"
                    value="${cart.quantity}"
                    min="0"
                  />
                </td>
                <td>$${cart.unitPrice}</td>
                <td>${cart.discount * 100}%</td>
                <td>
                  $${(cart.quantity * cart.unitPrice) - (cart.unitPrice *
                  cart.discount * cart.quantity)}
                </td>
                <td>
                  <a
                    href="CartURL?service=removePro&pID=${cart.productID}"
                    class="remove-link"
                  >
                    <i class="fas fa-trash"></i> Remove
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <div class="cart-actions">
          <div class="left-actions">
            <input type="submit" name="submit" value="Update Cart" />
            <button type="button">
              <a href="CartURL?service=removeAll">Remove All</a>
            </button>
          </div>

          <div class="right-actions">
            <p class="total">
              Total: <span class="total-value">$${total}</span>
            </p>
          </div>
        </div>
      </form>

      <!-- Thanh toán được hiển thị đẹp hơn -->
      <div class="checkout-form">
        <form action="CartURL" method="post">
          <input type="hidden" name="service" value="checkout" />
          <input type="submit" value="Check Out" class="checkout-btn" />
        </form>
      </div>

      <c:if test="${not empty message}">
        <div class="message">
          <i class="fas fa-check-circle"></i> ${message}
        </div>
      </c:if>
    </div>
  </body>
</html>
