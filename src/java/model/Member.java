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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MEMBER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Member.findAll", query = "SELECT m FROM Member m"),
    @NamedQuery(name = "Member.findByMemberId", query = "SELECT m FROM Member m WHERE m.memberId = :memberId"),
    @NamedQuery(name = "Member.findByMemberName", query = "SELECT m FROM Member m WHERE m.memberName like :memberName"),
    @NamedQuery(name = "Member.findByMemberIc", query = "SELECT m FROM Member m WHERE m.memberIc like :memberIc"),
    @NamedQuery(name = "Member.findByMemberRegDate", query = "SELECT m FROM Member m WHERE m.memberRegDate like :memberRegDate"),
    @NamedQuery(name = "Member.findByMemberTelNo", query = "SELECT m FROM Member m WHERE m.memberTelNo like :memberTelNo")})
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEMBER_ID")
    private Integer memberId;
    @Size(max = 50)
    @Column(name = "MEMBER_NAME")
    private String memberName;
    @Size(max = 14)
    @Column(name = "MEMBER_IC")
    private String memberIc;
    @Size(max = 10)
    @Column(name = "MEMBER_REG_DATE")
    private String memberRegDate;
    @Size(max = 11)
    @Column(name = "MEMBER_TEL_NO")
    private String memberTelNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberId")
    private List<Invoice> invoiceList;
    @JoinColumn(name = "MEMBER_TYPE", referencedColumnName = "MEMBERSHIP_TYPE")
    @ManyToOne
    private Membership memberType;

    public Member() {
    }

    public Member(Integer memberId, String memberName, String memberIc, String memberRegDate, String memberTelNo, Membership memberType) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberIc = memberIc;
        this.memberRegDate = memberRegDate;
        this.memberTelNo = memberTelNo;
        this.memberType = memberType;
    }

    public Member(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberIc() {
        return memberIc;
    }

    public void setMemberIc(String memberIc) {
        this.memberIc = memberIc;
    }

    public String getMemberRegDate() {
        return memberRegDate;
    }

    public void setMemberRegDate(String memberRegDate) {
        this.memberRegDate = memberRegDate;
    }

    public String getMemberTelNo() {
        return memberTelNo;
    }

    public void setMemberTelNo(String memberTelNo) {
        this.memberTelNo = memberTelNo;
    }

    @XmlTransient
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public Membership getMemberType() {
        return memberType;
    }

    public void setMemberType(Membership memberType) {
        this.memberType = memberType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberId != null ? memberId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member)) {
            return false;
        }
        Member other = (Member) object;
        if ((this.memberId == null && other.memberId != null) || (this.memberId != null && !this.memberId.equals(other.memberId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Member[ memberId=" + memberId + " ]";
    }

}
