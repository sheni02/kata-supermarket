package fr.adlere.bnp.supermarket.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasketTest {

	@DisplayName("Basket test : Calculate price before discount")
	@Test
	public void testCalculatePriceBeforeDiscount() {
		Store store = Store.createStore();
		assertNotNull(store);

		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);

		PrintUtils.printHeader();
		basket.scan("Orange", 3);
		basket.scan("Apple", 4);
		basket.scan("Watermelon", 5);

		basket.getBasketItems().entrySet().stream().forEach(e -> {
			switch (e.getKey()) {
			case "Orange": {
				assertEquals(1.5, basket.calculatePriceBeforeDiscount(e.getValue()));
				break;
			}
			case "Apple": {
				assertEquals(0.8, basket.calculatePriceBeforeDiscount(e.getValue()));
				break;
			}
			case "Watermelon": {
				assertEquals(4, basket.calculatePriceBeforeDiscount(e.getValue()));
				break;
			}
			}
		});

		double total = basket.total();
		assertEquals(5.10, total);
	}

	@DisplayName("Basket test : Price per item")
	@Test
	public void testCalculateItemPrice() {
		Store store = Store.createStore();
		assertNotNull(store);

		Basket basket = Basket.createBasket(store);
		assertNotNull(basket);

		PrintUtils.printHeader();
		basket.scan("Orange", 3);
		basket.scan("Apple", 4);
		basket.scan("Watermelon", 5);
		basket.getBasketItems().entrySet().stream().forEach(e -> {
			switch (e.getKey()) {
			case "Orange": {
				assertEquals(1.5, basket.calculateItemTotal(e.getValue()));
				break;
			}
			case "Apple": {
				assertEquals(0.4, basket.calculateItemTotal(e.getValue()));
				break;
			}
			case "Watermelon": {
				assertEquals(3.2, basket.calculateItemTotal(e.getValue()));
				break;
			}
			}
		});

		double total = basket.total();
		assertEquals(5.10, total);
	}
}
