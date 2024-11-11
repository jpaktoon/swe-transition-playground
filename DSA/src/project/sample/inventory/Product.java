package project.sample.inventory;


/* Each product has the following attributes:

Product ID (String): A unique identifier for each product.
Product Name (String): Name of the product.
Category (String): Category to which the product belongs (e.g., "Electronics", "Clothing").
Price (double): Price of the product.
*/

// public class Product implements Comparable{
public class Product {

    String productId;
    String productName;
    String category;
    double price;

    public Product(final String productId, final String productName, final String category, final double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
    }

    public String getName(){
        return this.productName;
    }

    public double getPrice() {
        return this.price;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o.getClass() == Product.class) {
//            return this.productId.equals(((Product) o).productId);
//        }
//        return false;
//    }

//    @Override
//    public int compareTo(Object o) {
//        final double otherPrice = ((Product) o).price;
//        if (this.price > otherPrice) {
//            return 1;
//        } else if (this.price < otherPrice) {
//            return -1;
//        }
//        return 0;
//    }
}
