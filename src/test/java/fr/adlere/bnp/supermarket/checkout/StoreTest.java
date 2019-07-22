package fr.adlere.bnp.supermarket.checkout;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the Store.
 */

public class StoreTest {

	
	@DisplayName("Bulk checkout test")
	@Test
	public void testBulkCheckout() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		PrintUtils.printHeader();
		basket.scan("Orange");
		basket.scan("Orange");
		basket.scan("Apple");
		basket.scan("Orange");
		basket.scan("Apple");
		basket.scan("Apple");
		basket.scan("Watermelon");
		basket.scan("Watermelon");
		basket.scan("Watermelon");
		basket.scan("Watermelon");
		basket.scan("Watermelon");
		basket.scan("Apple");
		double total = basket.total();
		assertEquals(5.10, total);
		PrintUtils.printFooter(total);
	}
	
	@DisplayName("Checkout test with quantities")
	@Test
	public void testCheckoutWithQuantities() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		PrintUtils.printHeader();
		basket.scan("Orange", 2);
		basket.scan("Orange", 1);
		basket.scan("Apple", 4);
		basket.scan("Watermelon", 5);
		double total = basket.total();
		assertEquals(5.10, total);
		PrintUtils.printFooter(total);
	}
	
	@DisplayName("Checkout test : Article found and not found")
	@Test
	public void testCheckoutArticlefoundAndNotFound() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		assertDoesNotThrow(() -> store.getArticle("Orange"));
		assertThrows(NoSuchElementException.class, () -> store.getArticle("Ananas"));
		
	}
	
	
	@DisplayName("Checkout test : No Discouted applying")
	@Test
	public void testCheckoutWithoutDiscountedItems() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		PrintUtils.printHeader();
		basket.scan("Orange", 4);
		basket.scan("Apple", 1);
		basket.scan("Watermelon", 2);
		double total = basket.total();
		assertEquals(3.8, total);
		PrintUtils.printFooter(total);
	}
}
