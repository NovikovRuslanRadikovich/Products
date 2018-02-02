package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;
import com.fujitsu.fs.rnovikov.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static ProductDao productDao;

    private ProductDaoImpl() {

    }

    public static ProductDao getInstance() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }
        return productDao;
    }

    @Override
    public void save(Product product) {
        try(Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (name) VALUES (?);");

            preparedStatement.setString(1, product.getName());

            preparedStatement.execute();
        } catch(SQLException e) {

        }
    }

    @Override
    public void delete(int productId) {
        try(Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE product_id = ?");
            preparedStatement.setInt(1, productId);

            preparedStatement.execute();

        } catch (SQLException e) {

        }
    }

    @Override
    public Product[] getAll() {
        String query = "SELECT * FROM product";
        Product product = null;
        List<Product> products = new ArrayList();

        try(Connection connection = ConnectionPool.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {

                product = new Product(
                        resultSet.getString("name")
                );

                product.setProduct_id(resultSet.getInt("product_id"));

                products.add(product);
            }
        } catch(SQLException e) {

        }

        return products.toArray(new Product[0]);
    }
}
