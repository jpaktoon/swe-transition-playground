package project.sample.inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
    HashMap for main data
    TreeSet for sorting
    Another hashMap for filter
    Perf
        Time taken to add 1000000 products: 379 ms
        Time taken to update 1000000 products: 529 ms
        Time taken to remove 1000000 products: 181 ms
        Time taken to filter products by category: 11 ms
 */

public class InventoryManagerV2 {

    static Map<String, Product> PRODUCTS = new HashMap<>();

    //Sorted by price
    static Set<Product> SORTED_BY_PRICE = new TreeSet<>(Comparator.comparingDouble(Product::getPrice));

    //Filtered by category
    static Map<String, Set<Product>> PRODUCTS_BY_CATEGORY = new HashMap<>();

    public void addProduct(final Product product) {
        if (productExist(product.productId)) {
            throw new IllegalArgumentException("Product with this ID already exists");
        }
        PRODUCTS.put(product.productId, product);
        SORTED_BY_PRICE.add(product);
        addProductToCategory(product);
    }

    public List<Product> listProducts() {
        return new ArrayList<>(SORTED_BY_PRICE);
    }

    public void updateProduct(final Product product) {
        if (productExist(product.productId)) {
            final Product exitingProduct = PRODUCTS.get(product.productId);
            SORTED_BY_PRICE.remove(exitingProduct);
            PRODUCTS_BY_CATEGORY.get(exitingProduct.category).remove(exitingProduct);

            PRODUCTS.put(product.productId, product);
            SORTED_BY_PRICE.add(product);
            addProductToCategory(product);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public void removeProduct(final String productId) {
        if (productExist(productId)) {
            final Product exitingProduct = PRODUCTS.get(productId);
            SORTED_BY_PRICE.remove(exitingProduct);
            removeProductFromCategory(exitingProduct);
            PRODUCTS.remove(productId);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    public ArrayList filterByCategory(final String category) {
        //return new ArrayList<>(PRODUCTS.values().stream().filter(p -> p.category.equals(category)).toList());
        return new ArrayList<>(PRODUCTS_BY_CATEGORY.getOrDefault(category, Collections.emptySet()));
    }

    public void clearStore() {
        PRODUCTS.clear();
        SORTED_BY_PRICE.clear();
        PRODUCTS_BY_CATEGORY.clear();
    }

    private boolean productExist(final String productId) {
        return PRODUCTS.containsKey(productId);
    }

    private void addProductToCategory(final Product product) {
        if (PRODUCTS_BY_CATEGORY.containsKey(product.category)) {
            PRODUCTS_BY_CATEGORY.get(product.category).add(product);
        } else {
            final Set<Product> newCategory = new HashSet<>();
            newCategory.add(product);
            PRODUCTS_BY_CATEGORY.put(product.category, newCategory);
        }
    }

    private void removeProductFromCategory(final Product product) {
        if (PRODUCTS_BY_CATEGORY.containsKey(product.category)) {
            PRODUCTS_BY_CATEGORY.get(product.category).remove(product);
        }
    }
}
