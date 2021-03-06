package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;
import com.fujitsu.fs.rnovikov.utils.ConnectionPool;
import com.fujitsu.fs.rnovikov.utils.SingleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by User on 07.01.2018.
 */
public class ProductDaoImpl implements ProductDao<Product,Integer,String> {

    private static ProductDao<Product,Integer,String> productDao;

    private ProductDaoImpl() {

    }

    public static ProductDao<Product,Integer,String> getInstance() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }
        return productDao;
    }


    public void save(Product product)  {

        try(Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products (name, price, description, detailed_description, quantity,photo) VALUES (?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getDetailed_description());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setBlob(6, product.getPhoto());

            preparedStatement.execute();
        } catch(SQLException e) {

        }
    }

    public void delete(Integer productId) {

        deleteConcreteProductInBaskets(productId);

        try(Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?");
            preparedStatement.setInt(1, productId);

            preparedStatement.execute();

        } catch (SQLException e) {

        }
    }

    public void deleteConcreteProductInBaskets(Integer productId) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM basket WHERE product_id = ?");
            preparedStatement.setInt(1, productId);

            preparedStatement.execute();

        } catch (SQLException e) {

        }
    }

    public Product[] getAll() {

        String query = "SELECT * FROM products";
        Product product = null;
        List<Product> products = new ArrayList();

        try(Connection connection = ConnectionPool.getConnection()) {

            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {

                product = new Product(
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("description"),
                        resultSet.getString("detailed_description"),
                        resultSet.getInt("quantity"),
                        resultSet.getBlob("photo")
                );

                product.setProduct_id(resultSet.getInt("product_id"));

                products.add(product);
            }
        } catch(SQLException e) {

        }

        return products.toArray(new Product[0]);
    }

    public Product getById(Integer id) {

        Product product = null;

        try(Connection connection  = ConnectionPool.getConnection()) {

            ArrayList<Product> products = new ArrayList<Product>();


            PreparedStatement preparedStatemet = null;
            if (connection != null) {
                preparedStatemet = connection.prepareStatement("SELECT * FROM products WHERE product_id = ?");
                preparedStatemet.setInt(1, id);

                ResultSet resultSet = preparedStatemet.executeQuery();
                while (resultSet.next()) {

                    product = new Product(
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("description"),
                            resultSet.getString("detailed_description"),
                            resultSet.getInt("quantity"),
                            resultSet.getBlob("photo")
                    );

                    product.setProduct_id(resultSet.getInt("product_id"));

                }
            }
        } catch(SQLException e) {

        }

        return product;

    }

    public Product getByName(String name){

        Product product = null;
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE name = ?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            product = new Product(
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("description"),
                    resultSet.getString("detailed_description"),
                    resultSet.getInt("quantity"),
                    resultSet.getBlob("photo")
            );
            product.setProduct_id(resultSet.getInt("product_id"));


        } catch (SQLException e) {

        }
        return product;
    }

    public Product[] getDecadeProduct(Integer decadeId) {
        Product[] allproducts = getAll();
        int productsNumber = allproducts.length;
        List<Product> decadeProducts = new ArrayList<Product>();
        for (int k = productsNumber - 1 - 10 * (decadeId - 1); k > productsNumber - 1 - 10 * (decadeId); k--) {
            if (k >= 0) {
                decadeProducts.add(allproducts[k]);
            }
        }
        Product[] decade = new Product[decadeProducts.size()];
        for (int i = 0; i < decadeProducts.size(); i++) {
            decade[i] = decadeProducts.get(i);
        }


        return decade;
    }

    @Override
    public void editProduct(Integer product_id, Integer price, String detailed_description) {
        try (Connection connection = ConnectionPool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET price = ?, detailed_description = ? WHERE product_id = ?");
            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, detailed_description);
            preparedStatement.setInt(3, product_id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
