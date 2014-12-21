/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Membership;
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
import model.MembershipDao;

/**
 *
 * @author Kevin
 */
public class MembershipServlet extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            MembershipDao membershipDao = new MembershipDao(em);
            String action = request.getParameter("action");
//            String membershipType = request.getParameter("type");
//            String fee = request.getParameter("fee");
//            String discount = request.getParameter("discount");
//            double membershipFee = 0.0;
//            double memberhipDiscount = 0.0;

//            if (!fee.isEmpty()) {
//                membershipFee = Double.parseDouble(fee);
//            }
//            if (!discount.isEmpty()) {
//                memberhipDiscount = Double.parseDouble(discount);
//            }
            Membership membership = (Membership) session.getAttribute("membership");
          

            if ("Add".equalsIgnoreCase(action)) {
                utx.begin();
                membershipDao.addMembership(membership);
                utx.commit();
            } else if ("Delete".equalsIgnoreCase(action)) {

                utx.begin();
                membershipDao.deleteMembership(membership.getMembershipType());
                utx.commit();

            } else if ("Update".equalsIgnoreCase(action)) {
                utx.begin();
                membershipDao.updateMembership(membership);
                utx.commit();
            } else if ("Search".equalsIgnoreCase(action)) {
                String searchCriteria = request.getParameter("searchCriteria");
                Object searchValue = request.getParameter("searchValue");

                session.removeAttribute("allMembership");
                session.setAttribute("allMembership", membershipDao.searchMembership(searchCriteria, searchValue));
                response.sendRedirect("./secureAdmin/membership/membershipMenu.jsp");
                return;

            }
            session.removeAttribute("allMembership");
            session.removeAttribute("membership");

            // session.setAttribute("action", action);
            session.setAttribute("membership", membership);
            session.setAttribute("allMembership", membershipDao.allMembership());
            response.sendRedirect("./secureAdmin/membership/membershipMenu.jsp");

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
