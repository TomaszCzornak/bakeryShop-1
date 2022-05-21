package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.*;
import com.slodkacysia.bakeryshop.repository.CartItemRepository;
import com.slodkacysia.bakeryshop.repository.CartRepository;
import com.slodkacysia.bakeryshop.repository.ProductRepository;
import com.slodkacysia.bakeryshop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@PreAuthorize("hasRole('ROLE_USER')")
@Controller
@RequestMapping("/rest/cart/")
public class CartItemController {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartItemController(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @RequestMapping("/{cartId}")
    public String getCartById(Model model, @PathVariable("cartId") Long cartId) {

        model.addAttribute("cartList", cartRepository.getCartById(cartId));
        return "cartAdded";
    }

    @RequestMapping("/products_to_buy")
    public String addToCart(Model model) {
        List<Product> productList = productRepository.findAllBy();
        model.addAttribute("offer", productList);

        return "productListCustomer";
    }

    @GetMapping("/add/{productId}")
    public String addItem(Model model, @PathVariable("productId") Long productId, @AuthenticationPrincipal User activeCustomer) {

        com.slodkacysia.bakeryshop.entity.User user = userRepository.findUserByEmail(activeCustomer.getEmail());
        Cart cart = user.getCart();
        Product product = productRepository.findProductById(productId);
        cart.addProduct(product);
        List<CartItem> cartItems = cart.getCartItems();
        for (int i = 0; i < cartItems.size(); i++) {

            if (product.getId() == cartItems.get(i).getProduct().getId()) {
                CartItem cartItem = cartItems.get(i);
                BigDecimal productPrice = new BigDecimal(String.valueOf(product.getPrice()));
                BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getQuantity()));
                cartItem.setQuantity(cartItem.getQuantity().add(BigDecimal.valueOf(1)));
                BigDecimal total_price = product.getPrice().multiply(cartItem.getQuantity());
                cartItem.setTotal_price(total_price);
                cart.setId(user.getCart().getId());
                cartItem.setCart(cart);
                cartRepository.save(cart);
                cartItemRepository.save(cartItem);

                model.addAttribute("addedItems", cartItem);
                return "cartAdded";
            } else {
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(BigDecimal.valueOf(1));
                BigDecimal total_price = product.getPrice().multiply(cartItem.getQuantity());
                cartItem.setTotal_price(total_price);
                cart.setId(user.getCart().getId());
                cartItem.setCart(cart);
                cartItemRepository.save(cartItem);
                System.out.println("Lista cartItemów " + cartItem.getProduct().getId());
                model.addAttribute("addedItems", cartItem);

            }
        }
        return "cartAdded";
    }

    @RequestMapping(value = "/remove/{productId}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String removeItem(@PathVariable("productId") Long productId) {
        CartItem cartItem = cartItemRepository.findCartItemByProduct_id(productId);

        cartItemRepository.deleteCartItemById(cartItem.getId());

        return "cartAdded";
    }


    //REMOVE CART/CLEAR CART

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void clearCart(@PathVariable(value = "cartId") int cartId) {

        Cart cart = cartRepository.getCartById(cartId);
        cartItemRepository.deleteAllByCart(cart);

    }
    @GetMapping("/add/quantity/{productId}")
    public String addQuantity(@PathVariable Long productId){
        CartItem cartItem = cartItemRepository.findCartItemByProduct_id(productId);
        cartItem.setQuantity(cartItem.getQuantity().add(BigDecimal.valueOf(1)));
        cartItemRepository.save(cartItem);
        Long cartID = cartItem.getCart().getId();
        return  "redirect: /rest/cart/"+ cartID;
    }


}