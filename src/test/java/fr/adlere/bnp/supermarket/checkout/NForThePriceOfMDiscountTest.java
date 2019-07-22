package fr.adlere.bnp.supermarket.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NForThePriceOfMDiscountTest {

	@DisplayName("Checkout test : Create a Discount instance")
	@Test
	public void testCreateDiscount() {
				
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(3, 2);
		assertNotNull(discount);
		
		IllegalArgumentException nEqualToZeroException = assertThrows(IllegalArgumentException.class, () -> NForThePriceOfMDiscount.createDiscount(0, 0));
		assertTrue(nEqualToZeroException.getMessage().contains("must be greater than 0"));
		
		IllegalArgumentException nlowerThanMException = assertThrows(IllegalArgumentException.class, () -> NForThePriceOfMDiscount.createDiscount(1, 2));
		assertTrue(nlowerThanMException.getMessage().contains("must be lower or equal to the argument m"));
		
	}
	
	@DisplayName("Checkout test : Calculate discounted quantity")
	@Test
	public void testCalculateDiscountedQuantity() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(3, 2);
		assertNotNull(discount);

		PrintUtils.printHeader();
		basket.scan("Orange", 3);
		basket.scan("Apple", 4);
		basket.scan("Watermelon", 6);
		basket.getBasketItems().entrySet().stream().forEach(e -> {
			switch (e.getKey()) {
				case "Orange": {
					assertEquals(1, discount.calculateDiscountedQuantity(e.getValue()));
					break;
				}
				case "Apple": {
					assertEquals(1, discount.calculateDiscountedQuantity(e.getValue()));
					break;
				}
				case "Watermelon": {
					assertEquals(2, discount.calculateDiscountedQuantity(e.getValue()));
					break;
				}
			}
		});
	}
	
	@DisplayName("Checkout test : Calculate Discount")
	@Test
	public void testCalculateDiscount() {
		Store store = Store.createStore();
		assertNotNull(store);
		
		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);
		
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(3, 2);
		assertNotNull(discount);

		PrintUtils.printHeader();
		basket.scan("Orange", 3);
		basket.scan("Apple", 4);
		basket.scan("Watermelon", 6);
		basket.getBasketItems().entrySet().stream().forEach(e -> {
			switch (e.getKey()) {
				case "Orange": {
					assertEquals(0.5, discount.calculateDiscount(e.getValue()));
					break;
				}
				case "Apple": {
					assertEquals(0.2, discount.calculateDiscount(e.getValue()));
					break;
				}
				case "Watermelon": {
					assertEquals(1.6, discount.calculateDiscount(e.getValue()));
					break;
				}
			}
		});
	}
}
