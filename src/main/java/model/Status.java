package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Status")
public class Status implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    @Type(type = "long")
    private long id;

    @Column(name = "cart_id", nullable = false)
    private String idCart;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "buy_date")
    @Type(type = "date")
    private Date buyDate;

    @Column(name = "bill")
    private String billLink;

    public Status() {
        super();
    }

    public Status(long id, String idCart, String approvalStatus, Date buyDate, String billLink) {
        super();
        this.id = id;
        this.idCart = idCart;
        this.approvalStatus = approvalStatus;
        this.buyDate = buyDate;
        this.billLink = billLink;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdCart() {
        return this.idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getApprovalStatus() {
        return this.approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Date getBuyDate() {
        return this.buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getBillLink() {
        return this.billLink;
    }

    public void setBillLink(String billLink) {
        this.billLink = billLink;
    }
}