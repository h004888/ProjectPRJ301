<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- Document :
menu Created on : Mar 11, 2025, 5:11:25 PM Author : ADMIN --%> <%@page
contentType="text/html" pageEncoding="UTF-8"%>
<!-- Combined Header and Navigation -->
<div class="bg-light border-bottom mb-4">
  <div class="container">
    <!-- User Information -->
    <div
      class="d-flex justify-content-between align-items-center py-3 border-bottom"
    >
      <div class="fw-bold">
        <i class="fas fa-id-badge me-1 text-primary"></i> ID:
        <span id="rollNumber"><a href="/projectMVC">HE186353</a></span>
      </div>
      <div class="fw-bold">
        <i class="fas fa-user me-1 text-primary"></i> Full Name:
        <span id="fullName">Phạm Xuân Hùng</span>
      </div>
      <c:if test="${not empty sessionScope.username}">
        <div class="fw-bold">
          <i class="fas fa-hand-wave me-1 text-success"></i> Welcome:
          <span id="userName" class="text-success"
            >${sessionScope.username}</span
          >
        </div>
      </c:if>
    </div>

    <!-- Navigation Menu -->
    <ul class="nav nav-pills nav-fill py-2">
      <c:if test="${empty sessionScope.username}">
        <li class="nav-item">
          <a
            class="nav-link text-primary"
            href="CustomerURL?service=loginCustomer"
            ><i class="fas fa-sign-in-alt me-1"></i> Login</a
          >
        </li>
      </c:if>

      <c:if test="${not empty sessionScope.username}">
        <li class="nav-item">
          <a
            class="nav-link text-primary"
            href="CustomerURL?service=logoutCustomer"
            ><i class="fas fa-sign-out-alt me-1"></i> Logout</a
          >
        </li>
      </c:if>
      <c:if test="${empty sessionScope.username}">
        <li class="nav-item">
          <a
            class="nav-link text-primary"
            href="CustomerURL?service=insertCustomer"
            ><i class="fas fa-user-plus me-1"></i> Register</a
          >
        </li>
      </c:if>
      <li class="nav-item">
        <a class="nav-link text-primary" href="CartURL?service=showCart"
          ><i class="fas fa-shopping-cart me-1"></i> Cart</a
        >
      </li>
    </ul>
  </div>
</div>
