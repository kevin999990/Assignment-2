/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kevin
 */
@Entity
@Table(name = "INVOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceNo", query = "SELECT i FROM Invoice i WHERE i.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "Invoice.findByInvoiceDate", query = "SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "INVOICE_NO")
    private Integer invoiceNo;
    @Size(max = 10)
    @Column(name = "INVOICE_DATE")
    private String invoiceDate;
    @JoinColumn(name = "MEMBER_ID", referencedColumnName = "MEMBER_ID")
    @ManyToOne(optional = false)
    private Member memberId;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "invoiceNo")
    private List<Purchase> purchaseList;

    public Invoice() {
    }

    public Invoice(Integer invoiceNo, String invoiceDate, Member memberId, List<Purchase> purchaseList) {
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.memberId = memberId;
        this.purchaseList = purchaseList;
    }

    public Invoice(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Integer getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Integer invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Member getMemberId() {
        return memberId;
    }

    public void setMemberId(Member memberId) {
        this.memberId = memberId;
    }

    @XmlTransient
    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceNo != null ? invoiceNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.invoiceNo == null && other.invoiceNo != null) || (this.invoiceNo != null && !this.invoiceNo.equals(other.invoiceNo))) {
            return false;
        }
        return true;
    }

    public double totalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < getPurchaseList().size(); i++) {
            totalPrice += getPurchaseList().get(i).getProductCode().getProductPrice() * getPurchaseList().get(i).getPurchaseQuantity();
        }
        return totalPrice;
    }

    public double discountPrice() {
        double discountPrice = 0;
        double discountRate = (1 - memberId.getMemberType().getMembershipDiscountRate());
        discountPrice = this.totalPrice() * discountRate;

        return discountPrice;
    }

    @Override
    public String toString() {
        return "model.Invoice[ invoiceNo=" + invoiceNo + " ]";
    }

}
