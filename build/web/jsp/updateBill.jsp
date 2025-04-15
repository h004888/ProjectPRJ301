<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Update Bill Status</title>
  </head>
  <body>
     
    <form action="billURL" method="get">
      <input type="hidden" name="service" value="updateBill" />
      <!-- Nếu có thông tin Order thì truyền OrderID qua form -->
      <c:if test="${not empty orderID}">
        <input type="hidden" name="orID" value="${orderID}" />
      </c:if>
      <h2>Mời bạn cập nhật status cho ID ${orID}</h2>
      <input type="hidden" name="orID" value="${orID}" />
      <label><input type="radio" name="select" value="1" /> done</label>
      <label><input type="radio" name="select" value="2" /> process</label>
      <label><input type="radio" name="select" value="3" /> wait</label>
      <input type="submit" name="submit" value="CHANGE" />
    </form>

    <c:if test="${not empty message}">
      <h2>${message}</h2>
    </c:if>
    <a href="billURL?service=listBill">Back to list bill</a>
  </body>
</html>
