package fr.adlere.bnp.supermarket.checkout;

import fr.adlere.bnp.supermarket.checkout.basket.Basket;
import fr.adlere.bnp.supermarket.checkout.store.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static fr.adlere.bnp.supermarket.checkout.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BasketTest {

    private Store store;
    private Basket basket;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        store = Store.createStore();
        assertNotNull(store);

        basket = Basket.createBasket(store);
        assertNotNull(basket);

        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testScan_WhenArticleDoNotExist_PrintErrorMessage() {
        basket.scan("Banana");
        assertEquals("Sorry ! 'Banana' doesn't exist in the store !\r\n", outContent.toString());
    }

    @Test
    public void testScan_WhenArticleExist_TheAddedQuantityShouldBe1() {
        basket.scan(APPLE);
        assertEquals(1, basket.getBasketItems().get(APPLE).getQuantity());
    }

    @Test
    public void testScan_WhenItemExitAndAndQuantityIs4_TheShouldAdd1ToQuantity() {
        basket.scan(APPLE, 4);
        basket.scan(APPLE);
        assertEquals(5, basket.getBasketItems().get(APPLE).getQuantity());
    }

	@Test
	public void testTotal_When1Apple_1Orange_1WaterMelon_ThenPriceIs1_5() {
		basket.scan(APPLE);
		basket.scan(ORANGE);
		basket.scan(WATERMELON);
		assertEquals(1.5, basket.total());
	}

	@Test
	public void testTotal_When2Apple_1Orange_1WaterMelon_ThenPriceIs1_5() {
		basket.scan(APPLE, 2);
		basket.scan(ORANGE);
		basket.scan(WATERMELON);
		assertEquals(1.5, basket.total());
	}

	@Test
	public void testTotal_When4Apple_1Orange_1WaterMelon_ThenPriceIs1_7() {
		basket.scan(APPLE, 4);
		basket.scan(ORANGE);
		basket.scan(WATERMELON);
		assertEquals(1.7, basket.total());
	}

	@Test
	public void testTotal_When2Apple_1Orange_3WaterMelon_ThenPriceIs2_3() {
		basket.scan(APPLE, 2);
		basket.scan(ORANGE);
		basket.scan(WATERMELON, 3);
		assertEquals(2.3, basket.total());
	}
}
