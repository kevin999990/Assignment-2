/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kevin
 */
public class MembershipDao {

    @PersistenceContext
    EntityManager em;
    @Resource
    Query query;

    public MembershipDao(EntityManager em) {
        this.em = em;
    }

    public void addMembership(Membership membership) {
        membership.setMembershipType(membership.getMembershipType().toUpperCase());
        if (membership.getMembershipDescribe().isEmpty() || membership.getMembershipDescribe().equalsIgnoreCase("")) {
            membership.setMembershipDescribe("");
        }
        em.persist(membership);
    }

    public Membership getMembership(String membershipType) {
        return em.find(Membership.class, membershipType);
    }

    public Membership getMembershipByDesc(String MembershipDescribe) {
        List<Membership> m = allMembership();
        String id = null;
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMembershipDescribe().equalsIgnoreCase(MembershipDescribe)) {
                id = m.get(i).getMembershipType();
            }
        }
        return getMembership(id);
    }

    public void deleteMembership(String MembershipType) {
        em.remove(getMembership(MembershipType));
    }

    public void deleteMembershipByDesc(String MembershipDescribe) {
        em.remove(getMembershipByDesc(MembershipDescribe));
    }

    public void updateMembership(Membership membership) {
        em.merge(membership);
    }

    public List<Membership> searchMembership(String searchCriteria, Object searchValue) {
        if (searchValue instanceof String) {
            searchValue = '%' + (String) searchValue + '%';
        }
        if (searchCriteria.equalsIgnoreCase("membershipDescribe")) {
            return em.createNamedQuery("Membership.findByMembershipDescribe").setParameter(searchCriteria, searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("membershipDiscountRate")) {
            return em.createNamedQuery("Membership.findByMembershipDiscountRate").setParameter(searchCriteria, searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("membershipFee")) {
            return em.createNamedQuery("Membership.findByMembershipFee").setParameter(searchCriteria, searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("membershipType")) {
            return em.createNamedQuery("Membership.findByMembershipType").setParameter(searchCriteria, searchValue).getResultList();
        } else {
            return allMembership();
        }
    }

    public List<Membership> allMembership() {

        return em.createNamedQuery("Membership.findAll").getResultList();

    }

}
