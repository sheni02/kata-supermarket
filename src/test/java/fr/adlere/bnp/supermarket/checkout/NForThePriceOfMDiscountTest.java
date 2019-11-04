package fr.adlere.bnp.supermarket.checkout;

import static fr.adlere.bnp.supermarket.checkout.util.Constants.APPLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.adlere.bnp.supermarket.checkout.basket.Basket;
import fr.adlere.bnp.supermarket.checkout.discount.NForThePriceOfMDiscount;
import fr.adlere.bnp.supermarket.checkout.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NForThePriceOfMDiscountTest {

	private Store store;
	private Basket basket;

	@BeforeEach
	void setUp() {
		store = Store.createStore();
		assertNotNull(store);

		basket = Basket.createBasket(store);
		assertNotNull(basket);
	}

	@Test
	public void testCreateDiscount_When_N_Is_3_And_M_is_2_ThenReturnNonNullDiscountObject() {
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(3, 2);
		assertNotNull(discount);
	}
	@Test
	public void testCreateDiscount_When_N_IsLowerThan_M_ThenThrowIllegalArgumentException() {
		IllegalArgumentException nLowerThanMException = assertThrows(IllegalArgumentException.class, () -> NForThePriceOfMDiscount.createDiscount(1, 2));
		assertTrue(nLowerThanMException.getMessage().contains("must be lower or equal to the argument m"));
	}

	@Test
	public void testCreateDiscount_When_N_EqualToZero_ThrowIllegalArgumentException() {
		IllegalArgumentException nEqualToZeroException = assertThrows(IllegalArgumentException.class, () -> NForThePriceOfMDiscount.createDiscount(0, 0));
		assertTrue(nEqualToZeroException.getMessage().contains("must be greater than 0"));
	}


	@Test
	public void testCalculateDiscount_WhenItemIsAppleAndQuantityIs_1_thenDiscountIs_0() {
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(3, 2);
		basket.scan(APPLE, 1);
		assertEquals(0.0, discount.calculateDiscount(basket.getBasketItems().get(APPLE)));
	}

	@Test
	public void testCalculateDiscount_WhenItemIsAppleAndQuantityIs_3_thenDiscountIs_0_4() {
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(3, 2);
		basket.scan(APPLE, 3);
		assertEquals(0.2, discount.calculateDiscount(basket.getBasketItems().get(APPLE)));
	}
	
	@Test
	public void testCalculateDiscountedQuantity_WhenItemIsAppleAndQuantityIs_1_thenDiscountedQuantityIs_0() {
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(2, 1);
		basket.scan(APPLE, 1);
		assertEquals(0, discount.calculateDiscountedQuantity(basket.getBasketItems().get(APPLE)));
	}

	@Test
	public void testCalculateDiscountedQuantity_WhenItemIsAppleAndQuantityIs_2_thenDiscountedQuantityIs_1() {
		NForThePriceOfMDiscount discount = NForThePriceOfMDiscount.createDiscount(2, 1);
		basket.scan(APPLE, 2);
		assertEquals(1, discount.calculateDiscountedQuantity(basket.getBasketItems().get(APPLE)));
	}
}
