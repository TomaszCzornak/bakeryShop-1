//package com.slodkacysia.bakeryshop.entity;
//
//import javax.persistence.*;
//
//@Entity
//public class Customer {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    private Long userId;
//
//    private Integer enabled;
//
//    private String username;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Integer getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Integer enabled) {
//        this.enabled = enabled;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    @OneToOne
//    private Cart cart;
//
//    private String email;
//    private String first_name;
//    private String last_name;
//    private String password;
//    private String city;
//    private String street;
//    private String phone;
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    private String post_code;
//
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirst_name() {
//        return first_name;
//    }
//
//    public void setFirst_name(String first_name) {
//        this.first_name = first_name;
//    }
//
//    public String getLast_name() {
//        return last_name;
//    }
//
//    public void setLast_name(String last_name) {
//        this.last_name = last_name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getPost_code() {
//        return post_code;
//    }
//
//    public void setPost_code(String post_code) {
//        this.post_code = post_code;
//    }
//}
