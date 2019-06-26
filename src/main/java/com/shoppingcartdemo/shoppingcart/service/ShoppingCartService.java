package com.shoppingcartdemo.shoppingcart.service;

import com.shoppingcartdemo.shoppingcart.exceptions.ShoppingCartException;
import com.shoppingcartdemo.shoppingcart.model.Item;

import java.util.Set;

public interface ShoppingCartService {

    public Set<Item> addItemsToCart(Item item) throws ShoppingCartException;
    public Set<Item> removeItemsFromCart(Item item) throws ShoppingCartException;
    public Double calculateCostOfShoppingCart();
    public void updateShoppingCartDiscount(double discount) throws ShoppingCartException;
    public void updateShoppingCartTax(double tax) throws ShoppingCartException;
    public void clearCart();
}
