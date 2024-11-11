package project.sample.urlshortener;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class URLShortenerServiceTest {

    private URLShortenerService urlShortenerService;

    @BeforeEach
    public void setup() {
        urlShortenerService = new URLShortenerService();
    }

    @AfterEach
    public void teardown() {
        urlShortenerService.clear();
    }

    @Test
    public void testShortenURL() {
        String longURL = "https://www.example.com/some-long-url";
        String shortURL = urlShortenerService.shortenURL(longURL);

        // Check if short URL is generated
        assertNotNull(shortURL);
        assertEquals(6, shortURL.length());

        // Retrieve the original URL from the short URL
        String originalURL = urlShortenerService.getOriginalURL(shortURL);
        assertEquals(longURL, originalURL);
    }

    @Test
    public void testRetrieveOriginalURL() {
        String longURL = "https://www.example.com/some-long-url";
        String shortURL = urlShortenerService.shortenURL(longURL);

        // Retrieve the original URL
        String originalURL = urlShortenerService.getOriginalURL(shortURL);
        assertEquals(longURL, originalURL);
    }

    @Test
    public void testListAllURLs() {
        String longURL1 = "https://www.example.com/some-long-url";
        String longURL2 = "https://www.anotherexample.com/another-url";
        String shortURL1 = urlShortenerService.shortenURL(longURL1);
        String shortURL2 = urlShortenerService.shortenURL(longURL2);

        // List all URL mappings
        List<String> allURLs = urlShortenerService.listAllURLs();
        assertEquals(2, allURLs.size());
        assertTrue(allURLs.contains(shortURL1 + " -> " + longURL1));
        assertTrue(allURLs.contains(shortURL2 + " -> " + longURL2));
    }

    @Test
    public void testDeleteURL() {
        String longURL = "https://www.example.com/some-long-url";
        String shortURL = urlShortenerService.shortenURL(longURL);

        // Verify URL was added
        assertEquals(longURL, urlShortenerService.getOriginalURL(shortURL));

        // Delete the short URL
        urlShortenerService.deleteURL(shortURL);

        // Try to retrieve the deleted short URL (should throw exception or return null)
        assertThrows(IllegalArgumentException.class, () -> {
            urlShortenerService.getOriginalURL(shortURL);
        });
    }

    @Test
    public void testShortenSameURLTwice() {
        String longURL = "https://www.example.com/some-long-url";
        String shortURL1 = urlShortenerService.shortenURL(longURL);
        String shortURL2 = urlShortenerService.shortenURL(longURL);

        // Verify that shortening the same URL twice generates the same short URL
        assertEquals(shortURL1, shortURL2);

        // Ensure that retrieving the original URL still works
        assertEquals(longURL, urlShortenerService.getOriginalURL(shortURL1));
    }

    @Test
    public void testRetrieveNonExistentURL() {
        // Try to retrieve a short URL that doesn't exist (should throw exception or return null)
        assertThrows(IllegalArgumentException.class, () -> {
            urlShortenerService.getOriginalURL("nonexistent");
        });
    }

    @Test
    public void testDeleteNonExistentURL() {
        // Try to delete a non-existent URL (should throw exception)
        assertThrows(IllegalArgumentException.class, () -> {
            urlShortenerService.deleteURL("nonexistent");
        });
    }
}
