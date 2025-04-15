<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- Document :
listOrders Created on : Mar 14, 2025, 1:37:44 AM Author : ADMIN --%> <%@page
contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
  </head>
  <body>
      <form action="OrderURL" method="get">
            <input type="hidden" name="service" value="listOrders" />
            <table border="1">
                <tbody>
                    <tr>
                        <td>Search Name</td>
                        <td><input type="text" name="cID" /> </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" name="submit" value="submit" />

        </form>
      
      <p>
          <a href="OrderURL?service=insertOrders">insertOrders</a>
      </p>
    <table border="1">
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
        <c:forEach var="od" items="${listOrders}">
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
              <a href="OrderURL?service=updateOrders&orderID=${od.orderID}">Update</a>
            </td>
            <td>
              <a href="OrderURL?service=deleteOrders&orderID=${od.orderID}">Delete</a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
