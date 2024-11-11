package project.sample.inventory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/* a class InventoryManager that provides the following functionality:

Add a Product:
    Add a new product to the inventory. If a product with the same ID already exists, the system should reject the addition.

Update a Product:
    Update an existing product’s details. The system should allow updating the product’s name, category, and price.

Remove a Product:
    Remove a product from the inventory by its ID. If no product exists with the given ID, throw an appropriate exception.

List Products:
    List all products, sorted by price in ascending order.

Filter by Category:
    Provide a method to retrieve a list of products in a given category, sorted by price in ascending order.

 */

/*
    HashMap for main data
    TreeSet for sorting
    Perf.
        Time taken to add 1000000 products: 216 ms
        Time taken to update 1000000 products: 225 ms
        Time taken to remove 1000000 products: 84 ms
        Time taken to filter products by category: 24 ms
 */

public class InventoryManager {

    static Map<String, Product> PRODUCTS = new HashMap<>();

    //Sorted by price
    /*
    You're currently storing products in both HashMap (PRODUCTS) and TreeSet (SORTED_BY_PRICE).
    This leads to memory duplication since each Product is held in two collections.
    While this is necessary for sorting, it's something to be mindful of.
    If memory becomes an issue, you might explore reducing the data stored in the TreeSet
    (such as storing only a subset of the Product properties needed for sorting).
    */
    static Set<Product> SORTED_BY_PRICE = new TreeSet<>(Comparator.comparingDouble(Product::getPrice));

    public void addProduct(final Product product) {
        if (productExist(product.productId)) {
            throw new IllegalArgumentException("Product with this ID already exists");
        }
        PRODUCTS.put(product.productId, product);
        SORTED_BY_PRICE.add(product);
    }

    public List<Product> listProducts() {
        return new ArrayList<>(SORTED_BY_PRICE);
    }

    public void updateProduct(final Product product) {
        final Product exitingProduct = PRODUCTS.get(product.productId);
        if (exitingProduct != null) {
            SORTED_BY_PRICE.remove(exitingProduct);
            PRODUCTS.put(product.productId, product);
            SORTED_BY_PRICE.add(product);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public void removeProduct(final String productId) {
        final Product exitingProduct = PRODUCTS.get(productId);
        if (exitingProduct != null) {
            SORTED_BY_PRICE.remove(exitingProduct);
            PRODUCTS.remove(productId);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public List<Product> filterByCategory(final String category) {
        return new ArrayList<>(PRODUCTS.values().stream().filter(p -> p.category.equals(category)).toList());
    }

    public void clearStore() {
        PRODUCTS.clear();
        SORTED_BY_PRICE.clear();
    }

    private boolean productExist(final String productId) {
        return PRODUCTS.containsKey(productId);
    }
}
