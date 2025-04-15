<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : listEmployees
    Created on : Mar 9, 2025, 9:33:08 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <h1>${goi}</h1>
        <table border="1">

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
                    <th>delete</th>
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
                        <td><a><a href="EmployeesURL?service=updateEmployees">update</a></td>
                        <td><a><a href="EmployeesURL?service=removeEmployees">remove</a></td>


                    </tr>
                </c:forEach>


            </tbody>
        </table>

    </body>
</html>
