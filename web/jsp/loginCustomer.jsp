<%-- Document : loginCustomer Created on : Feb 28, 2025, 1:10:42 PM Author :
ADMIN --%> <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Đăng nhập khách hàng</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
    />
    <style>
      body {
        margin: 0;
        padding: 0;
        height: 100vh;
        background: linear-gradient(
          135deg,
          #0b4182 0%,
          #1e88e5 60%,
          #40c4ff 100%
        );
        overflow: hidden;
      }

      .login-page {
        display: flex;
        height: 100vh;
        width: 100%;
      }

      .illustration {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: 0;
      }

      .illustration-content {
        position: relative;
        width: 100%;
        height: 100%;
      }

      .moon {
        position: absolute;
        top: 100px;
        right: 40%;
        width: 60px;
        height: 60px;
        background-color: white;
        border-radius: 50%;
        box-shadow: 0 0 20px 5px rgba(255, 255, 255, 0.5);
      }

      .mountain {
        position: absolute;
        bottom: 100px;
        width: 100%;
        height: 60%;
      }

      .mountain-1 {
        position: absolute;
        bottom: 0;
        left: 10%;
        width: 300px;
        height: 200px;
        background-color: #0d3a6e;
        clip-path: polygon(0% 100%, 50% 0%, 100% 100%);
      }

      .mountain-2 {
        position: absolute;
        bottom: 0;
        right: 15%;
        width: 400px;
        height: 250px;
        background-color: #0b325e;
        clip-path: polygon(0% 100%, 30% 30%, 70% 60%, 100% 100%);
      }

      .tower {
        position: absolute;
        bottom: 170px;
        left: 30%;
        width: 80px;
        height: 120px;
      }

      .tower-base {
        width: 80px;
        height: 20px;
        background-color: #194677;
      }

      .tower-stand {
        width: 30px;
        height: 60px;
        background-color: #194677;
        margin: 0 auto;
      }

      .tower-top {
        width: 60px;
        height: 30px;
        background-color: #194677;
        margin: 0 auto;
      }

      .tower-windows {
        display: flex;
        justify-content: space-around;
      }

      .window {
        width: 8px;
        height: 8px;
        background-color: #fff;
        margin-top: 5px;
      }

      .tower-2 {
        bottom: 220px;
        right: 30%;
        left: auto;
      }

      .stars {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
      }

      .star {
        position: absolute;
        width: 2px;
        height: 2px;
        background-color: white;
        border-radius: 50%;
      }

      .login-form-container {
        position: relative;
        z-index: 1;
        width: 600px;
        margin: 0 auto;
        background-color: white;
        box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
        padding: 50px;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: center;
      }

      .login-header {
        margin-bottom: 40px;
        text-align: center;
      }

      .login-header h2 {
        color: #1976d2;
        font-size: 2.5rem;
        font-weight: 700;
        margin-bottom: 0.5rem;
      }

      .login-header p {
        color: #aaa;
      }

      .form-group {
        margin-bottom: 20px;
      }

      .form-control {
        height: 50px;
        background-color: #f5f5f5;
        border: none;
        border-radius: 5px;
        padding-left: 15px;
      }

      .form-control:focus {
        box-shadow: none;
        border: 1px solid #1976d2;
      }

      .form-check {
        margin-bottom: 20px;
      }

      .forgot-password {
        text-align: right;
        color: #aaa;
        text-decoration: none;
        font-size: 0.9rem;
      }

      .forgot-password:hover {
        color: #1976d2;
      }

      .btn-next {
        background-color: #0d47a1;
        color: white;
        border: none;
        height: 50px;
        border-radius: 5px;
        font-weight: 600;
      }

      .btn-next:hover {
        background-color: #1565c0;
      }

      .create-account {
        text-align: center;
        margin-top: 20px;
        color: #aaa;
        font-size: 0.9rem;
      }

      .create-account a {
        color: #1976d2;
        text-decoration: none;
      }

      .error-message {
        color: #dc3545;
        margin-bottom: 15px;
        text-align: center;
      }

      .login-container {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
        width: 100%;
        position: relative;
      }
    </style>
  </head>
  <body>
    <div class="login-page">
      <!-- Phần minh họa background -->
      <div class="illustration">
        <div class="illustration-content">
          <div class="moon"></div>

          <!-- Tạo ngẫu nhiên 50 ngôi sao -->
          <div class="stars">
            <% for(int i = 0; i < 50; i++) { double top = Math.random() * 100;
            double left = Math.random() * 100; double size = 1 + Math.random() *
            2; double opacity = 0.5 + Math.random() * 0.5; %>
            <div
              class="star"
              style="
                top: <%= top %>%;
                left: <%= left %>%;
                width: <%= size %>px;
                height: <%= size %>px;
                opacity: <%= opacity %>;
              "
            ></div>
            <% } %>
          </div>

          <div class="mountain">
            <div class="mountain-1"></div>
            <div class="mountain-2"></div>
          </div>

          <!-- Tháp canh 1 -->
          <div class="tower">
            <div class="tower-top">
              <div class="tower-windows">
                <div class="window"></div>
                <div class="window"></div>
                <div class="window"></div>
                <div class="window"></div>
                <div class="window"></div>
              </div>
            </div>
            <div class="tower-stand"></div>
            <div class="tower-base"></div>
          </div>

          <!-- Tháp canh 2 -->
          <div class="tower tower-2">
            <div class="tower-top">
              <div class="tower-windows">
                <div class="window"></div>
                <div class="window"></div>
                <div class="window"></div>
                <div class="window"></div>
                <div class="window"></div>
              </div>
            </div>
            <div class="tower-stand"></div>
            <div class="tower-base"></div>
          </div>
        </div>
      </div>

      <!-- Container để căn giữa form -->
      <div class="login-container">
        <!-- Phần form đăng nhập -->
        <div class="login-form-container">
          <div class="login-header">
            <h2>HELLO!</h2>
            <p>Vui lòng đăng nhập để tiếp tục</p>
          </div>

          <% String message=(String)request.getAttribute("message"); if(message
          != null && !message.isEmpty()) { %>
          <div class="error-message"><%=message%></div>
          <% } %>

          <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="loginCustomer" />

            <div class="form-group">
              <div class="input-group">
                <span class="input-group-text bg-light border-0">
                  <i class="fas fa-user text-muted"></i>
                </span>
                <input
                  type="text"
                  class="form-control"
                  id="userName"
                  name="userName"
                  placeholder="Email hoặc tên đăng nhập"
                  required
                />
              </div>
            </div>

            <div class="form-group">
              <div class="input-group">
                <span class="input-group-text bg-light border-0">
                  <i class="fas fa-lock text-muted"></i>
                </span>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  name="pass"
                  placeholder="Mật khẩu"
                  required
                />
              </div>
            </div>

            <div class="d-flex justify-content-between mb-4">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" id="remember" />
                <label class="form-check-label text-muted" for="remember"
                  >Nhớ mật khẩu</label
                >
              </div>
              <a href="#" class="forgot-password">Quên mật khẩu?</a>
            </div>

            <div class="d-grid">
              <button
                type="submit"
                class="btn btn-next"
                name="submit"
                value="login"
              >
                TIẾP TỤC <i class="fas fa-arrow-right ms-2"></i>
              </button>
            </div>

            <div class="create-account">
              <span>Chưa có tài khoản? <a href="#">Đăng ký</a></span>
            </div>
          </form>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
