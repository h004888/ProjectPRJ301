<%-- Document : insertEmployee Created on : Mar 13, 2025, 10:54:06 PM Author :
ADMIN --%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>JSP Page</title>
  </head>
  <body>
    
    <form action="EmployeesURL" method="post">
      <input type="hidden" name="service" value="updateEmployees" />
      <table border="1">
        <tbody>
          <tr>
            <td>EmployeeID</td>
            <td>
              <input type="text" name="employeeID" value="${Emp.employeeID}" />
            </td>
          </tr>
          <tr>
            <td>LastName</td>
            <td>
              <input type="text" name="LastName" value="${Emp.lastName}" />
            </td>
          </tr>
          <tr>
            <td>FirstName</td>
            <td>
              <input type="text" name="FirstName" value="${Emp.firstName}" />
            </td>
          </tr>
          <tr>
            <td>Title</td>
            <td><input type="text" name="Title" value="${Emp.title}" /></td>
          </tr>
          <tr>
            <td>TitleOfCourtesy</td>
            <td>
              <input
                type="text"
                name="TitleOfCourtesy"
                value="${Emp.titleOfCourtesy}"
              />
            </td>
          </tr>
          <tr>
            <td>BirthDate</td>
            <td>
              <input type="date" name="BirthDate" value="${Emp.birthDate}" />
            </td>
          </tr>
          <tr>
            <td>HireDate</td>
            <td>
              <input type="date" name="HireDate" value="${Emp.hireDate}" />
            </td>
          </tr>
          <tr>
            <td>Address</td>
            <td><input type="text" name="Address" value="${Emp.address}" /></td>
          </tr>
          <tr>
            <td>City</td>
            <td><input type="text" name="City" value="${Emp.city}" /></td>
          </tr>
          <tr>
            <td>Region</td>
            <td><input type="text" name="Region" value="${Emp.region}" /></td>
          </tr>
          <tr>
            <td>PostalCode</td>
            <td>
              <input type="text" name="PostalCode" value="${Emp.postalCode}" />
            </td>
          </tr>
          <tr>
            <td>Country</td>
            <td><input type="text" name="Country" value="${Emp.country}" /></td>
          </tr>
          <tr>
            <td>HomePhone</td>
            <td>
              <input type="text" name="HomePhone" value="${Emp.homePhone}" />
            </td>
          </tr>
          <tr>
            <td>Extension</td>
            <td>
              <input type="text" name="Extension" value="${Emp.extension}" />
            </td>
          </tr>
          <tr>
            <td>Photo</td>
            <td><input type="text" name="Photo" value="${Emp.photo}" /></td>
          </tr>
          <tr>
            <td>Notes</td>
            <td><input type="text" name="Notes" value="${Emp.notes}" /></td>
          </tr>
          <tr>
            <td>ReportsTo</td>
            <td>
              <input type="text" name="reportsTo" value="${Emp.reportsTo}" />
            </td>
          </tr>
          <tr>
            <td>PhotoPath</td>
            <td>
              <input type="text" name="PhotoPath" value="${Emp.photoPath}" />
            </td>
          </tr>
        </tbody>
      </table>
      <input type="submit" name="submit" value="Update" />
    </form>
  </body>
</html>
