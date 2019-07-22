package fr.adlere.bnp.supermarket.checkout;

import java.util.NoSuchElementException;

/**
 * Checkout article wrapper 
 * @author saber
 *
 */
public class BasketItem {

	private Article article;
	private int quantity;
	
	/**
	 * Create and initialize a BasketItem object
	 * @param articleName a String
	 * @param store Store object
	 * @return an instance of a BasketItem
	 */
	public static BasketItem createItem(String articleName, Store store) throws NoSuchElementException {
		return createItem(articleName,  1, store);
	}
	
	/**
	 * Create and initialize a BasketItem object
	 * @param articleName a String
	 * @param Quanity an int
	 * @param store Store object
	 * @return an instance of a BasketItem
	 */
	public static BasketItem createItem(String articleName, int quantity, Store store) throws NoSuchElementException {
		BasketItem basketItem = new BasketItem();
		basketItem.setArticle(store.getArticle(articleName));;
		basketItem.setQuantity(quantity);
		return basketItem;
	}

	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
}
