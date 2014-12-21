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
public class InvoiceDao {

    @PersistenceContext
    EntityManager em;

    public InvoiceDao(EntityManager em) {
        this.em = em;
    }

    public void addInvoice(Invoice invoice) {
        em.persist(invoice);
    }

    public Invoice getInvoice(int id) {
        return em.find(Invoice.class, id);
    }

    public void deleteInvoice(int id) {
        em.remove(getInvoice(id));
    }

    public List<Invoice> allInvoice() {
        return em.createNamedQuery("Invoice.findAll").getResultList();
    }

    public int autoId() {
        int id = 0;
        List<Invoice> memberList = allInvoice();
        for (Invoice m : memberList) {
            if (m.getInvoiceNo() > id) {
                id = m.getInvoiceNo();
            }
        }
        id++;
        return id;
    }
}
