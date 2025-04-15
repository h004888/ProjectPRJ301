<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : listBill
    Created on : Mar 28, 2025, 9:34:11 AM
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
        <table border="1">
            <thead>
                <tr>
                    <th>orderID</th>
                    <th>customerID</th>
                    <th>orderDate</th>
                    <th>total</th>
                    <th>status</th>
                    <th>view</th>
                    <th>Status</th>
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

    </body>
</html>
