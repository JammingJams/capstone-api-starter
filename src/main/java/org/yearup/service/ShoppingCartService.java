package org.yearup.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.exception.ProductNotFoundException;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
@Transactional
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId) {
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

        ShoppingCart cart = new ShoppingCart();

        for (CartItem item : cartItems) {
            Product product = productService.getById(item.getProductId());

            ShoppingCartItem sci = new ShoppingCartItem();
            sci.setProduct(product);
            sci.setQuantity(item.getQuantity());

            cart.add(sci);
        }

        return cart;
    }

    public ShoppingCart create(int userId, int productId) {

        Product product = productService.getById(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found");
        }
        if (product.getStock() <= 0) {
            throw new ProductNotFoundException("Product is out of stock");
        }

        CartItem existing = shoppingCartRepository
                .findByUserIdAndProductId(userId, productId);


        if (existing != null) {
            int quantity = existing.getQuantity() >= product.getStock() ? 0 : 1;
            existing.setQuantity(existing.getQuantity() + quantity);
            shoppingCartRepository.save(existing);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProductId(productId);
            newItem.setQuantity(1);

            shoppingCartRepository.save(newItem);
        }

        return getByUserId(userId);
    }

    public ShoppingCart update(int userId, int productId, CartItem newItem) {

        Product product = productService.getById(productId);

        CartItem existing = shoppingCartRepository
                .findByUserIdAndProductId(userId, productId);

        if (product == null || existing == null) {
            throw new ProductNotFoundException("Product not found");
        }
        System.out.println(newItem.getQuantity());
        System.out.println(productService.getById(productId).getStock());

        if (newItem.getQuantity() <= productService.getById(productId).getStock()) {
            existing.setQuantity(newItem.getQuantity());
        }
        else {existing.setQuantity(productService.getById(productId).getStock());}
        shoppingCartRepository.save(existing);

        return getByUserId(userId);
    }

    public boolean deleteAllProducts(int userId) {
        List<CartItem> items = shoppingCartRepository.findByUserId(userId);

        if(!items.isEmpty()){
            shoppingCartRepository.deleteByUserId(userId);
            return true;
        }
        return false;
    }

}
