package project.sample.urlshortener;

import java.util.ArrayList;
import java.util.List;

public class URLShortenerPerformanceTest {

    private static final int NUM_URLS = 1_000_000; // Number of URLs to test
    private static final String BASE_LONG_URL = "https://www.example.com/product/";

    public static void main(String[] args) {
        URLShortenerService urlShortenerService = new URLShortenerService();

        // Performance Test for shortenURL method
        long startTime = System.nanoTime();

        List<String> shortURLs = new ArrayList<>();
        for (int i = 0; i < NUM_URLS; i++) {
            String longURL = BASE_LONG_URL + i;
            String shortURL = urlShortenerService.shortenURL(longURL);
            shortURLs.add(shortURL); // Storing short URLs for verification
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("Time taken to shorten " + NUM_URLS + " URLs: " + duration / 1_000_000 + " ms");

        // Performance Test for getOriginalURL method
        startTime = System.nanoTime();

        for (String shortURL : shortURLs) {
            String originalURL = urlShortenerService.getOriginalURL(shortURL);
            assert originalURL != null : "Original URL should not be null";
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;

        System.out.println("Time taken to retrieve original URL for " + NUM_URLS + " short URLs: " + duration / 1_000_000 + " ms");
    }
}
