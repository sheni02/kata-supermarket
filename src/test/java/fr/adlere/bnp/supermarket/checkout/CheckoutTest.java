package fr.adlere.bnp.supermarket.checkout;

import fr.adlere.bnp.supermarket.checkout.basket.Basket;
import fr.adlere.bnp.supermarket.checkout.store.Store;
import fr.adlere.bnp.supermarket.checkout.util.PrintUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static fr.adlere.bnp.supermarket.checkout.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for the Store.
 */

public class CheckoutTest {


	@DisplayName("Bulk checkout test")
	@Test
	public void testBulkCheckout() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		PrintUtils.printHeader();
		basket.scan(ORANGE);
		basket.scan(ORANGE);
		basket.scan(APPLE);
		basket.scan(ORANGE);
		basket.scan(APPLE);
		basket.scan(APPLE);
		basket.scan(WATERMELON);
		basket.scan(WATERMELON);
		basket.scan(WATERMELON);
		basket.scan(WATERMELON);
		basket.scan(WATERMELON);
		basket.scan(APPLE);
		double total = basket.total();
		PrintUtils.printFooter(total);
		assertEquals(5.10, total);
	}
	
	@DisplayName("Checkout test with quantities")
	@Test
	public void testCheckoutWithQuantities() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		PrintUtils.printHeader();
		basket.scan(ORANGE, 2);
		basket.scan(ORANGE, 1);
		basket.scan(APPLE, 4);
		basket.scan(WATERMELON, 5);
		double total = basket.total();
		PrintUtils.printFooter(total);
		assertEquals(5.10, total);
	}
}
