package com.slodkacysia.bakeryshop.controllers;

import com.slodkacysia.bakeryshop.entity.*;
import com.slodkacysia.bakeryshop.repository.*;
import com.slodkacysia.bakeryshop.service.CartDao;
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
    private final CartDao cartDao;
    private final PurchaseRepository purchaseRepository;

    public CartItemController(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, CartItemRepository cartItemRepository, CartDao cartDao, PurchaseRepository purchaseRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartDao = cartDao;

        this.purchaseRepository = purchaseRepository;
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

    @RequestMapping("/products_view")
    public String shopView(Model model, @AuthenticationPrincipal User activeUser) {
        List<Product> productList = productRepository.findAllBy();
        model.addAttribute("offer", productList);
        User user = userRepository.findUserByEmail(activeUser.getEmail());
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotal_amount(BigDecimal.ZERO);
        user.setCart(cart);
        cartRepository.save(cart);
        return "productsView";
    }

    @GetMapping("/add/{productId}")
    public String addItem(Model model, @PathVariable("productId") Long productId, @AuthenticationPrincipal User activeCustomer) {

        com.slodkacysia.bakeryshop.entity.User user = userRepository.findUserByEmail(activeCustomer.getEmail());
        Cart cart = user.getCart();
        Product product = productRepository.findProductById(productId);
//        cartDao.addProduct(product);

        CartItem cartItem = new CartItem();

        cartItem.setProduct(product);
        cartItem.setQuantity(BigDecimal.valueOf(1));
        BigDecimal total_price = product.getPrice().multiply(cartItem.getQuantity());
        cartItem.setTotal_price(total_price);
        cart.setTotal_amount(cartItem.getTotal_price());
        cartItem.setCart(cart);
        cartItem.setPrice(product.getPrice());
        cartItem.setStatus(0);
        cartItemRepository.save(cartItem);
        model.addAttribute("addedItems", cartItem);


        return "cartAdded";
    }

    @RequestMapping(value = "/remove/{productId}")
    public String removeItem(@PathVariable Long productId) {
        CartItem cartItem = cartItemRepository.findCartItemById(productId);
        System.out.println("testowanie");
        cartItemRepository.deleteCartItemById(cartItem.getId());

        return "redirect:/user/customer/cart/"+ cartItem.getCart().getId();
    }


//REMOVE CART/CLEAR CART

    @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void clearCart(@PathVariable(value = "cartId") int cartId) {

        Cart cart = cartRepository.getCartById(cartId);
        cartItemRepository.deleteAllByCart(cart);

    }

    @GetMapping("/add/quantity/{productId}")
    public String addQuantity(@PathVariable Long productId) {
        CartItem cartItem = cartItemRepository.findCartItemByProductId(productId);
        cartItem.setQuantity(cartItem.getQuantity().add(BigDecimal.valueOf(1)));
        cartItemRepository.save(cartItem);
        Long cartID = cartItem.getCart().getId();
        return "redirect: /rest/cart/" + cartID;
    }


}