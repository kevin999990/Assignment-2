/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kevin
 */
@Entity
@Table(name = "SUPPLIER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findBySupplierId", query = "SELECT s FROM Supplier s WHERE s.supplierId = :supplierId"),
    @NamedQuery(name = "Supplier.findBySupplierName", query = "SELECT s FROM Supplier s WHERE s.supplierName like :supplierName"),
    @NamedQuery(name = "Supplier.findBySupplierAddress", query = "SELECT s FROM Supplier s WHERE s.supplierAddress like :supplierAddress"),
    @NamedQuery(name = "Supplier.findBySupplierTelNo", query = "SELECT s FROM Supplier s WHERE s.supplierTelNo like :supplierTelNo"),
    @NamedQuery(name = "Supplier.findBySupplierEmail", query = "SELECT s FROM Supplier s WHERE s.supplierEmail like :supplierEmail")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SUPPLIER_ID")
    private Integer supplierId;
    @Size(max = 50)
    @Column(name = "SUPPLIER_NAME")
    private String supplierName;
    @Size(max = 110)
    @Column(name = "SUPPLIER_ADDRESS")
    private String supplierAddress;
    @Size(max = 15)
    @Column(name = "SUPPLIER_TEL_NO")
    private String supplierTelNo;
    @Size(max = 50)
    @Column(name = "SUPPLIER_EMAIL")
    private String supplierEmail;
    @JoinTable(name = "SUPPLY_LIST", joinColumns = {
        @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PRODUCT_CODE", referencedColumnName = "PRODUCT_CODE")})
    @ManyToMany
    private List<Product> productList;

    public Supplier() {
    }

    public Supplier(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierTelNo() {
        return supplierTelNo;
    }

    public void setSupplierTelNo(String supplierTelNo) {
        this.supplierTelNo = supplierTelNo;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Supplier[ supplierId=" + supplierId + " ]";
    }

}
