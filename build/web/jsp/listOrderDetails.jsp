<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- Document :
listOrderDetails Created on : Mar 14, 2025, 11:16:27 AM Author : ADMIN --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
  </head>
  <body>
    <form action="OrderDetailsURL" method="get">
      <input type="hidden" name="service" value="listOrderDetails" />
      <table border="1">
        <tbody>
          <tr>
            <td>Search Name</td>
            <td><input type="text" name="orID" /></td>
          </tr>
        </tbody>
      </table>
      <input type="submit" name="submit" value="submit" />
    </form>

    <p>
      <a href="OrderDetailsURL?service=insertOdt">Add Order Detail</a>
    </p>
    <table border="1">
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
        <c:forEach var="odt" items="${orderDetails}">
          <tr>
            <td>${odt.orderID}</td>
            <td>${odt.productID}</td>
            <td>${odt.unitPrice}</td>
            <td>${odt.quantity}</td>
            <td>${odt.discount}</td>
            <td>
              <a
                href="OrderDetailsURL?service=updateOdt&OrderID=${odt.orderID}&ProductID=${odt.productID}"
                >Update</a
              >
            </td>
            <td>
              <a
                href="OrderDetailsURL?service=deleteOdt&OrderID=${odt.orderID}&ProductID=${odt.productID}"
                >Delete</a
              >
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
