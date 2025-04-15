package model;

import entity.Categories;
import java.sql.SQLException;
import java.util.Vector;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class DAOCategories extends DBConnect {

    public Vector<Categories> getCategories(String sql) {
        Vector<Categories> vector = new Vector<Categories>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");
                String Picture = rs.getString("Picture");

                Categories cat = new Categories(CategoryID, CategoryName, Description, Picture);
                vector.add(cat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int addCategory(Categories cat) {
        int n = 0;
        String sql = "INSERT INTO [Categories]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description]\n"
                + "           ,[Picture])\n"
                + "     VALUES\n"
                + "           ('" + cat.getCategoryName() + "'\n"
                + "           ,'" + cat.getDescription() + "'\n"
                + "           ,'" + cat.getPicture() + "')";

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeCategory(int categoryId) {
        int result = 0;
        String sql = "DELETE FROM Categories WHERE CategoryID = " + categoryId;

        try {
            // Kiểm tra xem category có products không
            Statement checkState = conn.createStatement();
            ResultSet rs = checkState.executeQuery(
                    "SELECT COUNT(*) as count FROM Products WHERE CategoryID = " + categoryId);

            if (rs.next() && rs.getInt("count") > 0) {
                return -1; // Có ràng buộc khóa ngoại
            }

            // Thực hiện xóa nếu không có ràng buộc
            Statement state = conn.createStatement();
            result = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String[] args) {
        DAOCategories dao = new DAOCategories();
        int n = dao.addCategory(new Categories(0, "New Category", "Description", "PictureString"));
        if (n > 0) {
            System.out.println("inserted");
        }
        Vector<Categories> cat = dao.getCategories("SELECT * FROM Categories");

        System.out.println("Danh sách danh mục từ cơ sở dữ liệu:");
        for (Categories category : cat) {
            System.out.println(category);
        }
    }
}
