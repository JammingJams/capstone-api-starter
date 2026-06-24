package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.models.Order;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.OrderService;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/orders")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class OrderController {

    public final OrderService orderService;
    public final UserService userService;
    private final ProfileService profileService;

    public OrderController(OrderService orderService, UserService userService, ProfileService profileService) {
        this.orderService = orderService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @PostMapping
    public ResponseEntity<Order> checkoutOrder(Principal principal) {

        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();
        Profile profile = profileService.getProfileByUserId(userId);
        
        Order order = orderService.create(profile, userId);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
