package dao;

import entity.Category;
import util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoItf implements CategoryDAO{
    @Override
    public List<Category> getAllCategory() {

        //mở kết nối
        Connection connection = ConnectionDB.openConnection();
        List<Category> categories = new ArrayList<>();
        try {
            // thực hiện lệnh SQL thông qua preparedStament
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM category");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("category_name"));
                category.setCategoryStatus(resultSet.getBoolean("category_status"));
                categories.add(category);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public Boolean addCategory(Category category) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sql = "INSERT INTO category(category_name,category_status) VALUE (?,?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,category.getCategoryName());
            statement.setBoolean(2,category.isCategoryStatus());
            int check = statement.executeUpdate();
            if(check>0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return null;
    }

    @Override
    public Category findById(int id) {
        Connection connection = ConnectionDB.openConnection();
        Category category = new Category();
        try {
            String sql = "SELECT * FROM category WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()){
                return null;
            }
            while (resultSet.next()){
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("category_name"));
                category.setCategoryStatus(resultSet.getBoolean("category_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return category;
    }

    @Override
    public boolean updateCategory(Category category) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sql = "UPDATE category SET category_name = ?, category_status = ? WHERE id =  ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,category.getCategoryName());
            statement.setBoolean(2,category.isCategoryStatus());
            statement.setInt(3,category.getCategoryId());
            int check = statement.executeUpdate();
            if (check > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public void deleteCategory(int id) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sql = "DELETE FROM category WHERE id =  ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
    }

    @Override
    public List<Category> searchByName(String keyword) {
        Connection connection = ConnectionDB.openConnection();
        List<Category> categories = new ArrayList<>();
        try {
            // thực hiện lệnh SQL thông qua preparedStament
            String sql = "SELECT * FROM category WHERE category_name LIKE ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,"%"+keyword+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("category_name"));
                category.setCategoryStatus(resultSet.getBoolean("category_status"));
                categories.add(category);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return categories;
    }


}

