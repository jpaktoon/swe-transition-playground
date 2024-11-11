package project.sample.urlshortener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
Time taken to shorten 1000000 URLs: 810 ms
Time taken to retrieve original URL for 1000000 short URLs: 42 ms
*/

public class URLShortenerService {

    private final HashMap<String, String> URLS = new HashMap<>();

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
        final TreeMap<String, String> sortedMap = new TreeMap<>(URLS);
        final List<String> allURLS = new ArrayList<>();
        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
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

            // Encode the byte array to Base64 and take the first 6 characters
            return Base64.getUrlEncoder().encodeToString(hash).substring(0, 6);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short code", e);
        }
    }

    public void clear() {
        URLS.clear();
    }
}
