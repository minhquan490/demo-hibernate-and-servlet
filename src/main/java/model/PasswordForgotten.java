package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "PasswordForgotten")
public class PasswordForgotten implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Type(type = "long")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token")
    @Type(type = "text")
    private String token;

    @Column(name = "date_created")
    @Type(type = "date")
    private Date dateCreated;

    @Column(name = "time_created")
    @Type(type = "time")
    private Time timeCreated;

    public PasswordForgotten() {
        super();
    }

    public PasswordForgotten(long id, User user, String token, Date dateCreated, Time timeCreated) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }


    public void setToken(String token) {
        this.token = sha256(token);
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Time getTimeCreated() {
        return this.timeCreated;
    }

    public void setTimeCreated(Time timeCreated) {
        this.timeCreated = timeCreated;
    }

    private String sha256(String token) {
        String sha256 = "";
        if (token.length() < 64 && token.length() > 4) {
            sha256 = DigestUtils.sha256Hex(token);
        } else {
            token = sha256;
        }
        return sha256;
    }
}