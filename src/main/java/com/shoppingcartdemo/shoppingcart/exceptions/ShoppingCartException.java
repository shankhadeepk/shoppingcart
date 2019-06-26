package com.shoppingcartdemo.shoppingcart.exceptions;

/*
* User Defined exception specific to shopping cart application
*
*
* */
public class ShoppingCartException  extends Exception{

    public ShoppingCartException(String message){
        super(message);
        System.out.println(message);
    }
}
