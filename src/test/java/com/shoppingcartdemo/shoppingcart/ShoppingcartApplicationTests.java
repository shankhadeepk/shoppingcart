package com.shoppingcartdemo.shoppingcart;

import com.shoppingcartdemo.shoppingcart.exceptions.ShoppingCartException;
import com.shoppingcartdemo.shoppingcart.model.Item;
import com.shoppingcartdemo.shoppingcart.service.ShoppingCartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingcartApplicationTests {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Before
	public void setUp(){
		shoppingCartService.addItemsToStock("Apple",20L);
		shoppingCartService.addItemsToStock("Pine Apple",20L);
		shoppingCartService.addItemsToStock("Orange",10L);
	}


	@Test
	public void contextLoads() {
	}

	@Test
	public void checkAddItemToCart() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(0.0);
		item.setQuantity(10);
		item.setTax(3.0);

		Set<Item> items=new HashSet<>();
		items.add(item);

		assertEquals(shoppingCartService.addItemsToCart(item),items);
		shoppingCartService.clearCart();
	}
	@Test
	public void checkRemovedItemFromCart() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(0.0);
		item.setQuantity(10);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);

		assertEquals(shoppingCartService.removeItemsFromCart(item),new HashSet<>());
		shoppingCartService.clearCart();
	}
	@Test(expected = ShoppingCartException.class)
	public void checkAddItemToCartWhenDiscountNegative() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(-10.0);
		item.setQuantity(10);
		item.setTax(3.0);
	}
	@Test(expected = ShoppingCartException.class)
	public void checkAddItemToCartWhenQuantityIsNegative() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(10.0);
		item.setQuantity(-10);
		item.setTax(3.0);
	}
	@Test(expected = ShoppingCartException.class)
	public void checkAddItemToCartWhenTaxIsNegative() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(10.0);
		item.setQuantity(10);
		item.setTax(-3.0);
	}
	@Test(expected = ShoppingCartException.class)
	public void checkAddItemToCartWhenPriceForEachIsNegative() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(-15.0);
		item.setDiscount(10.0);
		item.setQuantity(10);
		item.setTax(3.0);
	}
	@Test
	public void checkCalculateTheShoppingCartCost() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(10.0);
		item.setQuantity(10);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);

		item=new Item();
		item.setName("Pine Apple");
		item.setType("Fruit");
		item.setPriceOfEach(5.0);
		item.setDiscount(4.0);
		item.setQuantity(10);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);

		assertEquals(shoppingCartService.calculateCostOfShoppingCart(),192.0);
		shoppingCartService.clearCart();
	}
	@Test
	public void checkCalculateTheShoppingCartCostWithDiscountAndTax() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(10.0);
		item.setQuantity(10);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);

		item=new Item();
		item.setName("Pine Apple");
		item.setType("Fruit");
		item.setPriceOfEach(5.0);
		item.setDiscount(4.0);
		item.setQuantity(10);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);
		shoppingCartService.updateShoppingCartDiscount(4.5);
		shoppingCartService.updateShoppingCartTax(5.5);

		assertEquals(shoppingCartService.calculateCostOfShoppingCart(),193.0);
		shoppingCartService.clearCart();
	}
	@Test(expected = ShoppingCartException.class)
	public void checkCalculateTheShoppingCartCostWithDiscountAndTaxAsNegative() throws ShoppingCartException {

		Item item=new Item();
		item.setName("Apple");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(10.0);
		item.setQuantity(10);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);

		item=new Item();
		item.setName("Pine Apple");
		item.setType("Fruit");
		item.setPriceOfEach(5.0);
		item.setDiscount(4.0);
		item.setQuantity(10);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);
		shoppingCartService.updateShoppingCartDiscount(-4.5);
		shoppingCartService.updateShoppingCartTax(-5.5);

	}
	@Test(expected = ShoppingCartException.class)
	public void checkIfNoItemAddedToCart() throws ShoppingCartException {
		Item item=null;

		shoppingCartService.addItemsToCart(item);
	}
	@Test(expected = ShoppingCartException.class)
	public void checkIfNoItemRemovedFromCart() throws ShoppingCartException {
		Item item=null;

		shoppingCartService.removeItemsFromCart(item);
	}
	@Test(expected = ShoppingCartException.class)
	public void checkIfAddedItemIsNotPresentInStock() throws ShoppingCartException {
		Item item=new Item();
		item.setName("Orange");
		item.setType("Fruit");
		item.setPriceOfEach(15.0);
		item.setDiscount(10.0);
		item.setQuantity(20);
		item.setTax(3.0);

		shoppingCartService.addItemsToCart(item);
	}
}
