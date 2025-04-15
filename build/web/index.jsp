<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : Mar 11, 2025, 3:21:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html lang="en">
    <head>
        <title>Laptop Store</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <!-- Bootstrap CSS -->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <!-- Custom CSS -->
        <link href="css/style.css" rel="stylesheet" />
    </head>
    <body>
        <jsp:include page="/jsp/Panner.jsp"></jsp:include>


            <!-- Main Content -->
            <div class="container">
                <div class="row">
                    <!-- Categories Column -->
                    <div class="col-md-3 mb-4">
                        <div class="card">
                            <div class="card-header bg-dark text-white">
                                <h5 class="card-title mb-0"><i class="fas fa-laptop-code me-2"></i>Brands</h5>
                            </div>
                            <div class="card-body">
                                <ul class="list-group list-group-flush ">
                                <c:forEach var="cat" items="${vectorCat}">

                                    <li
                                        class="list-group-item d-flex justify-content-between align-items-center ${tag == cat.categoryID ?"active":""}"
                                        >
                                        <a class="${tag == cat.categoryID ?"text-light":""} text-decoration-none" href="home?service=ProductByCid&cid=${cat.categoryID}">
                                            <i class="fas fa-tag me-2"></i>${cat.categoryName}
                                        </a>

                                        <span class="badge bg-primary rounded-pill"></span>
                                    </li>



                                </c:forEach>



                            </ul>
                        </div>
                    </div>
                </div>
                <jsp:include page="/jsp/content.jsp"></jsp:include>

            </div>
        </div>

        <!-- Footer -->
        <footer class="bg-dark text-white py-4 mt-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h5><i class="fas fa-laptop me-2"></i>Laptop Store</h5>
                        <p class="small">Cung cấp các sản phẩm laptop chất lượng cao với giá cả hợp lý.</p>
                    </div>
                    <div class="col-md-3">
                        <h5>Liên kết</h5>
                        <ul class="list-unstyled">
                            <li><a href="#" class="text-white"><i class="fas fa-home me-1"></i> Trang chủ</a></li>
                            <li><a href="#" class="text-white"><i class="fas fa-info-circle me-1"></i> Giới thiệu</a></li>
                            <li><a href="#" class="text-white"><i class="fas fa-phone me-1"></i> Liên hệ</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <h5>Kết nối</h5>
                        <div class="d-flex">
                            <a href="#" class="text-white me-3"><i class="fab fa-facebook fa-lg"></i></a>
                            <a href="#" class="text-white me-3"><i class="fab fa-twitter fa-lg"></i></a>
                            <a href="#" class="text-white me-3"><i class="fab fa-instagram fa-lg"></i></a>
                            <a href="#" class="text-white"><i class="fab fa-youtube fa-lg"></i></a>
                        </div>
                    </div>
                </div>
                <hr class="my-3">
                <div class="text-center small">
                    <p class="mb-0">&copy; 2025 Laptop Store. All rights reserved.</p>
                </div>
            </div>
        </footer>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- jQuery UI -->
        <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Custom JS -->
        <script src="js/main.js"></script>
    </body>
</html>
