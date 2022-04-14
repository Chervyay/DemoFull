package ru.chervy.demoexam.manager;

import ru.chervy.demoexam.Application;
import ru.chervy.demoexam.entity.ProductEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductEntityManager
{
    public static List<ProductEntity> selectAll() throws SQLException {

        List<ProductEntity> list = new ArrayList<>();
        try(Connection c = Application.getConnetion())
        {
            String sql = "select * from product";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            while(resultSet.next()){
                list.add(new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("ArticleNumber"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getInt("ProductionPersonCount"),
                        resultSet.getInt("ProductionWorkshopNumber"),
                        resultSet.getFloat("MinCostForAgent")));
            }
        }
        return list;
    }

    public static void update(ProductEntity product) throws SQLException {
        try(Connection c = Application.getConnetion())
        {
            String sql = "Update product set Title = ?, ProductType = ?," +
                    " ArticleNumber = ?, Description = ?,"+
                    "Image = ?, ProductionPersonCount = ?,"+
                    " ProductionWorkshopNumber = ?, MinCostForAgent = ? where id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getArticleNumber());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImagePath());
            ps.setInt(6, product.getPersonCount());
            ps.setInt(7, product.getWorkshopNumber());
            ps.setFloat(8, product.getMinimalCostForAgent());
            ps.setInt(9, product.getId());

            ps.executeUpdate();
        }

    }

    public static void insert(ProductEntity product) throws SQLException {
        try(Connection c = Application.getConnetion())
        {
            String sql = "Insert into product(Title,ProductType,ArticleNumber,Description,Image,ProductionPersonCount,ProductionWorkshopNumber,MinCostForAgent) values (?,?,?,?,?,?,?,?) ";
            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getArticleNumber());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImagePath());
            ps.setInt(6, product.getPersonCount());
            ps.setInt(7, product.getWorkshopNumber());
            ps.setFloat(8, product.getMinimalCostForAgent());

            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next())
            {
                product.setId(keys.getInt(1));
                return;
            }
            throw new SQLException("Entity not added");
        }
    }

    public static void delete(ProductEntity product) throws SQLException {

        List<ProductEntity> list = new ArrayList<>();
        try(Connection c = Application.getConnetion())
        {
            String sql = "delete from product where id = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1,product.getId());
            ps.executeUpdate();
        }
    }
}


