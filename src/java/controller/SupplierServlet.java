/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Supplier;
import model.SupplierDao;

/**
 *
 * @author Kevin
 */
public class SupplierServlet extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            SupplierDao supplierDao = new SupplierDao(em);

            String action = request.getParameter("action");

            Supplier supplier = (Supplier) session.getAttribute("supplier");

            if ("Add".equalsIgnoreCase(action)) {
                utx.begin();
                supplierDao.addSupplier(supplier);
                utx.commit();
            } else if ("Delete".equalsIgnoreCase(action)) {
                utx.begin();
                supplierDao.deleteSupplier(supplier.getSupplierId());
                utx.commit();
            } else if ("Update".equalsIgnoreCase(action)) {
                utx.begin();
                supplierDao.updateSupplier(supplier);
                utx.commit();
            } else if ("Search".equalsIgnoreCase(action)) {

                String searchCriteria = request.getParameter("searchCriteria");
                String searchValue = request.getParameter("searchValue");

                session.removeAttribute("allSupplier");
                session.setAttribute("allSupplier", supplierDao.searchSupplier(searchCriteria, searchValue));
                response.sendRedirect("./secureAdmin/supplier/supplierMenu.jsp");
                return;

            }

            session.removeAttribute("supplier");
            session.removeAttribute("allSupplier");
            session.removeAttribute("autoId");

            session.setAttribute("action", action);
            session.setAttribute("supplier", supplier);
            session.setAttribute("allSupplier", supplierDao.allSupplier());
            session.setAttribute("autoId", supplierDao.autoId());
            response.sendRedirect("./secureAdmin/supplier/supplierMenu.jsp");
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
