package fr.adlere.bnp.supermarket.checkout;

/**
 * A Class representing the discounts that can be applied on articles.
 * Discount of the type n articles for the price of m.
 * @author sheni
 *
 */
public class NForThePriceOfMDiscount {

	private int n;
	private int m;

	/**
	 * Create an instance of the discount n articles for the price of m.
	 * @param n articles bought
	 * @param m articles to be paid
	 * @return an instance of the {@link NForThePriceOfMDiscount}
	 */
	public static NForThePriceOfMDiscount createDiscount(int n, int m) {
		if(n < m) {
			throw new IllegalArgumentException(String.format("The n = '%d' argument must be lower or equal to the argument m = '%d'", n, m));
		}
		
		if(n == 0) {
			throw new IllegalArgumentException(String.format("The m = '%d' must be greater than 0", m));
		}
		
		NForThePriceOfMDiscount discount = new NForThePriceOfMDiscount();
		discount.n = n;
		discount.m = m;
		return discount;
	}

	/**
	 * Calculate the amount the discount for a given {@link BasketItem} instance
	 * @param item {@link BasketItem}
	 * @return the discounted price of the item, 0 if no discount applied.
	 */
	public double calculateDiscount(BasketItem item) {
		double discountPrice = 0;
		int discountQuantity = calculateDiscountedQuantity(item);
		if(discountQuantity >= 1) {
			discountPrice = discountQuantity * item.getArticle().getPrice();
		}
		return discountPrice;
	}
	
	/**
	 * Calculate the quantity after removing the free (Discounted) items  
	 * @param item {@link BasketItem} object
	 * @return the effective quantity after removing the free (Discounted) items  
	 */
	public int calculateDiscountedQuantity(BasketItem item) {
		return item.getQuantity() * (n-m)/ n;
	}
	
	public int getN() {
		return n;
	}
	
	public int getM() {
		return m;
	}
	
}
