<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Access Denied</title>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
    <style>
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }

      body {
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #333;
      }

      .error-container {
        background-color: white;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
        text-align: center;
        max-width: 500px;
        width: 90%;
        animation: fadeIn 0.5s ease-in-out;
      }

      @keyframes fadeIn {
        from {
          opacity: 0;
          transform: translateY(-20px);
        }
        to {
          opacity: 1;
          transform: translateY(0);
        }
      }

      .error-icon {
        font-size: 80px;
        color: #e74c3c;
        margin-bottom: 20px;
      }

      h2 {
        font-size: 28px;
        margin-bottom: 20px;
        color: #2c3e50;
      }

      p {
        margin-bottom: 30px;
        line-height: 1.6;
        color: #7f8c8d;
      }

      .home-button {
        display: inline-block;
        padding: 12px 30px;
        background: linear-gradient(135deg, #3498db, #2980b9);
        color: white;
        text-decoration: none;
        border-radius: 50px;
        font-weight: 600;
        transition: all 0.3s ease;
        box-shadow: 0 4px 15px rgba(52, 152, 219, 0.3);
      }

      .home-button:hover {
        transform: translateY(-3px);
        box-shadow: 0 7px 20px rgba(52, 152, 219, 0.4);
      }

      .home-button i {
        margin-right: 8px;
      }
    </style>
  </head>
  <body>
    <div class="error-container">
      <div class="error-icon">
        <i class="fas fa-exclamation-circle"></i>
      </div>
      <h2>Truy cập bị từ chối</h2>
      <p>
        Bạn không có quyền truy cập trang này. Vui lòng liên hệ quản trị viên
        nếu bạn cho rằng đây là lỗi.
      </p>
      <a href="/projectMVC" class="home-button">
        <i class="fas fa-home"></i> Quay lại trang chủ
      </a>
    </div>
  </body>
</html>
