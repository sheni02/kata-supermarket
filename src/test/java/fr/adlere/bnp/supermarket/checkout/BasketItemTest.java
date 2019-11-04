package fr.adlere.bnp.supermarket.checkout;

import fr.adlere.bnp.supermarket.checkout.basket.BasketItem;
import fr.adlere.bnp.supermarket.checkout.exception.ArticleNotFoundException;
import fr.adlere.bnp.supermarket.checkout.store.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static fr.adlere.bnp.supermarket.checkout.util.Constants.APPLE;
import static org.junit.jupiter.api.Assertions.*;

class BasketItemTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = Store.createStore();
        assertNotNull(store);
    }

    @Test
    void testCreateItem_WhenArticleDoNotExistInTheStore_ThrowsArticleNotFoundException() {
        assertThrows(ArticleNotFoundException.class, () -> BasketItem.createItem("Bana", store));
    }

    @Test
    void testCreateItem_WhenArticleExist_ReturnBasketItem() throws ArticleNotFoundException {
        assertNotNull(BasketItem.createItem(APPLE, store));
    }

    @Test
    void testCreateItem_WithoutQuantityParameter_WhenArticleExist_QuantityIs1() throws ArticleNotFoundException {
        assertEquals(1, BasketItem.createItem(APPLE, store).getQuantity());
    }

    @Test
    void testCreateItem_WhenArticleExistAndGivenQuanityIs4_ReturnItemWithQuantityIs4() throws ArticleNotFoundException {
        assertEquals(4, BasketItem.createItem(APPLE, 4, store).getQuantity());
    }

}