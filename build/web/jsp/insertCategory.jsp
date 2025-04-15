<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Insert Category</title>
  </head>
  <body>
    <form action="CategoryURL" method="post">
      <input type="hidden" name="service" value="insertCategory" />
      <table>
        <tr>
          <td>CategoryID</td>
          <td><input type="text" name="CategoryID" /></td>
        </tr>
        <tr>
          <td>CategoryName</td>
          <td><input type="text" name="CategoryName" /></td>
        </tr>
        <tr>
          <td><input type="submit" name="submit" value="Insert Category" /></td>
          <td><input type="reset" value="Clear" /></td>
        </tr>
      </table>
    </form>
  </body>
</html>
