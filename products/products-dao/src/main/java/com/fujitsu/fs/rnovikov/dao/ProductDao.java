package com.fujitsu.fs.rnovikov.dao;

import com.fujitsu.fs.rnovikov.entities.Product;

public interface ProductDao {

    void save(Product product);
    void delete(int id);

    Product[] getAll();
}
