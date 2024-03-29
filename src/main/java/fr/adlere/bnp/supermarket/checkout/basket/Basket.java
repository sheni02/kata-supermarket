package fr.adlere.bnp.supermarket.checkout.basket;

import fr.adlere.bnp.supermarket.checkout.exception.ArticleNotFoundException;
import fr.adlere.bnp.supermarket.checkout.store.Store;
import fr.adlere.bnp.supermarket.checkout.discount.NForThePriceOfMDiscount;
import fr.adlere.bnp.supermarket.checkout.util.ItemUtils;
import fr.adlere.bnp.supermarket.checkout.util.PrintUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The Basket class that checkouts the items and calculate their total price
 * 
 * @author saber
 *
 */
public class Basket {
	private Map<String, BasketItem> basketItems;
	private Store store;

	/**
	 * Load the pricing rules from the file and init the BasketCheckout object.
	 * 
	 * @return a BasketCheckout instance.
	 */
	public static Basket createBasket(Store store) {
		Basket basket = new Basket();
		basket.basketItems = new HashMap<>();
		basket.store = store;
		return basket;
	}

	/**
	 * Scan Articles and create the their corresponding items and/or update their
	 * quantities.
	 * One article at the time.
	 * 
	 * @param scannedArticle String name of the article
	 **/
	public void scan(String scannedArticle) {
		this.scan(scannedArticle, 1);
	}

	/**
	 * Scan Articles and create the their corresponding items and/or update their
	 * quantities.
	 * 
	 * @param scannedArticle String name of the scanned article
	 * @param scannedQuantity the quantity of the article
	 **/
	public void scan(String scannedArticle, int scannedQuantity) {
		BasketItem basketItem = basketItems.get(scannedArticle);
		if (basketItem == null) {
			try {
				BasketItem scannedItem = BasketItem.createItem(scannedArticle, scannedQuantity, store);
				basketItems.put(scannedArticle, scannedItem);
			} catch (ArticleNotFoundException e) {
				System.out.println("Sorry ! '" + scannedArticle+ "' doesn't exist in the store !");
			}
		} else {
			int quantity = basketItem.getQuantity();
			basketItem.setQuantity(quantity + scannedQuantity);
		}
	}

	/**
	 * Calculate the items prices and applies the discounts.
	 * 
	 * @return the total price of the scanned items.
	 */
	public double total() {
		basketItems.values().forEach(item -> {
			PrintUtils.printLine((item),calculatePriceBeforeDiscount(item));
			PrintUtils.printLineDiscount(item);
		});
		double totalPrice = basketItems.values()
				.parallelStream()
				.mapToDouble(this::calculateItemTotal)
				.sum();

		return ItemUtils.round(totalPrice, 2);
	}

	/**
	 * Calculate the total price per items and applies the discounts.
	 * 
	 * @param item a BasketItem object
	 * @return total price per item
	 */
	private double calculateItemTotal(BasketItem item) {
		double price = calculatePriceBeforeDiscount(item);
		NForThePriceOfMDiscount discount = item.getArticle().getDiscount();
		if (discount != null) {
			double discountedPrice = discount.calculateDiscount(item);
			double totalPerItem = price - discountedPrice;

			return ItemUtils.round(totalPerItem, 2);
		}
		return ItemUtils.round(price, 2);
	}

	/**
	 * Calculate the price per items before discounts.
	 * 
	 * @param item a BasketItem object
	 * @return total price per item before discounts
	 */
	private double calculatePriceBeforeDiscount(BasketItem item) {
		return ItemUtils.round(item.getQuantity() * item.getArticle().getPrice(), 2);
	}
	
	public Map<String, BasketItem> getBasketItems() {
		return basketItems;
	}

}
