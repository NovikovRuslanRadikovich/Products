package com.fujitsu.fs.rnovikov.servlets;

import com.fujitsu.fs.rnovikov.dao.ProductDaoImpl;
import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by User on 07.01.2018.
 */
@WebServlet("/getproductsnext/*")
class GetProductsNext extends HttpServlet {

    private ProductDao<Product> productDao;

    @Override
    public void init() {

        productDao = ProductDaoImpl.getInstance();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request,response);

    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.valueOf(request.getPathInfo().substring(1));
        try {
            request.setAttribute("productsDecade",productDao.getDecadeProduct(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getServletConfig().getServletContext().getRequestDispatcher("get_products_next.ftl").forward(request,response);
    }




}
