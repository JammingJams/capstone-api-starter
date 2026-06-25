package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class ShoppingCartController
{

    private ShoppingCartService shoppingCartService;
    private UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ShoppingCart> getCart(Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        if (shoppingCartService.getByUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(shoppingCartService.getByUserId(userId));
    }

    @PostMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> addCartItemByProductId(@PathVariable int productId, Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        if (shoppingCartService.getByUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingCartService.create(userId, productId));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ShoppingCart> updateCartItem(@PathVariable int productId, Principal principal, @RequestBody CartItem cartItem)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        if (shoppingCartService.getByUserId(userId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(shoppingCartService.update(userId, productId, cartItem));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts(Principal principal) {

        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        if (shoppingCartService.deleteAllProducts(userId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
