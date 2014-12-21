/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Member;
import model.MemberDao;
import model.Membership;
import model.MembershipDao;

/**
 *
 * @author Kevin
 */
public class MemberServlet extends HttpServlet {

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
            MemberDao memberDao = new MemberDao(em);
            MembershipDao membershipDao = new MembershipDao(em);
            String action = request.getParameter("action");

            /* Integer id = Integer.parseInt(request.getParameter("id"));
             String name = request.getParameter("memberName");
             String ic = request.getParameter("memberIc");

             SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/YYYY");
             String tDate = simpleDate.format(new Date());

             String tel = request.getParameter("memberTelNo");
             String mType = request.getParameter("membershipType");

             Membership membership = em.find(Membership.class, mType);*/
            Member member = (Member) session.getAttribute("member");

            if ("Add".equalsIgnoreCase(action)) {
                utx.begin();
                memberDao.addMember(member);
                utx.commit();
            } else if ("Delete".equalsIgnoreCase(action)) {
                utx.begin();
                memberDao.deleteMember(member.getMemberId());
                utx.commit();
            } else if ("Update".equalsIgnoreCase(action)) {
                utx.begin();
                memberDao.updateMember(member);
                utx.commit();
            } else if ("Search".equalsIgnoreCase(action)) {

                String searchCriteria = request.getParameter("searchCriteria");
                String searchValue = request.getParameter("searchValue");

                session.removeAttribute("allMember");
                session.setAttribute("allMember", memberDao.searchMember(searchCriteria, searchValue));
                response.sendRedirect("./secureAdmin/member/memberMenu.jsp");
                return;

            }

            session.removeAttribute("member");
            session.removeAttribute("allMember");
            session.removeAttribute("membershipList");
            session.removeAttribute("autoId");

            session.setAttribute("action", action);
            session.setAttribute("member", member);
            session.setAttribute("allMember", memberDao.allMember());
            session.setAttribute("autoId", memberDao.autoId());
            session.setAttribute("membershipList", membershipDao.allMembership());
            response.sendRedirect("./secureAdmin/member/memberMenu.jsp");
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
