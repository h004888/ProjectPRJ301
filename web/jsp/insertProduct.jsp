<%-- 
    Document   : insertProduct
    Created on : Feb 21, 2025, 1:34:07 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <form action="ProductURL" method="post">
            <input type="hidden" name="service" value="insertProduct">
            <table>
                <tr>
                    <td>ProductID</td>
                    <td><input type="text" name="ProductID" id=""></td>
                </tr>
                <tr>
                    <td>ProductName</td>
                    <td><input type="text" name="ProductName" id=""></td>
                </tr>
                <tr>
                    <td>Supplier Name</td>
                    <%
               ResultSet rsCate=(ResultSet)request.getAttribute("rsCate");
               ResultSet rsSupp=(ResultSet)request.getAttribute("rsSupp");
                    %>
                    <td>
                        <select name="SupplierID" id="">
                            <%while(rsSupp.next()){%>
                                <option value="<%=rsSupp.getInt(1)%>"><%=rsSupp.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Category Name</td>
                    <td>
                        <select name="CategoryID" id="">
                           <%while(rsCate.next()){%>
                                <option value="<%=rsCate.getInt(1)%>"><%=rsCate.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>QuantityPerUnit</td>
                    <td><input type="text" name="QuantityPerUnit" id=""></td>
                </tr>
                <tr>
                    <td>UnitPrice</td>
                    <td><input type="text" name="UnitPrice" id=""></td>
                </tr>
                <tr>
                    <td>UnitsInStock</td>
                    <td><input type="number" name="UnitsInStock" id=""></td>
                </tr>
                <tr>
                    <td>UnitsOnOrder</td>
                    <td><input type="number" name="UnitsOnOrder" id=""></td>
                </tr>
                <tr>
                    <td>ReorderLevel</td>
                    <td><input type="number" name="ReorderLevel" id=""></td>
                </tr>
                <tr>
                    <td>Discontinued</td>
                    <td>
                        <input type="radio" name="Discontinued" value="1">Discontinued
                        <input type="radio" name="Discontinued" value="0">Continued
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="insertProduct"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
