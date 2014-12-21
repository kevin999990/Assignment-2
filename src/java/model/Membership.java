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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "MEMBERSHIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membership.findAll", query = "SELECT m FROM Membership m"),
    @NamedQuery(name = "Membership.findByMembershipType", query = "SELECT m FROM Membership m WHERE m.membershipType like :membershipType"),
    @NamedQuery(name = "Membership.findByMembershipFee", query = "SELECT m FROM Membership m WHERE m.membershipFee IN :membershipFee"),
    @NamedQuery(name = "Membership.findByMembershipDiscountRate", query = "SELECT m FROM Membership m WHERE m.membershipDiscountRate IN :membershipDiscountRate"),
    @NamedQuery(name = "Membership.findByMembershipDescribe", query = "SELECT m FROM Membership m WHERE m.membershipDescribe like :membershipDescribe")})
public class Membership implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MEMBERSHIP_TYPE")
    private String membershipType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MEMBERSHIP_FEE")
    private Double membershipFee;
    @Column(name = "MEMBERSHIP_DISCOUNT_RATE")
    private Double membershipDiscountRate;
    @Size(max = 10)
    @Column(name = "MEMBERSHIP_DESCRIBE")
    private String membershipDescribe;
    @OneToMany(mappedBy = "memberType")
    private List<Member> memberList;

    public Membership() {
    }

    public Membership(String membershipType) {
        this.membershipType = membershipType;
    }

    public Membership(String membershipType, Double membershipFee, Double membershipDiscountRate) {
        this.membershipType = membershipType;
        this.membershipFee = membershipFee;
        this.membershipDiscountRate = membershipDiscountRate;
    }

    public Membership(String membershipType, Double membershipFee, Double membershipDiscountRate, String membershipDescribe) {
        this.membershipType = membershipType;
        this.membershipFee = membershipFee;
        this.membershipDiscountRate = membershipDiscountRate;
        this.membershipDescribe = membershipDescribe;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Double getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(Double membershipFee) {
        this.membershipFee = membershipFee;
    }

    public Double getMembershipDiscountRate() {
        return membershipDiscountRate;
    }

    public void setMembershipDiscountRate(Double membershipDiscountRate) {
        this.membershipDiscountRate = membershipDiscountRate;
    }

    public String getMembershipDescribe() {
        return membershipDescribe;
    }

    public void setMembershipDescribe(String membershipDescribe) {
        this.membershipDescribe = membershipDescribe;
    }

    @XmlTransient
    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> member1List) {
        this.memberList = member1List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (membershipType != null ? membershipType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membership)) {
            return false;
        }
        Membership other = (Membership) object;
        if ((this.membershipType == null && other.membershipType != null) || (this.membershipType != null && !this.membershipType.equals(other.membershipType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Membership{" + "membershipDescribe=" + membershipDescribe + ", membershipType=" + membershipType + ", membershipFee=" + membershipFee + ", membershipDiscountRate=" + membershipDiscountRate + ", memberList=" + memberList + '}';
    }

  

}
