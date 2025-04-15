<%-- Document : insertCustomer Created on : Mar 6, 2025, 10:34:25 PM Author :
ADMIN --%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
  </head>
  <body>
    <form action="CustomerURL" method="POST">
      <input type="hidden" name="service" value="insertCustomer" />
      <table>
        <tr>
          <td>CustomerID</td>
          <td><input type="text" name="CustomerID" id="" /></td>
        </tr>
        <tr>
          <td>CompanyName</td>
          <td><input type="text" name="CompanyName" id="" /></td>
        </tr>
        <tr>
          <td>ContactName</td>
          <td><input type="text" name="ContactName" id="" /></td>
        </tr>
        <tr>
          <td>ContactTitle</td>
          <td><input type="text" name="ContactTitle" id="" /></td>
        </tr>
        <tr>
          <td>Address</td>
          <td><input type="text" name="Address" id="" /></td>
        </tr>
        <tr>
          <td>City</td>
          <td><input type="text" name="City" id="" /></td>
        </tr>
        <tr>
          <td>PostalCode</td>
          <td><input type="text" name="PostalCode" id="" /></td>
        </tr>
        <tr>
          <td>Region</td>
          <td><input type="text" name="Region" id="" /></td>
        </tr>
        <tr>
          <td>Country</td>
          <td><input type="text" name="Country" id="" /></td>
        </tr>
        <tr>
          <td>Phone</td>
          <td><input type="text" name="Phone" id="" /></td>
        </tr>
        <tr>
          <td>Fax</td>
          <td><input type="text" name="Fax" id="" /></td>
        </tr>
        <tr>
          <td><input type="submit" name="submit" value="insertCustomer" /></td>
          <td><input type="reset" value="Clear" /></td>
        </tr>
      </table>
    </form>
  </body>
</html>
