package fr.adlere.bnp.supermarket.checkout;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * The store class contains the articles list.
 * 
 * @author saber
 *
 */
public class Store {

	private Map<String, Article> articles;

	/**
	 * Create a store instance and initialize the articles list.
	 * 
	 * @return a store instance 
	 */
	public static Store createStore() {
		Store store = new Store();
		store.articles = Store.createArticles();		
		return store;
	}
	
	/**
	 * Initialize the articles list of the store.
	 * @return return the initialized articles list
	 */
	private static Map<String, Article> createArticles() {
		Map<String, Article> arts = new HashMap<>();
		
		Article apple = Article.createArticle("Apple", 0.20, NForThePriceOfMDiscount.createDiscount(2, 1));
		Article orange = Article.createArticle("Orange", 0.50);
		Article watermelon =Article.createArticle("Watermelon", 0.80, NForThePriceOfMDiscount.createDiscount(3, 2));
		
		arts.put(apple.getName(), apple);
		arts.put(orange.getName(), orange);
		arts.put(watermelon.getName(), watermelon);
		
		return arts;
	}

	/**
	 * Look an return the article object in the list of the given articleName
	 * @param articleName
	 * @return return the article object corresponding to the articleName in
	 *         parameter or NoSuchElementException is thrown if the article doesn't exist in the store
	 */
	public Article getArticle(String articleName)  {
		Article article = articles.get(articleName);
		if (article != null) {
			return article;
		}

		throw new NoSuchElementException("No article found with the name '" + articleName + "' in the Store");
	}	
	
}
