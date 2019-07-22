### Folder content
- Eclipse project (Java 11, maven 3.6.1)
- SupermarketCheckout-jar-with-dependencies.jar

### How to test
Run the command below and customize the list of args

`$ java -jar SupermarketCheckout-jar-with-dependencies.jar Orange Apple Apple Watermelon Watermelon Watermelon Apple Orange Watermelon Apple Orange`

or add bulk before the list of args to be able to precise the quantity of each product.

`$ java -jar SupermarketCheckout-jar-with-dependencies.jar bulk Orange 3 Apple 4 Watermelon 5`