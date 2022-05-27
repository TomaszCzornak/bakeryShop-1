package com.slodkacysia.bakeryshop.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total_amount;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Column(nullable = true)
    @OneToMany(mappedBy="cart",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<CartItem> cartItems;

    @OneToOne(cascade = CascadeType.ALL)
    private Purchase purchase;

    public BigDecimal getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(BigDecimal total_amount) {
        this.total_amount = total_amount;
    }


    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addProduct(Product product) {
        CartItem item = this.findItemByID(product.getId());

        if (item == null) {
            item = new CartItem();
            item.setQuantity(BigDecimal.valueOf(1));
            item.setProduct(product);
            this.cartItems.add(item);
        }

    }

    private CartItem findItemByID(Long id) {
        CartItem item = null;
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getId() == id) {
                item = cartItems.get(i);
            }
        }
        return item;
    }
}