package project.sample.inventory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryManagerPerformanceTest {

    private InventoryManager inventoryManager;
    private static final int NUM_PRODUCTS = 1_000_000; // Test with 1,000,000 products

    @BeforeEach
    public void setUp() {
        inventoryManager = new InventoryManager();
    }

    @AfterEach
    public void tearDown() {
        inventoryManager.clearStore();
    }

    @Test
    public void testAddProduct_Performance() {
        long startTime = System.nanoTime();

        for (int i = 1; i <= NUM_PRODUCTS; i++) {
            String id = "P" + i;
            String name = "Product" + i;
            String category = (i % 2 == 0) ? "Electronics" : "Clothing";
            double price = i * 10.0;
            Product product = new Product(id, name, category, price);
            inventoryManager.addProduct(product);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("Time taken to add " + NUM_PRODUCTS + " products: " + duration + " ms");

        // Ensure the operation completes within a reasonable time, adjust this threshold as needed
        assertTrue(duration < 5000, "Performance test failed: Adding products took too long");
    }

    @Test
    public void testUpdateProduct_Performance() {
        // Add initial products
        for (int i = 1; i <= NUM_PRODUCTS; i++) {
            String id = "P" + i;
            String name = "Product" + i;
            String category = (i % 2 == 0) ? "Electronics" : "Clothing";
            double price = i * 10.0;
            Product product = new Product(id, name, category, price);
            inventoryManager.addProduct(product);
        }

        long startTime = System.nanoTime();

        for (int i = 1; i <= NUM_PRODUCTS; i++) {
            String id = "P" + i;
            Product updatedProduct = new Product(id, "UpdatedProduct" + i, "UpdatedCategory", i * 20.0);
            inventoryManager.updateProduct(updatedProduct);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("Time taken to update " + NUM_PRODUCTS + " products: " + duration + " ms");

        // Set a threshold to ensure it finishes in a reasonable time
        assertTrue(duration < 5000, "Performance test failed: Updating products took too long");
    }

    @Test
    public void testFilterByCategory_Performance() {
        // Add initial products
        for (int i = 1; i <= NUM_PRODUCTS; i++) {
            String id = "P" + i;
            String name = "Product" + i;
            String category = (i % 2 == 0) ? "Electronics" : "Clothing";
            double price = i * 10.0;
            Product product = new Product(id, name, category, price);
            inventoryManager.addProduct(product);
        }

        long startTime = System.nanoTime();

        // Filter by category
        inventoryManager.filterByCategory("Electronics");

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("Time taken to filter products by category: " + duration + " ms");

        // Set a reasonable time threshold
        assertTrue(duration < 1000, "Performance test failed: Filtering by category took too long");
    }

    @Test
    public void testRemoveProduct_Performance() {
        // Add initial products
        for (int i = 1; i <= NUM_PRODUCTS; i++) {
            String id = "P" + i;
            String name = "Product" + i;
            String category = (i % 2 == 0) ? "Electronics" : "Clothing";
            double price = i * 10.0;
            Product product = new Product(id, name, category, price);
            inventoryManager.addProduct(product);
        }

        long startTime = System.nanoTime();

        for (int i = 1; i <= NUM_PRODUCTS; i++) {
            String id = "P" + i;
            inventoryManager.removeProduct(id);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        System.out.println("Time taken to remove " + NUM_PRODUCTS + " products: " + duration + " ms");

        // Set a threshold for performance
        assertTrue(duration < 5000, "Performance test failed: Removing products took too long");
    }
}

