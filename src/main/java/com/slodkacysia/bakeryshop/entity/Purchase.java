package com.slodkacysia.bakeryshop.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Cart cart;

    @Temporal(TemporalType.DATE)
    @Column(name = "cart_date")
    private Date date;

    @OneToOne
    private Customer customer;


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
