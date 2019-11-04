package fr.adlere.bnp.supermarket.checkout.basket;

import fr.adlere.bnp.supermarket.checkout.exception.ArticleNotFoundException;
import fr.adlere.bnp.supermarket.checkout.store.Article;
import fr.adlere.bnp.supermarket.checkout.store.Store;
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
	public static BasketItem createItem(String articleName, Store store) throws ArticleNotFoundException {
		return createItem(articleName,  1, store);
	}
	
	/**
	 * Create and initialize a BasketItem object
	 * @param articleName a String
	 * @param quantity an int
	 * @param store Store object
	 * @return an instance of a BasketItem
	 * @throws ArticleNotFoundException if the article does'nt exist in the store
	 */
	public static BasketItem createItem(String articleName, int quantity, Store store) throws ArticleNotFoundException {
		BasketItem basketItem = new BasketItem();
		basketItem.setArticle(store.getArticle(articleName));
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
