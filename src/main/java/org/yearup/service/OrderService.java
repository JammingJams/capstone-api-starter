package org.yearup.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.models.*;
import org.yearup.repository.OrderLineItemRepository;
import org.yearup.repository.OrderRepository;
import org.yearup.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderLineItemRepository orderLineItemRepository, ShoppingCartService shoppingCartService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.productRepository = productRepository;
    }

    public Order create(Profile profile, int userId) {

        Order order = new Order();

        order.setAddress(profile.getAddress());
        order.setZip(profile.getZip());
        order.setCity(profile.getCity());
        order.setState(profile.getState());
        order.setDate(LocalDateTime.now());
        order.setShippingAmount(15);
        order.setUserId(userId);

        orderRepository.save(order);

        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        List<ShoppingCartItem> cartItems = new ArrayList<>(cart.getItems().values());

        if (cartItems.isEmpty()) {
            return null;
        }

        //Loops through each cart item and retrieves data to assign to the line item
        for (ShoppingCartItem item : cartItems) {
            OrderLineItem lineItem = new OrderLineItem();
            Product product = item.getProduct();

            lineItem.setDiscount(item.getDiscountPercent());
            lineItem.setSalesPrice(product.getPrice());
            lineItem.setQuantity(item.getQuantity());
            lineItem.setProductId(product.getProductId());
            lineItem.setOrderId(order.getOrderId());

            product.setStock(product.getStock() - lineItem.getQuantity());

            productRepository.save(product);
            orderLineItemRepository.save(lineItem);
        }

        double subtotal = cart.getTotal();
        double taxAmount = subtotal * 0.2;
        double total = subtotal + taxAmount + order.getShippingAmount();

        order.setSubTotal(subtotal);
        order.setTotalPrice(total);

        orderRepository.save(order);
        shoppingCartService.deleteAllProducts(userId);

        return order;
    }
}
