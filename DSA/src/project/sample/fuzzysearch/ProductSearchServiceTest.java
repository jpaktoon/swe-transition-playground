package project.sample.fuzzysearch;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProductSearchServiceTest {

    @Test
    public void testAddAndSearch() {
        ProductSearchService service = new ProductSearchService();

        // Add products
        service.addProduct("1", "iPhone", "Electronics");
        service.addProduct("2", "Samsung Galaxy", "Electronics");
        service.addProduct("3", "MacBook Pro", "Computers");

        // Test fuzzy search
        List<ProductSearchService.Product> result = service.search("iPhon");
        assertEquals(1, result.size());
        assertEquals("iPhone", result.get(0).productName);

        // Test fuzzy search with typo
        result = service.search("MacBk");
        assertEquals(1, result.size());
        assertEquals("MacBook Pro", result.get(0).productName);
    }

    @Test
    public void testListByCategory() {
        ProductSearchService service = new ProductSearchService();

        // Add products
        service.addProduct("1", "iPhone", "Electronics");
        service.addProduct("2", "Samsung Galaxy", "Electronics");
        service.addProduct("3", "MacBook Pro", "Computers");

        // List by category
        List<ProductSearchService.Product> result = service.listByCategory("Electronics");
        assertEquals(2, result.size());
    }

    @Test
    public void testSearchPerformance() {
        ProductSearchService service = new ProductSearchService();

        // Adding a large number of products
        for (int i = 0; i < 100000; i++) {
            service.addProduct(String.valueOf(i), "Product" + i, "Category" + (i % 10));
        }

        long startTime = System.currentTimeMillis();

        // Perform fuzzy search
        service.search("Produc");

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Search took " + duration + " ms");

        // Ensure performance is within an acceptable range (for large datasets)
        assertTrue(duration < 1000, "Search took too long");
    }
}

