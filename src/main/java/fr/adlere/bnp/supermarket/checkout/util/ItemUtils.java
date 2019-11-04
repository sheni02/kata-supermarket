package fr.adlere.bnp.supermarket.checkout.util;


public class ItemUtils {

	private ItemUtils() {
		throw new IllegalStateException("Utility class can't be instantiated");
	}

	/**
	 * Round a double value to n decimal  
	 * @param value the double number to round
	 * @param n number of decimal
	 * @return a rounded double with n decimal
	 */
	public static double round(double value, int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		long factor = (long) Math.pow(10, n);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
