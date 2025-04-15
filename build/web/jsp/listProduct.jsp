<%-- 
    Document   : listProduct
    Created on : Feb 18, 2025, 4:45:12 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Products,java.util.Vector" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <p align="right">
            <a href="CartURL?service=showcart">View Cart</a>
            <% String username = (String)session.getAttribute("username"); %>
            <% if(username==null){ %>
            <a href="CustomerURL?service=loginCustomer">Login</a>
            <a href="CustomerURL?service=insertCustomer">Register</a>
            <% }else{ %>
            welcome <%=username%>
            <a href="CustomerURL?service=logoutCustomer">Logout</a>
            <% } %>
        </p>

        <% 
        Vector<Products> vector=
                (Vector<Products>)request.getAttribute("productData");
        String titleTable=
                (String)request.getAttribute("titleTable");
        %>
        <form action="ProductURL" method="get">
            <input type="hidden" name="service" value="listProducts">
            <p>Search Name <input type="text" name="pname">
            <p><input type="submit" name="submit" value="Search">
                <input type="reset" value="Clear">
        </form>
        <p><a href="ProductURL?service=insertProduct">insertProduct</a>
        <table >
            <caption><%=titleTable%></caption>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>SupplierID</th>
                <th>CategoryID</th>
                <th>QuantityPerUnit</th>
                <th>UnitPrice</th>
                <th>UnitsInStock</th>
                <th>UnitsOnOrder</th>
                <th>ReorderLevel</th>
                <th>Discontinued</th>
                <th>update</th>
                <th>dalete</th>
                <<th>add2cart</th>
            </tr>
            <% for (Products products : vector) {%>
            <tr>
                <td><%=products.getProductID()%></td>
                <td><%=products.getProductName()%></td>
                <td><%=products.getSupplierID()%></td>
                <td><%=products.getCategoryID()%></td>
                <td><%=products.getQuantityPerUnit()%></td>
                <td><%=products.getUnitPrice()%></td>
                <td><%=products.getUnitsInStock()%></td>
                <td><%=products.getUnitsOnOrder()%></td>
                <td><%=products.getReorderLevel()%></td>
                <td><%=products.isDiscontinued()%></td>
                <td><a href="ProductURL?service=updateProducts&pid=<%=products.getProductID()%>">update</a></td>
                <td><a href="ProductURL?service=deleteProduct&pid=<%=products.getProductID()%>">delete</a></td>
                <td><a href="CartURL?service=add2cart&pid=<%=products.getProductID()%>">add2cart</a></td>

            </tr>
            <%}%>
        </table>
    </body>
</html>
