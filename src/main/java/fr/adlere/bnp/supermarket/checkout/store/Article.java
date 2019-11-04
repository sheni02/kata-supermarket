package fr.adlere.bnp.supermarket.checkout.store;

import fr.adlere.bnp.supermarket.checkout.discount.NForThePriceOfMDiscount;

/**
 * Article class representing a store product.
 * @author saber
 *
 */
public class Article {

	private String name;
	// The discount that can be applied to articles objects. Null if the article has no discount.
	private NForThePriceOfMDiscount discount;
	private double price;	

	public static Article createArticle(String name, double price) {
		return createArticle(name, price, null);
	}
	
	public static Article createArticle(String name, double price, NForThePriceOfMDiscount discount) {
		Article article = new Article();
		article.setName(name);
		article.setPrice(price);
		article.setDiscount(discount);
		
		return article;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public NForThePriceOfMDiscount getDiscount() {
		return discount;
	}
	
	public void setDiscount(NForThePriceOfMDiscount discount) {
		this.discount = discount;
	}
}
