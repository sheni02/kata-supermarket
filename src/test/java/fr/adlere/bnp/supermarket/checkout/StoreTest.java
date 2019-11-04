package fr.adlere.bnp.supermarket.checkout;

import fr.adlere.bnp.supermarket.checkout.basket.Basket;
import fr.adlere.bnp.supermarket.checkout.exception.ArticleNotFoundException;
import fr.adlere.bnp.supermarket.checkout.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static fr.adlere.bnp.supermarket.checkout.util.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    private Store store;
    private Basket basket;

    @BeforeEach
    void setUp() {
        store = Store.createStore();
        assertNotNull(store);

        Basket basket = Basket.createBasket(store);
        assertNotNull(basket);
    }

    @Test
    void createStore_WhenArticleIsApple_ThenDiscountIsNotNull() throws ArticleNotFoundException {
        assertNotNull(store.getArticle(APPLE).getDiscount());
    }

    @Test
    void createStore_WhenArticleIsWatermelon_ThenDiscountIsNotNull() throws ArticleNotFoundException {
        assertNotNull(store.getArticle(WATERMELON).getDiscount());
    }

    @Test
    void createStore_WhenArticleIsOrange_ThenDiscountIsNull() throws ArticleNotFoundException {
        assertNull(store.getArticle(ORANGE).getDiscount());
    }

    @Test
    void createStore_WhenArticleIsApple_ThenPriceIs0_2() throws ArticleNotFoundException {
        assertEquals(0.2, store.getArticle(APPLE).getPrice());
    }

    @Test
    void createStore_WhenArticleIsOrange_ThenPriceIs0_5() throws ArticleNotFoundException {
        assertEquals(0.5, store.getArticle(ORANGE).getPrice());
    }

    @Test
    void createStore_WhenArticleIsWatermelon_ThenPriceIs0_8() throws ArticleNotFoundException {
        assertEquals(0.8, store.getArticle(WATERMELON).getPrice());
    }

    @Test
    void getArticle_WhenArticleDoNotExist_ThenThrowArticleNotFoundException() {
        assertThrows(ArticleNotFoundException.class, () -> store.getArticle("Banana"));
    }

    @Test
    void getArticle_WhenArticleExist_ThenNoExceptionShouldBeThrown() {
        assertDoesNotThrow(() -> store.getArticle(ORANGE));
        assertDoesNotThrow(() -> store.getArticle(APPLE));
        assertDoesNotThrow(() -> store.getArticle(WATERMELON));
    }
}