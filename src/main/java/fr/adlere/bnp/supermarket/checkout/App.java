package fr.adlere.bnp.supermarket.checkout;

import java.util.Arrays;

/**
 * Main class to used to run the supermarket app
 * 
 * @author sheni
 *
 */
public class App {

	public static void main(String[] args) {

		Store store = Store.createStore();
		Basket basket = Basket.createBasket(store);

		if (args.length > 0) {
			PrintUtils.printHeader();
			if (!args[0].equalsIgnoreCase("bulk")) {
				Arrays.stream(args).forEach(articleName -> {
					basket.scan(articleName);
				});
			} else {
				try {
					for (int i = 1; i < args.length - 1; i += 2) {
						basket.scan(args[i], Integer.parseInt(args[i + 1]));
					}
				} catch (NumberFormatException e) {
					System.err.println(
							"Error ! Quantity value expected and found a String. Every article should be followed by it's quantity.");
				}
			}
			PrintUtils.printFooter(basket.total());
		}
	}
}
