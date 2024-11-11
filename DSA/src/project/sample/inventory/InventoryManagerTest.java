package project.sample.inventory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class InventoryManagerTest {

    private InventoryManager inventoryManager;

    @BeforeEach
    public void setUp() {
        inventoryManager = new InventoryManager();
    }

    @AfterEach
    public void tearDown() {
        inventoryManager.clearStore();
    }

    @Test
    public void testAddProduct_Success() {
        Product product = new Product("P123", "Laptop", "Electronics", 1200.0);
        inventoryManager.addProduct(product);
        List<Product> products = inventoryManager.listProducts();
        assertEquals(1, products.size());
        assertEquals("Laptop", products.get(0).getName());
    }

    @Test
    public void testAddProduct_DuplicateId() {
        Product product1 = new Product("P123", "Laptop", "Electronics", 1200.0);
        Product product2 = new Product("P123", "Phone", "Electronics", 800.0);
        inventoryManager.addProduct(product1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryManager.addProduct(product2);
        });
        assertEquals("Product with this ID already exists", exception.getMessage());
    }

    @Test
    public void testUpdateProduct_Success() {
        Product product = new Product("P123", "Laptop", "Electronics", 1200.0);
        inventoryManager.addProduct(product);
        Product updatedProduct = new Product("P123", "Laptop", "Electronics", 1150.0);
        inventoryManager.updateProduct(updatedProduct);

        List<Product> products = inventoryManager.listProducts();
        assertEquals(1, products.size());
        assertEquals(1150.0, products.get(0).getPrice());
    }

    @Test
    public void testUpdateProduct_NotFound() {
        Product product = new Product("P999", "Tablet", "Electronics", 300.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryManager.updateProduct(product);
        });
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    public void testRemoveProduct_Success() {
        Product product = new Product("P123", "Laptop", "Electronics", 1200.0);
        inventoryManager.addProduct(product);
        inventoryManager.removeProduct("P123");

        List<Product> products = inventoryManager.listProducts();
        assertTrue(products.isEmpty());
    }

    @Test
    public void testRemoveProduct_NotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inventoryManager.removeProduct("P999");
        });
        assertEquals("Product not found", exception.getMessage());
    }

    @Test
    public void testListProducts_SortedByPrice() {
        Product product1 = new Product("P123", "Laptop", "Electronics", 1200.0);
        Product product2 = new Product("P456", "Phone", "Electronics", 800.0);
        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);

        List<Product> products = inventoryManager.listProducts();
        assertEquals(2, products.size());
        assertEquals("Phone", products.get(0).getName()); // Cheaper product should come first
    }

    @Test
    public void testFilterByCategory() {
        Product product1 = new Product("P123", "Laptop", "Electronics", 1200.0);
        Product product2 = new Product("P456", "Phone", "Electronics", 800.0);
        Product product3 = new Product("P789", "T-shirt", "Clothing", 20.0);
        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);
        inventoryManager.addProduct(product3);

        List<Product> electronics = inventoryManager.filterByCategory("Electronics");
        assertEquals(2, electronics.size());
        assertEquals("Phone", electronics.get(0).getName()); // Sorted by price
    }

    @Test
    public void testFilterByCategory_NoResults() {
        Product product = new Product("P123", "Laptop", "Electronics", 1200.0);
        inventoryManager.addProduct(product);

        List<Product> clothing = inventoryManager.filterByCategory("Clothing");
        assertTrue(clothing.isEmpty());
    }
}
