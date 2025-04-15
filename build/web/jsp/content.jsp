<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- Document :
content Created on : Mar 11, 2025, 5:05:31 PM Author : ADMIN --%> <%@page
contentType="text/html" pageEncoding="UTF-8"%>

<!-- Products Column -->
<div class="col-md-9">
  <div class="table-responsive">
    <table class="table table-hover">
      <thead class="table-dark">
        <tr>
          <th><i class="fas fa-laptop me-1"></i> Name</th>
          <th><i class="fas fa-tag me-1"></i> Price</th>
          <th><i class="fas fa-info-circle me-1"></i> Status</th>
          <th><i class="fas fa-shopping-cart me-1"></i> Action</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="pro" items="${vectorPro}">
          <tr>
            <td>
              <div class="fw-bold">${pro.productName}</div>
            </td>
            <td>
              <div class="text-primary fw-bold">
                ${pro.unitPrice} <span class="small text-muted">$</span>
              </div>
            </td>
            <td>
              <span class="badge bg-success"
                ><i class="fas fa-certificate me-1"></i> New</span
              >
            </td>
            <td>
              <button
                class="btn btn-primary btn-sm addToCart"
                data-pid="${pro.productID}"
              >
                <i class="fas fa-cart-plus me-1"></i> Add to cart
              </button>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
