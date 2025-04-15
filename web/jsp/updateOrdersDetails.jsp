<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- Document :
insertOrderDetail Created on : Mar 14, 2025, 11:22:25 AM Author : ADMIN --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Cập nhật Order Detail</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
    <style>
      body {
        background-color: #f8f9fa;
        padding: 20px;
      }
      .form-container {
        max-width: 600px;
        margin: 0 auto;
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
      }
      .form-title {
        margin-bottom: 20px;
        padding-bottom: 10px;
        border-bottom: 1px solid #ddd;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <div class="form-container">
        <h2 class="form-title text-center">Cập nhật Chi tiết đơn hàng</h2>
        
        <c:if test="${not empty errorMessage}">
          <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        
        <form action="OrderDetailsURL" method="post">
          <input type="hidden" name="service" value="updateOdt" />
          
          <div class="mb-3">
            <label for="orderID" class="form-label">Order ID:</label>
            <select name="OrderID" id="orderID" class="form-select" required>
              <c:forEach var="o" items="${orders}">
                <option value="${o.orderID}" ${o.orderID == ordetail.orderID ? 'selected' : ''}>${o.orderID}</option>
              </c:forEach>
            </select>
          </div>
          
          <div class="mb-3">
            <label for="productID" class="form-label">Product ID:</label>
            <select name="ProductID" id="productID" class="form-select" required>
              <c:forEach var="p" items="${products}">
                <option value="${p.productID}" ${p.productID == ordetail.productID ? 'selected' : ''}>${p.productID}</option>
              </c:forEach>
            </select>
          </div>
          
          <div class="mb-3">
            <label for="unitPrice" class="form-label">Unit Price:</label>
            <input type="number" step="0.01" class="form-control" id="unitPrice" name="UnitPrice" value="${ordetail.unitPrice}" required />
          </div>
          
          <div class="mb-3">
            <label for="quantity" class="form-label">Quantity:</label>
            <input type="number" class="form-control" id="quantity" name="Quantity" value="${ordetail.quantity}" required />
          </div>
          
          <div class="mb-3">
            <label for="discount" class="form-label">Discount:</label>
            <input type="number" step="0.01" min="0" max="1" class="form-control" id="discount" name="Discount" value="${ordetail.discount}" required />
            <small class="form-text text-muted">Nhập giá trị từ 0 đến 1 (ví dụ: 0.10 cho 10%).</small>
          </div>
          
          <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="OrderDetailsURL" class="btn btn-secondary me-md-2">Hủy</a>
            <button type="submit" name="submit" value="updateOdt" class="btn btn-primary">Cập nhật</button>
          </div>
        </form>
      </div>
    </div>
    
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
