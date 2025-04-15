package controller;

import entity.Categories;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.DAOCategories;
import java.util.logging.Logger;
import java.util.logging.Level;

@WebServlet(name = "CategoryController", urlPatterns = { "/CategoryURL" })
public class CategoryController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CategoryController.class.getName());
    private final DAOCategories dao = new DAOCategories();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service");

        if (service == null) {
            service = "listCategories";
        }

        try {
            switch (service) {
                case "deleteCategory":
                    handleDeleteCategory(request, response);
                    break;
                case "insertCategory":
                    handleInsertCategory(request, response);
                    break;
                case "listCategories":
                    handleListCategories(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi xử lý request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cid = Integer.parseInt(request.getParameter("cid"));
            int result = dao.removeCategory(cid);

            switch (result) {
                case 0:
                    request.setAttribute("message", "Xóa thất bại");
                    break;
                case -1:
                    request.setAttribute("message", "Không thể xóa do ràng buộc khóa ngoại");
                    break;
                default:
                    response.sendRedirect("CategoryURL");
                    return;
            }
            request.getRequestDispatcher("/jsp/listCategory.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID danh mục không hợp lệ", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID danh mục không hợp lệ");
        }
    }

    private void handleInsertCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String submit = request.getParameter("submit");

        if (submit == null) {
            request.getRequestDispatcher("/jsp/insertCategory.jsp").forward(request, response);
            return;
        }

        try {
            int categoryId = Integer.parseInt(request.getParameter("CategoryID"));
            String categoryName = request.getParameter("CategoryName");
            String description = request.getParameter("Description");
            String picture = request.getParameter("Picture");

            Categories category = new Categories(categoryId, categoryName, description, picture);
            int result = dao.addCategory(category);

            if (result > 0) {
                response.sendRedirect("CategoryURL");
            } else {
                request.setAttribute("message", "Thêm danh mục thất bại");
                request.getRequestDispatcher("/jsp/insertCategory.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Dữ liệu đầu vào không hợp lệ", e);
            request.setAttribute("message", "ID danh mục không hợp lệ");
            request.getRequestDispatcher("/jsp/insertCategory.jsp").forward(request, response);
        }
    }

    private void handleListCategories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String submit = request.getParameter("submit");
        List<Categories> categories;

        if (submit == null) {
            categories = dao.getCategories("SELECT * FROM Categories");
        } else {
            String categoryName = request.getParameter("cname");
            categories = dao.getCategories(
                    "SELECT * FROM Categories WHERE CategoryName LIKE '%" + categoryName + "%'");
        }

        request.setAttribute("categoryData", categories);
        request.setAttribute("titleTable", "Danh sách Categories");
        request.getRequestDispatcher("/jsp/listCategory.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}