<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Dashboard</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <style>
      body {
        font-family: "Segoe UI", Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
      }
      .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background: linear-gradient(135deg, #0072ff, #00c6ff);
        color: white;
        padding: 15px 20px;
        border-bottom: 2px solid rgba(255, 255, 255, 0.2);
        position: relative;
        z-index: 1000;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
      }
      .admin-info {
        display: flex;
        align-items: center;
      }
      #menu-toggle {
        background: rgba(255, 255, 255, 0.2);
        border: none;
        color: white;
        font-size: 20px;
        cursor: pointer;
        margin-right: 15px;
        width: 40px;
        height: 40px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        transition: all 0.3s ease;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      }
      #menu-toggle:hover {
        background: rgba(255, 255, 255, 0.3);
        transform: scale(1.05);
      }
      .admin-name {
        font-weight: 600;
        margin-left: 10px;
        font-size: 16px;
        background: rgba(255, 255, 255, 0.1);
        padding: 8px 15px;
        border-radius: 6px;
        letter-spacing: 0.5px;
        transition: all 0.3s ease;
      }
      .welcome-user {
        background-color: rgba(255, 255, 255, 0.15);
        padding: 10px 18px;
        border-radius: 30px;
        font-weight: bold;
        font-size: 16px;
        box-shadow: 0 3px 8px rgba(0, 0, 0, 0.15);
        transition: all 0.3s ease;
        display: flex;
        align-items: center;
        border: 1px solid rgba(255, 255, 255, 0.2);
      }
      .welcome-user:hover {
        background-color: rgba(255, 255, 255, 0.25);
        transform: translateY(-3px);
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
      }
      .welcome-user i {
        margin-right: 10px;
        font-size: 20px;
        color: #ffeb3b;
      }
      .username {
        color: #ffffff;
        text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.2);
        letter-spacing: 0.8px;
        font-weight: 600;
        font-size: 17px;
        margin-left: 8px;
      }
      .logout-container {
        display: flex;
        align-items: center;
      }
      .logout {
        display: flex;
        align-items: center;
        text-decoration: none;
        background-color: rgba(220, 53, 69, 0.8);
        color: white;
        font-weight: bold;
        padding: 10px 18px;
        border-radius: 30px;
        transition: all 0.3s ease;
        box-shadow: 0 3px 8px rgba(0, 0, 0, 0.15);
        border: 1px solid rgba(255, 255, 255, 0.1);
      }
      .logout:hover {
        background-color: #dc3545;
        transform: translateY(-3px);
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
      }
      .logout i {
        margin-right: 8px;
      }
      .sidebar {
        width: 60px;
        background: #2c3e50;
        height: 100%;
        position: fixed;
        left: 0;
        top: 0;
        z-index: 999;
        color: white;
        transition: width 0.3s ease;
        overflow: hidden;
        box-shadow: 3px 0 10px rgba(0, 0, 0, 0.1);
      }
      .sidebar.active {
        width: 250px;
      }
      .sidebar-content {
        padding-top: 80px;
        white-space: nowrap;
      }
      .sidebar a {
        color: white;
        text-decoration: none;
        display: flex;
        align-items: center;
        height: 60px;
        transition: all 0.3s ease;
        margin: 0;
        position: relative;
      }
      .sidebar a:hover {
        background: rgba(255, 255, 255, 0.1);
      }
      .sidebar i {
        font-size: 22px;
        color: #74b9ff;
        width: 60px;
        height: 60px;
        display: flex;
        justify-content: center;
        align-items: center;
        position: absolute;
        left: 0;
        top: 0;
      }
      .sidebar .menu-text {
        display: none;
        opacity: 0;
        transition: opacity 0.3s ease;
        margin-left: 60px;
        font-weight: 500;
      }
      .sidebar.active .menu-text {
        display: block;
        opacity: 1;
      }
      .content {
        margin-left: 60px;
        padding: 25px;
        transition: margin-left 0.3s ease;
      }
      .content.active {
        margin-left: 250px;
      }
      table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 25px;
        background: white;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
        border-radius: 10px;
        overflow: hidden;
      }
      th,
      td {
        text-align: left;
        padding: 12px 15px;
        border-bottom: 1px solid #eaeaea;
      }
      th {
        background: linear-gradient(135deg, #0072ff, #00c6ff);
        color: white;
        font-weight: 600;
        text-transform: uppercase;
        font-size: 14px;
        letter-spacing: 0.5px;
      }
      tr:last-child td {
        border-bottom: none;
      }
      tr:hover {
        background: #f9f9f9;
      }
      .action-btn {
        display: inline-block;
        padding: 8px 16px;
        margin: 2px;
        border-radius: 6px;
        text-decoration: none;
        font-weight: 600;
        font-size: 14px;
        transition: all 0.3s ease;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        text-align: center;
      }
      .update-btn {
        background-color: #4caf50;
        color: white;
      }
      .update-btn:hover {
        background-color: #45a049;
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
      }
      .delete-btn {
        background-color: #f44336;
        color: white;
      }
      .delete-btn:hover {
        background-color: #e53935;
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
      }
      .dashboard-home {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        padding: 40px 20px;
        text-align: center;
        margin-top: 50px;
      }
      .dashboard-title {
        font-size: 32px;
        color: #2c3e50;
        margin-bottom: 15px;
        font-weight: 700;
      }
      .dashboard-subtitle {
        font-size: 18px;
        color: #7f8c8d;
        margin-bottom: 50px;
      }
      .dashboard-icons {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 30px;
        max-width: 1200px;
      }
      .dashboard-card {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 200px;
        text-decoration: none;
        color: #2c3e50;
        padding: 25px 15px;
        border-radius: 15px;
        background-color: white;
        box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
        transition: all 0.3s ease;
      }
      .dashboard-card:hover {
        transform: translateY(-10px);
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.15);
      }
      .icon-container {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 80px;
        height: 80px;
        border-radius: 50%;
        margin-bottom: 20px;
        color: white;
      }
      .icon-container i {
        font-size: 35px;
      }
      .customer {
        background: linear-gradient(135deg, #1abc9c, #16a085);
      }
      .product {
        background: linear-gradient(135deg, #3498db, #2980b9);
      }
      .employee {
        background: linear-gradient(135deg, #9b59b6, #8e44ad);
      }
      .order {
        background: linear-gradient(135deg, #e74c3c, #c0392b);
      }
      .detail {
        background: linear-gradient(135deg, #f39c12, #d35400);
      }
      .dashboard-card span {
        font-size: 16px;
        font-weight: 600;
        margin-top: 5px;
      }
    </style>
  </head>
  <body>
    <div class="header">
      <div class="admin-info">
        <button id="menu-toggle"><i class="fas fa-bars"></i></button>
        <div class="admin-name">He186353 Phạm Xuân Hùng</div>
      </div>
      <div class="welcome-user">
        <i class="fas fa-user-circle"></i> Welcome:
        <span class="username">${sessionScope.username}</span>
      </div>
      <div class="logout-container">
        <a href="CustomerURL?service=logoutCustomer" class="logout">
          <i class="fas fa-sign-out-alt"></i> Logout
        </a>
      </div>
    </div>

    <div class="sidebar" id="sidebar">
      <div class="sidebar-content">
        <a href="admin?service=listCustomer">
          <i class="fas fa-user-tie"></i>
          <span class="menu-text">Customer Manager</span>
        </a>
        <a href="admin?service=listProduct">
          <i class="fas fa-box-open"></i>
          <span class="menu-text">Product Manager</span>
        </a>
        <a href="admin?service=listEmployee">
          <i class="fas fa-user-friends"></i>
          <span class="menu-text">Employee Manager</span>
        </a>
        <a href="admin?service=listOrders">
          <i class="fas fa-receipt"></i>
          <span class="menu-text">Bill Manager</span>
        </a>
        <a href="admin?service=listOrderDetails">
          <i class="fas fa-clipboard-list"></i>
          <span class="menu-text">Order Details</span>
        </a>
      </div>
    </div>

    <div class="content" id="content">
      <c:if test="${empty requestScope.service}">
        <div class="dashboard-home">
          <h1 class="dashboard-title">Admin Dashboard</h1>
          <p class="dashboard-subtitle">Select a management option below</p>

          <div class="dashboard-icons">
            <a href="admin?service=listCustomer" class="dashboard-card">
              <div class="icon-container customer">
                <i class="fas fa-user-tie"></i>
              </div>
              <span>Customer Manager</span>
            </a>

            <a href="admin?service=listProduct" class="dashboard-card">
              <div class="icon-container product">
                <i class="fas fa-box-open"></i>
              </div>
              <span>Product Manager</span>
            </a>

            <a href="admin?service=listEmployee" class="dashboard-card">
              <div class="icon-container employee">
                <i class="fas fa-user-friends"></i>
              </div>
              <span>Employee Manager</span>
            </a>

            <a href="admin?service=listOrders" class="dashboard-card">
              <div class="icon-container order">
                <i class="fas fa-receipt"></i>
              </div>
              <span>Order Manager</span>
            </a>

            <a href="admin?service=listBill" class="dashboard-card">
              <div class="icon-container bill">
                <i class="fas fa-receipt"></i>
              </div>
              <span>Bill Manager</span>
            </a>

            <a href="admin?service=listOrderDetails" class="dashboard-card">
              <div class="icon-container detail">
                <i class="fas fa-clipboard-list"></i>
              </div>
              <span>Order Details</span>
            </a>
          </div>
        </div>
      </c:if>

      <c:if test="${requestScope.service eq 'listCustomer'}">
        <table>
          <thead>
            <tr>
              <th>CustomerID</th>
              <th>CompanyName</th>
              <th>ContactName</th>
              <th>ContactTitle</th>
              <th>Address</th>
              <th>City</th>
              <th>Region</th>
              <th>PostalCode</th>
              <th>Country</th>
              <th>Phone</th>
              <th>Fax</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="cus" items="${vector}">
              <tr>
                <td>${cus.customerID}</td>
                <td>${cus.companyName}</td>
                <td>${cus.contactName}</td>
                <td>${cus.contactTitle}</td>
                <td>${cus.address}</td>
                <td>${cus.city}</td>
                <td>${cus.region}</td>
                <td>${cus.postalCode}</td>
                <td>${cus.country}</td>
                <td>${cus.phone}</td>
                <td>${cus.fax}</td>
                <td>
                  <a
                    class="action-btn update-btn"
                    href="CustomerURL?service=updateCustomer&cid=${cus.customerID}"
                  >
                    <i class="fas fa-edit"></i> Update
                  </a>
                </td>
                <td>
                  <a
                    class="action-btn delete-btn"
                    href="CustomerURL?service=deleteCustomer&cid=${cus.customerID}"
                  >
                    <i class="fas fa-trash"></i> Delete
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <c:if test="${requestScope.service eq 'listOrders'}">
        <table>
          <thead>
            <tr>
              <th>OrderID</th>
              <th>CustomerID</th>
              <th>EmployeeID</th>
              <th>OrderDate</th>
              <th>RequiredDate</th>
              <th>ShippedDate</th>
              <th>ShipVia</th>
              <th>Freight</th>
              <th>ShipName</th>
              <th>ShipAddress</th>
              <th>ShipCity</th>
              <th>ShipRegion</th>
              <th>ShipPostalCode</th>
              <th>ShipCountry</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="od" items="${vector}">
              <tr>
                <td>${od.orderID}</td>
                <td>${od.customerID}</td>
                <td>${od.employeeID}</td>
                <td>${od.orderDate}</td>
                <td>${od.requiredDate}</td>
                <td>${od.shippedDate}</td>
                <td>${od.shipVia}</td>
                <td>${od.freight}</td>
                <td>${od.shipName}</td>
                <td>${od.shipAddress}</td>
                <td>${od.shipCity}</td>
                <td>${od.shipRegion}</td>
                <td>${od.shipPostalCode}</td>
                <td>${od.shipCountry}</td>
                <td>
                  <a href="OrderURL?service=updateOrders&orderID=${od.orderID}"
                    >Update</a
                  >
                </td>
                <td>
                  <a href="OrderURL?service=deleteOrders&orderID=${od.orderID}"
                    >Delete</a
                  >
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <c:if test="${requestScope.service eq 'listEmployee'}">
        <table>
          <thead>
            <tr>
              <th>EmployeeID</th>
              <th>LastName</th>
              <th>FirstName</th>
              <th>Title</th>
              <th>TitleOfCourtesy</th>
              <th>BirthDate</th>
              <th>HireDate</th>
              <th>Address</th>
              <th>City</th>
              <th>Region</th>
              <th>PostalCode</th>
              <th>Country</th>
              <th>HomePhone</th>
              <th>Extension</th>
              <th>Photo</th>
              <th>Notes</th>
              <th>ReportsTo</th>
              <th>PhotoPath</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="emp" items="${listEmployee}">
              <tr>
                <td>${emp.employeeID}</td>
                <td>${emp.lastName}</td>
                <td>${emp.firstName}</td>
                <td>${emp.title}</td>
                <td>${emp.titleOfCourtesy}</td>
                <td>${emp.birthDate}</td>
                <td>${emp.hireDate}</td>
                <td>${emp.address}</td>
                <td>${emp.city}</td>
                <td>${emp.region}</td>
                <td>${emp.postalCode}</td>
                <td>${emp.country}</td>
                <td>${emp.homePhone}</td>
                <td>${emp.extension}</td>
                <td>${emp.photo}</td>
                <td>${emp.notes}</td>
                <td>${emp.reportsTo}</td>
                <td>${emp.photoPath}</td>
                <td>
                  <a
                    href="EmployeesURL?service=updateEmployees&empID=${emp.employeeID}"
                    >Update</a
                  >
                </td>
                <td>
                  <a
                    href="EmployeesURL?service=removeEmployees&empID=${emp.employeeID}"
                    >Delete</a
                  >
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <c:if test="${requestScope.service eq 'listProduct'}">
        <table>
          <thead>
            <tr>
              <th>ProductID</th>
              <th>ProductName</th>
              <th>SupplierID</th>
              <th>CategoryID</th>
              <th>QuantityPerUnit</th>
              <th>UnitPrice</th>
              <th>UnitsInStock</th>
              <th>UnitsOnOrder</th>
              <th>ReorderLevel</th>
              <th>Discontinued</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="pro" items="${vector}">
              <tr>
                <td>${pro.productID}</td>
                <td>${pro.productName}</td>
                <td>${pro.supplierID}</td>
                <td>${pro.categoryID}</td>
                <td>${pro.quantityPerUnit}</td>
                <td>${pro.unitPrice}</td>
                <td>${pro.unitsInStock}</td>
                <td>${pro.unitsOnOrder}</td>
                <td>${pro.reorderLevel}</td>
                <td>${pro.discontinued}</td>
                <td>
                  <a
                    class="action-btn update-btn"
                    href="ProductURL?service=updateProducts&pid=${pro.productID}"
                  >
                    <i class="fas fa-edit"></i> Update
                  </a>
                </td>
                <td>
                  <a
                    class="action-btn delete-btn"
                    href="ProductURL?service=deleteProduct&pid=${pro.productID}"
                  >
                    <i class="fas fa-trash"></i> Delete
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <c:if test="${requestScope.service eq 'listBill'}">
        <table border="1">
          <thead>
            <tr>
              <th>orderID</th>
              <th>customerID</th>
              <th>orderDate</th>
              <th>total</th>
              <th>status</th>
              <th>view</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="ls" items="${listBill}">
              <tr>
                <td>${ls.orderID}</td>
                <td>${ls.customerID}</td>
                <td>${ls.orderDate}</td>
                <td>${ls.total}</td>
                <td>${ls.status}</td>
                <td><a href="billURL?service=updateBill&orID=${ls.orderID}">detail</a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>

      <c:if test="${requestScope.service eq 'listOrderDetails'}">
        <table>
          <thead>
            <tr>
              <th>OrderID</th>
              <th>ProductID</th>
              <th>UnitPrice</th>
              <th>Quantity</th>
              <th>Discount</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="od" items="${vector}">
              <tr>
                <td>${od.orderID}</td>
                <td>${od.productID}</td>
                <td>${od.unitPrice}</td>
                <td>${od.quantity}</td>
                <td>${od.discount}</td>
                <td>
                  <a
                    class="action-btn update-btn"
                    href="OrderDetailsURL?service=updateOdt&orderID=${od.orderID}&productID=${od.productID}"
                  >
                    <i class="fas fa-edit"></i> Update
                  </a>
                </td>
                <td>
                  <a
                    class="action-btn delete-btn"
                    href="OrderDetailsURL?service=deleteOdt&OrderID=${od.orderID}&ProductID=${od.productID}"
                  >
                    <i class="fas fa-trash"></i> Delete
                  </a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:if>
    </div>

    <script>
      document.addEventListener("DOMContentLoaded", function () {
        const menuToggle = document.getElementById("menu-toggle");
        const sidebar = document.getElementById("sidebar");
        const content = document.getElementById("content");

        menuToggle.addEventListener("click", function () {
          sidebar.classList.toggle("active");
          content.classList.toggle("active");
        });
      });
    </script>
  </body>
</html>
