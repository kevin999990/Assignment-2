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
public class SupplierDao {

    @PersistenceContext
    EntityManager em;

    public SupplierDao(EntityManager em) {
        this.em = em;
    }

    public void addSupplier(Supplier s) {
        em.persist(s);
    }

    public Supplier getSupplier(int id) {
        return em.find(Supplier.class, id);
    }

    public void deleteSupplier(int id) {
        em.remove(getSupplier(id));
    }

    public void updateSupplier(Supplier s) {
        em.merge(s);
    }

    public List<Supplier> searchSupplier(String searchCriteria, String searchValue) {

        searchValue = '%' + (String) searchValue + '%';
        if (searchCriteria.equalsIgnoreCase("supplierAddress")) {
            return em.createNamedQuery("Supplier.findBySupplierAddress").setParameter("supplierAddress", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("supplierEmail")) {
            return em.createNamedQuery("Supplier.findBySupplierEmail").setParameter("supplierEmail", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("supplierName")) {
            return em.createNamedQuery("Supplier.findBySupplierName").setParameter("supplierName", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("supplierTelNo")) {
            return em.createNamedQuery("Supplier.findBySupplierTelNo").setParameter("supplierTelNo", searchValue).getResultList();
        } else {
            return allSupplier();
        }
    }

    public List<Supplier> allSupplier() {
        return em.createNamedQuery("Supplier.findAll").getResultList();
    }

    public int autoId() {
        int id = 0;
        List<Supplier> supplierList = allSupplier();
        for (Supplier s : supplierList) {
            if (s.getSupplierId() > id) {
                id = s.getSupplierId();
            }
        }
        id++;
        return id;
    }
}
