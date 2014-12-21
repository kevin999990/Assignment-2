/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import model.Invoice;
import model.InvoiceDao;
import model.Member;
import model.Product;
import model.Purchase;
import model.PurchaseDao;

/**
 *
 * @author Kevin
 */
public class processPurchase extends HttpServlet {

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
        InvoiceDao invoiceDao = new InvoiceDao(em);
        PurchaseDao purchaseDao = new PurchaseDao(em);
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/YYYY");
        String tDate = sd.format(new Date());
        try {
            HttpSession session = request.getSession();
            Member member = (Member) session.getAttribute("loginMember");
            List<Product> products = (List) session.getAttribute("allProduct");
            List<Purchase> selectedProduct = new ArrayList<Purchase>();
            for (int i = 0; i < products.size(); i++) {
                int quantity = Integer.parseInt(request.getParameter("productQtyList[" + i + "]"));
                if (quantity > 0) {
                    selectedProduct.add(new Purchase(quantity, products.get(i)));
                }
            }

            if (!selectedProduct.isEmpty()) {
                utx.begin();
                Invoice invoice = new Invoice(invoiceDao.autoId(), tDate, member, selectedProduct);
                for (int i = 0; i < invoice.getPurchaseList().size(); i++) {
                    selectedProduct.get(i).setInvoiceNo(invoice);
                    selectedProduct.get(i).setPurchaseId(purchaseDao.autoId() + i);
                }
                em.persist(invoice);

                utx.commit();
                session.removeAttribute("invoice");
                session.setAttribute("invoice", invoice);
                response.sendRedirect("./secureUser/purchase/purchaseSuccess.jsp");
            }

        } catch (NumberFormatException | NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | IOException e) {
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
