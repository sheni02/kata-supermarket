package fr.adlere.bnp.supermarket.checkout.exception;

public class ArticleNotFoundException extends Exception {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
