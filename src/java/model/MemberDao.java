/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kevin
 */
public class MemberDao {

    @PersistenceContext
    EntityManager em;

    public MemberDao(EntityManager em) {
        this.em = em;
    }

    public void addMember(Member m) {
        em.persist(m);
    }

    public void deleteMember(int id) {
        em.remove(getMember(id));
    }

    public Member getMember(int id) {
        return em.find(Member.class, id);
    }

    public List<Member> searchMember(String searchCriteria, String searchValue) {

        searchValue = '%' + (String) searchValue + '%';

        if (searchCriteria.equalsIgnoreCase("memberIc")) {
            return em.createNamedQuery("Member.findByMemberIc").setParameter("memberIc", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("memberId")) {
            return em.createNamedQuery("Member.findByMemberId").setParameter("memberId", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("memberName")) {
            return em.createNamedQuery("Member.findByMemberName").setParameter("memberName", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("memberRegDate")) {
            return em.createNamedQuery("Member.findByMemberRegDate").setParameter("memberRegDate", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("memberTelNo")) {
            return em.createNamedQuery("Member.findByMemberTelNo").setParameter("memberTelNo", searchValue).getResultList();
        } else {
            return allMember();
        }
    }

    public void updateMember(Member m) {
        em.merge(m);
    }

    public List<Member> allMember() {
        return em.createNamedQuery("Member.findAll").getResultList();
    }

    public int autoId() {
        int id = 0;
        List<Member> memberList = allMember();
        for (Member m : memberList) {
            if (m.getMemberId() > id) {
                id = m.getMemberId();
            }

        }
        id++;
        return id;
    }
}
