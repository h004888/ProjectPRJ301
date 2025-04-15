<%-- 
    Document   : MyJSP
    Created on : Feb 18, 2025, 3:43:18 PM
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
        <h1>Script normal java code</h1>
        <%
            //code java here (as servlet)
            int a=200;
            out.print("<h2>a="+a+"</h2>");
            if(a%2!=1){
                out.print(a +"là số chẵn ");

            }
        %>
        <h1>Expression : display value of Expression</h1>
        <h2 style="color: red;">value a*2 = <%=a*2 %></h2>
        <% for(int i=10;i<300;i+=10){ %>
            <hr width="<%=i%>"/>
        <% } %>

        <h1>delcare : global variable; method</h1>
        <%! int global=1000; %>
        <%! String wellcome(String name){
            return "Wellcome "+name;
        } %>
        <h2> <%= wellcome("Hùng dại gái") %></h2>
    </body>
</html>
