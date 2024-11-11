package project.sample.urlshortener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Time taken to shorten 1000000 URLs: 1504 ms
//Time taken to retrieve original URL for 1000000 short URLs: 605 ms

public class URLShortenerServiceV2 {

    private final TreeMap<String, String> URLS = new TreeMap<>();

    public String shortenURL(final String longUrl) {
        final String shortURL = generateShortCode(longUrl);
        if (!URLS.containsKey(shortURL)) URLS.put(shortURL, longUrl);
        return shortURL;
    }

    public String getOriginalURL(final String shortURL) {
        if (!URLS.containsKey(shortURL)) {
            throw new IllegalArgumentException();
        } else {
            return URLS.get(shortURL);
        }
    }

    public List<String> listAllURLs() {
        final List<String> allURLS = new ArrayList<>();
        for (Map.Entry<String, String> entry : URLS.entrySet()) {
            allURLS.add(entry.getKey() + " -> " + entry.getValue());
        }
        return allURLS;
    }

    public void deleteURL(final String shortURL) {
        if (!URLS.containsKey(shortURL)) {
            throw new IllegalArgumentException();
        } else {
            URLS.remove(shortURL);
        }
    }

    private String generateShortCode(String longURL) {
        try {
            // Create a SHA-256 hash of the long URL
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(longURL.getBytes());

            // Convert the byte array to a hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // Truncate the hex string to create a short code (e.g., take first 6 characters)
            return hexString.substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short code", e);
        }
    }

    public void clear() {
        URLS.clear();
    }
}
