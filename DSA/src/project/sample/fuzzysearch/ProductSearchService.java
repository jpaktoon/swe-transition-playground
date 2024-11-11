package project.sample.fuzzysearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductSearchService {

    Set<Product> PRODUCTS = new HashSet<>();

    public static class Product {
        String productId;
        String productName;
        String category;

        Product(String productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }
    }

    public void addProduct(final String productId, final String productName, final String category) {
        PRODUCTS.add(new Product(productId, productName, category));
    }

    public List<Product> search(final String term) {
        return PRODUCTS.stream().filter(x -> naiveStringMatcher(term, x.productName)).toList();
    }

    public List<Product> listByCategory(final String category) {
        return PRODUCTS.stream().filter(x -> x.category.equals(category)).toList();
    }

    private boolean naiveStringMatcher(final String query, final String productName) {
        if (productName.contains(query)) return true; // no typo
        int upto = 2;
        while (upto > 0) {
            Set<String> possibleMatchers = generatePossibleMathcer(query, upto);
            for (String matcher : possibleMatchers) {
                if (productName.contains(matcher)) return true;
            }
            upto--;
        }
        return false;
    }

    private Set<String> generatePossibleMathcer(final String query, final int upto) {
        final int length = query.length();
        Set<String> possibleMatcher = new HashSet<>();
        // length - upto = 5 - 2 = 3
        // i P h o n -> i = 0, upto = 2 -> '' + 'h o n'
        // i P h o n -> i = 1, upto = 2 -> 'i' + 'o n'
        // i P h o n -> i = 2, upto = 2 -> 'i P' + 'n'
        // i P h o n -> i = 3, upto = 2 -> 'i P h' + ''
        for (int i = 0; i <= length - upto; i++) {
            possibleMatcher.add(query.substring(0, i) + query.substring(i + upto));
        }
        return possibleMatcher;
    }
}
