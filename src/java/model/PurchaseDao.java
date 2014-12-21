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
public class PurchaseDao {

    @PersistenceContext
    EntityManager em;

    public PurchaseDao(EntityManager em) {
        this.em = em;
    }

    public void addPurchase(Purchase invoice) {
        em.persist(invoice);
    }

    public Purchase getPurchase(int id) {
        return em.find(Purchase.class, id);
    }

    public void deletePurchase(int id) {
        em.remove(getPurchase(id));
    }

    public List<Purchase> allPurchase() {
        return em.createNamedQuery("Purchase.findAll").getResultList();
    }

    public int autoId() {
        int id = 0;
        List<Purchase> memberList = allPurchase();
        for (Purchase m : memberList) {
            if (m.getPurchaseId() > id) {
                id = m.getPurchaseId();
            }
        }
        id++;
        return id;
    }
}
