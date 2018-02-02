package com.fujitsu.fs.rnovikov;

import com.fujitsu.fs.rnovikov.dao.ProductDao;
import com.fujitsu.fs.rnovikov.dao.ProductDaoImpl;
import com.fujitsu.fs.rnovikov.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    ProductDao productDao;

    @Override
    public void init() throws ServletException {
        productDao = ProductDaoImpl.getInstance();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Product[] allproducts = productDao.getAll();

        request.setAttribute("productsDecade", allproducts);


        getServletConfig().getServletContext().getRequestDispatcher("/products_decade.ftl").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        super.doPost(request, response);
    }
}
