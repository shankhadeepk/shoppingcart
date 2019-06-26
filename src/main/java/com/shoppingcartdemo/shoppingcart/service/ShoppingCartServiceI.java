package com.shoppingcartdemo.shoppingcart.service;

import com.shoppingcartdemo.shoppingcart.exceptions.ShoppingCartException;
import com.shoppingcartdemo.shoppingcart.model.Item;
import com.shoppingcartdemo.shoppingcart.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/*
*
* Shopping Cart service to add Items to cart, delete items from cart, update discount, update tax and calculate price of the shopping Cart
*
*
* */
@Service("shoppingCartService")
public class ShoppingCartServiceI implements ShoppingCartService {

    @Autowired
    private ShoppingCart shoppingCart;

    @Override
    public void updateShoppingCartDiscount(double discount) throws ShoppingCartException {
        shoppingCart.setDiscountOnCart(discount);
    }

    @Override
    public void updateShoppingCartTax(double tax) throws ShoppingCartException {
        shoppingCart.setTaxOnCart(tax);
    }

    @Override
    public void clearCart() {
        shoppingCart.getItems().clear();
    }

    @Override
    public void addItemsToStock(String itemName, Long quantity) {
        shoppingCart.addItemsToStock(itemName,quantity);
    }

    @Override
    public Set<Item> addItemsToCart(Item item) throws ShoppingCartException {
        shoppingCart.addItemsToCart(item);
        System.out.println("Items added");
        System.out.println(shoppingCart.getItems());
        return shoppingCart.getItems();
    }

    @Override
    public Set<Item> removeItemsFromCart(Item item) throws ShoppingCartException {
            shoppingCart.removeItemsFromCart(item);
            System.out.println("Items Removed:"+item);
            System.out.println(shoppingCart.getItems());
            return shoppingCart.getItems();
    }

    @Override
    public Double calculateCostOfShoppingCart() {

        List<Double> shoppingCartPrices=shoppingCart.getItems().stream().map(item ->
                ((item.getPriceOfEach() * item.getQuantity()) + item.getTax()) - item.getDiscount()).collect(Collectors.toList());

        System.out.println("The shopping cart contains:");
        shoppingCartPrices.stream().forEach(System.out::println);

        Double priceOfCart=shoppingCartPrices.stream().collect(Collectors.summingDouble(Double::doubleValue));

        System.out.println("Price of Cart:"+priceOfCart);

        Double finalPriceOfCart= priceOfCart + shoppingCart.getTaxOnCart() - shoppingCart.getDiscountOnCart();

        System.out.println("Price of Cart with Tax and Discount :"+finalPriceOfCart);

        return finalPriceOfCart;

    }
}
