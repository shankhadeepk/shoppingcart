package com.shoppingcartdemo.shoppingcart.model;

import com.shoppingcartdemo.shoppingcart.exceptions.ShoppingCartException;

import java.util.Objects;
/*
* Item determines individual item added and removed from ShoppingCart
*
* */
public class Item {

    private String type;
    private String name;
    private long quantity;
    private double priceOfEach;
    private double discount;
    private double tax;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) throws ShoppingCartException {
        if(quantity >= 0){
            this.quantity = quantity;
        }else {
            throw new ShoppingCartException("Quantity cannot be negative");
        }
    }

    public double getPriceOfEach() {
        return priceOfEach;
    }

    public void setPriceOfEach(double priceOfEach) throws ShoppingCartException {
        if(priceOfEach >= 0.0) {
            this.priceOfEach = priceOfEach;
        }else {
            throw new ShoppingCartException("Price for each cannot be negative");
        }
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) throws ShoppingCartException {
        if(discount >= 0.0) {
            this.discount = discount;
        }else{
            throw new ShoppingCartException("Discount cannot be negative or cannot be greater than price of Each");
        }
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) throws ShoppingCartException {
        if(tax >= 0.0) {
            this.tax = tax;
        }else {
            throw new ShoppingCartException("Tax cannot be negative");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(type, item.type) &&
                Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    @Override
    public String toString() {
        return "Item{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", priceOfEach=" + priceOfEach +
                ", discount=" + discount +
                ", tax=" + tax +
                '}';
    }
}
