package fr.adlere.bnp.supermarket.checkout;

import org.apache.commons.lang3.StringUtils;

public class PrintUtils {

	public static void printHeader() {
		System.out.printf("%-37S %15S %15S %15S%n", "Item", "Quantity", "Unit Price", "Total Price");
		printSeparator();
	}

	public static void printSeparator() {
		System.out.printf("%s %n", StringUtils.leftPad("", 85, "-"));
	}
	
	public static void printNewLine() {
		System.out.printf("%n");
	}

	public static void printFooter(double total) {
		printSeparator();
		System.out.printf("%-68S %15.2f£ %n", "Total", total);
		printSeparator();
		printNewLine();

	}

	public static void printLine(BasketItem item, double pricePerItem) {
		System.out.printf("%-35s %15d %15.2f£ %15.2f£ %n", item.getArticle().getName(), item.getQuantity(),
				item.getArticle().getPrice(), pricePerItem);
	}

	public static void printLineDiscount(BasketItem item) {
		NForThePriceOfMDiscount discount = item.getArticle().getDiscount();
		if (discount != null) {
			int discountedQuantity = discount.calculateDiscountedQuantity(item);
			double discountedPrice = discount.calculateDiscount(item);			

			if (discountedQuantity > 0) {
				System.out.printf("%35S  %14d %15s %16.2f£ %n",
						String.format("Discount %d for the price of %d", item.getArticle().getDiscount().getN(),
								item.getArticle().getDiscount().getM()),
						-discountedQuantity,"", -discountedPrice);
			}
		}
	}
}
