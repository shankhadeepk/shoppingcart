package com.shoppingcartdemo.shoppingcart.model;

import com.shoppingcartdemo.shoppingcart.exceptions.ShoppingCartException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
* The ShoppingCart takes care of the items added removed or cleared from the cart
*
*
* */
@Component
public class ShoppingCart {

    @Resource
    public HashMap<String,Long> stock;

    //Set is used so no one type of item added multiple times
    private Set<Item> items;
    private double discountOnCart;
    private double taxOnCart;

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addItemsToCart(Item item) throws ShoppingCartException {
        if(item!=null) {
            if (stock.get(item.getName()) > item.getQuantity()) {
                if (this.items == null) {
                    this.items = new HashSet<>();
                    this.items.add(item);
                    stock.put(item.getName(),(stock.get(item.getName()) - item.getQuantity()));
                } else {
                    stock.put(item.getName(),(stock.get(item.getName()) - item.getQuantity()));
                    this.items.add(item);
                }
            } else {
                throw new ShoppingCartException("Stock is not present");
            }
        }else {
            throw new ShoppingCartException("Item data does not exists");
        }
    }

    public void removeItemsFromCart(Item item) throws ShoppingCartException {
        if(item != null) {
            if (this.items == null) {
                throw new ShoppingCartException("The cart is empty");
            } else {
                stock.put(item.getName(),(stock.get(item.getName()) + item.getQuantity()));
                this.items.remove(item);
            }
        }else {
            throw new ShoppingCartException("The item is empty");
        }
    }

    public double getDiscountOnCart() {
        return discountOnCart;
    }

    public void setDiscountOnCart(double discountOnCart) throws ShoppingCartException {
        if(discountOnCart>=0.0) {
            this.discountOnCart = discountOnCart;
        }else {
            throw new ShoppingCartException("The discount on cart cannot be negative");
        }
    }

    public double getTaxOnCart() {
        return taxOnCart;
    }

    public void setTaxOnCart(double taxOnCart) throws ShoppingCartException {
        if(taxOnCart >= 0.0) {
            this.taxOnCart = taxOnCart;
        }else {
            throw new ShoppingCartException("Tax on cart cannot be negative");
        }
    }

    public void addItemsToStock(String itemName,Long quantity){
        stock.put(itemName,quantity);
    }
}
