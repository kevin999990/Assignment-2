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
public class ProductDao {

    @PersistenceContext
    EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public void addProduct(Product p) {
        em.persist(p);
    }

    public Product getProduct(String s) {
        return em.find(Product.class, s);
    }

    public void deleteProduct(String s) {
        em.remove(getProduct(s));
    }

    public void updateProduct(Product p) {
        em.merge(p);
    }

    public List<Product> allProduct() {
        return em.createNamedQuery("Product.findAll").getResultList();
    }

    public List<Product> searchProduct(String searchCriteria, String searchValue) {
        searchValue = '%' + searchValue + '%';

        if (searchCriteria.equalsIgnoreCase("productCode")) {
            return em.createNamedQuery("Product.findByProductCode").setParameter("productCode", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("productDesciption")) {
            return em.createNamedQuery("Product.findByProductDesciption").setParameter("productDesciption", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("productName")) {
            return em.createNamedQuery("Product.findByProductName").setParameter("productName", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("productPrice")) {
            return em.createNamedQuery("Product.findByProductPrice").setParameter("productPrice", searchValue).getResultList();
        } else if (searchCriteria.equalsIgnoreCase("productType")) {
            return em.createNamedQuery("Product.findByProductType").setParameter("productType", searchValue).getResultList();
        } else {
            return allProduct();
        }

    }
}
