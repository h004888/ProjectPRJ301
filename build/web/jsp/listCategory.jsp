<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="entity.Categories, java.util.Vector" %> <% Vector<Categories>
  vector = (Vector<Categories
    >) request.getAttribute("categoryData"); String titleTable = (String)
    request.getAttribute("titleTable"); %>
    <!DOCTYPE html>
    <html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>List Categories</title>
      </head>
      <body>
        <form action="CategoryURL" method="get">
          <p>Search Name <input type="text" name="cname" /></p>
          <p><input type="submit" name="submit" value="Search" /></p>
          <input type="reset" value="Clear" />
        </form>
        <p><a href="CategoryURL?service=insertCategory">Insert Category</a></p>
        <table border="1">
          <caption>
            <%=titleTable%>
          </caption>
          <tr>
            <th>CategoryID</th>
            <th>CategoryName</th>
            <th>Update</th>
            <th>Delete</th>
          </tr>
          <% for (Categories category : vector) { %>
          <tr>
            <td><%=category.getCategoryID()%></td>
            <td><%=category.getCategoryName()%></td>
            <td></td>
            <td>
              <a
                href="CategoryURL?service=deleteCategory&cid=<%=category.getCategoryID()%>"
                >Delete</a
              >
            </td>
          </tr>
          <% } %>
        </table>
      </body>
    </html>
  </Categories></Categories
>
