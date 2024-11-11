package project.mock;

import java.util.*;
import java.util.stream.Collectors;

public class Solution2 {

    static class SearchResult {
        int documentNumber;
        int matchCount;
        SearchResult(int documentNumber, int matchCount) {
            this.documentNumber = documentNumber;
            this.matchCount = matchCount;
        }

        public int getDocumentNumber() {
            return this.documentNumber;
        }

        public int getMatchCount() {
            return matchCount;
        }

        @Override
        public String toString() {
            return String.valueOf(getDocumentNumber());
        }
    }

    static class resultComparator implements Comparator<SearchResult> {

        @Override
        public int compare(SearchResult o1, SearchResult o2) {
//            int matchCountCompare =  o2.matchCount - o1.matchCount;
//            int docNumCompare = o1.documentNumber - o2.documentNumber;
            // handle potential integer overflow
            int matchCountCompare = Integer.compare(o2.matchCount, o1.matchCount);
            int docNumCompare = Integer.compare(o1.documentNumber, o2.documentNumber);
            return (matchCountCompare == 0) ? docNumCompare : matchCountCompare;
        }
    }

    private static final Map<String, Set<Integer>> termToIdsMap = new HashMap<>();

    // Method to store terms and their associated ID
    public static void storeDocument(final String document, final int documentNumber) {
        String[] termArray = document.split(" ");
        for (String term : termArray) {
            termToIdsMap.computeIfAbsent(term, k -> new HashSet<>()).add(documentNumber);
        }
    }

    // Method to find the top 10 matches by term occurrence
    public static String performSearch(final String search) {
        Set<String> targetSet = new HashSet<>(Arrays.asList(search.split(" ")));
        // Map to store the count of matches for each ID
        Map<Integer, Integer> matchCountMap = new HashMap<>();
        TreeSet<SearchResult> searchResults = new TreeSet<>(new resultComparator());

        // Count the matches using the termToIdsMap
        for (String term : targetSet) {
            Set<Integer> ids = termToIdsMap.get(term);
            if (ids != null) {
                for (Integer id : ids) {
                    matchCountMap.put(id, matchCountMap.getOrDefault(id, 0) + 1);
                }
            }
        }

        // Add entries to the TreeSet and keep its size to 10
        for (Map.Entry<Integer, Integer> entry : matchCountMap.entrySet()) {
            searchResults.add(new SearchResult(entry.getKey(), entry.getValue()));
            if (searchResults.size() > 10) {
                searchResults.pollLast(); // Remove the lowest entry when size exceeds 10
            }
        }

        return searchResults.stream()
                .map(SearchResult::toString)
                .collect(Collectors.joining(" "));
    }
}
