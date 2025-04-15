<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Document : insertOrders Created on : Mar 14, 2025, 2:03:53 AM Author :
ADMIN --%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
  </head>
  <body>
      <form action="OrderURL" method="post">
          <input type="hidden" name="service" value="insertOrders" />
      <table border="1">
        <tbody>
          <tr>
            <td>OrderID</td>
            <td><input type="text" name="OrderID" value="" /></td>
          </tr>
          <tr>
            <td>CustomerID</td>
            <td><select name="CustomerID">
                    <c:forEach var="listCus" items="${listCus}">
                        <option>${listCus.customerID}</option>
                    </c:forEach>
                    
                    
                </select></td>
          </tr>
          <tr>
            <td>EmployeeID</td>
            <td>
                <select name="EmployeeID">
                    <c:forEach var="listEmp" items="${listEmp}">
                        <option> ${listEmp.employeeID}</option>
                    </c:forEach>
                    
                    
                </select>
            </td>
          </tr>
          <tr>
            <td>OrderDate</td>
            <td><input type="date" name="OrderDate" value="" /></td>
          </tr>
          <tr>
            <td>RequiredDate</td>
            <td><input type="date" name="RequiredDate" value="" /></td>
          </tr>
          <tr>
            <td>ShippedDate</td>
            <td><input type="date" name="ShippedDate" value="" /></td>
          </tr>
          <tr>
            <td>ShipVia</td>
            <td><input type="number" name="ShipVia" value="" /></td>
          </tr>
          <tr>
            <td>Freight</td>
            <td><input type="number" name="Freight" value="" /></td>
          </tr>
          <tr>
            <td>ShipName</td>
            <td><input type="text" name="ShipName" value="" /></td>
          </tr>
          <tr>
            <td>ShipAddress</td>
            <td><input type="text" name="ShipAddress" value="" /></td>
          </tr>
          <tr>
            <td>ShipCity</td>
            <td><input type="text" name="ShipCity" value="" /></td>
          </tr>
          <tr>
            <td>ShipRegion</td>
            <td><input type="text" name="ShipRegion" value="" /></td>
          </tr>
          <tr>
            <td>ShipPostalCode</td>
            <td><input type="text" name="ShipPostalCode" value="" /></td>
          </tr>
          <tr>
            <td>ShipCountry</td>
            <td><input type="text" name="ShipCountry" value="" /></td>
          </tr>
        </tbody>
      </table>
      <input type="submit" name="submit" value="Insert" />
    </form>
  </body>
</html>
