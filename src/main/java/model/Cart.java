package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {
    
    @Id
    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn(name = "cart_id")
    private User user;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<CartItem> cartItems;

    @Column(name = "buy_date")
    private Date buyDate;

    public Cart(User user, Set<CartItem> cartItems, Date buyDate) {
        super();
        this.user = user;
        this.cartItems = cartItems;
        this.buyDate = buyDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Date getBuyDate() {
        return this.buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}