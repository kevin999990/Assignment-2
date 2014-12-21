/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Product;
import model.ProductDao;

/**
 *
 * @author Kevin
 */
public class ProductServlet extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();
            ProductDao productDao = new ProductDao(em);

            String action = request.getParameter("action");

            Product product = (Product) session.getAttribute("product");

            if ("Add".equalsIgnoreCase(action)) {
                utx.begin();
                productDao.addProduct(product);
                utx.commit();
            } else if ("Delete".equalsIgnoreCase(action)) {
                utx.begin();
                productDao.deleteProduct(product.getProductCode());
                utx.commit();
            } else if ("Update".equalsIgnoreCase(action)) {
                utx.begin();
                productDao.updateProduct(product);
                utx.commit();
            } else if ("Search".equalsIgnoreCase(action)) {

                String searchCriteria = request.getParameter("searchCriteria");
                String searchValue = request.getParameter("searchValue");

                session.removeAttribute("allProduct");
                session.setAttribute("allProduct", productDao.searchProduct(searchCriteria, searchValue));
                response.sendRedirect("./secureAdmin/product/productMenu.jsp");
                return;

            }

            session.removeAttribute("product");
            session.removeAttribute("allProduct");

            session.setAttribute("action", action);
            session.setAttribute("product", product);
            session.setAttribute("allProduct", productDao.allProduct());

            response.sendRedirect("./secureAdmin/product/productMenu.jsp");
        } catch (Exception e) {
                 HttpSession session = request.getSession(true);
            session.setAttribute("error", e);
            response.sendRedirect("/EuYanSang/error/invalidInput.jsp");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
