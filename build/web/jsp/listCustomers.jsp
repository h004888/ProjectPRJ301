<%-- Document : listCustomers Created on : Mar 6, 2025, 10:18:23 AM Author :
ADMIN --%> <%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>List Customers</title>
  </head>
  <body>
    <form action="CustomerURL" method="get">
      <input type="hidden" name="service" value="listCustomers" />
      <p>Search Name <input type="text" name="cname" /></p>
      <p>
        <input type="submit" name="submit" value="Search" />
        <input type="reset" value="Clear" />
      </p>
    </form>

    <p><a href="CustomerURL?service=insertCustomer">Insert Customer</a></p>
    <table border="1">
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
              <a href="CustomerURL?service=updateCustomer&cid=${cus.customerID}"
                >Update</a
              >
            </td>
            <td>
              <a href="CustomerURL?service=deleteCustomer&cid=${cus.customerID}"
                >Delete</a
              >
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
