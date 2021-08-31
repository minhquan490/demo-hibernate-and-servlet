package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Type(type = "long")
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    @Type(type = "date")
    private Date birthDate;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @Type(type = "text")
    private String password;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "role_id")
    private int roleId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PasswordForgotten> sPasswordForgottens = new HashSet<>();

    public User() {
        super();
    }

    public User(long id, String fullName, String email, String gender, Date birthDate, String address, String phone, String username, String password, String avatar, int roleId, Set<PasswordForgotten> sPasswordForgottens) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = sha256(password);
        this.avatar = avatar;
        this.roleId = roleId;
        this.sPasswordForgottens = sPasswordForgottens;
    }

    public User(long id, String email, String username, String password, int roleId) {
        super();
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = sha256(password);
        this.roleId = roleId;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = sha256(password);
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<PasswordForgotten> getSPasswordForgottens() {
        return this.sPasswordForgottens;
    }

    public void setSPasswordForgottens(Set<PasswordForgotten> sPasswordForgottens) {
        this.sPasswordForgottens = sPasswordForgottens;
    }

    private String sha256(String password) {
        String sha256 = "";
        if (password.length() < 64 && password.length() > 4) {
            sha256 = DigestUtils.sha256Hex(password);
        } else {
            password = sha256;
        }
        return sha256;
    }
}