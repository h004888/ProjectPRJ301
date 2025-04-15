<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- Document :
insertOrderDetail Created on : Mar 14, 2025, 11:22:25 AM Author : ADMIN --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
  </head>
  <body>
    <form action="OrderDetailsURL" method="post">
      <input type="hidden" name="service" value="insertOdt" />
      <table border="1">
        <tbody>
          <tr>
            <td>OrderID</td>
            <td>
              <select name="OrderID">
                <c:forEach var="o" items="${orders}">
                  <option value="${o.orderID}">${o.orderID}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td>ProductID</td>
            <td>
              <select name="ProductID">
                <c:forEach var="p" items="${products}">
                  <option value="${p.productID}">${p.productID}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
          <tr>
            <td>UnitPrice</td>
            <td><input type="text" name="UnitPrice" /></td>
          </tr>
          <tr>
            <td>Quantity</td>
            <td><input type="text" name="Quantity" /></td>
          </tr>

          <tr>
            <td>Discount</td>
            <td><input type="text" name="Discount" /></td>
          </tr>
        </tbody>
      </table>
      <input type="submit" name="submit" value="Insert" />
    </form>
  </body>
</html>
