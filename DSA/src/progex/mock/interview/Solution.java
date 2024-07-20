import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class Solution {

    static HashMap<Integer, HashSet<String>> documents = new HashMap<>();

    static class SearchResult
    {
        int documentNumber;
        int matchCount;
        SearchResult(int documentNumber, int matchCount) {
            this.documentNumber = documentNumber;
            this.matchCount = matchCount;
        }

        public int getDocumentNumber() {
            return this.documentNumber;
        }

        @Override
        public String toString() {
            return String.valueOf(getDocumentNumber());
        }
    }

    static class resultComparator implements Comparator<SearchResult> {

        @Override
        public int compare(SearchResult o1, SearchResult o2) {
            int matchCountCompare =  o2.matchCount - o1.matchCount;
            int docNumCompare = o1.documentNumber - o2.documentNumber;
            return (matchCountCompare == 0) ? docNumCompare : matchCountCompare;
        }
    }

    public static void storeDocument(final String document, final int documentNumber) {
        System.out.println("storeDocument - docNum: " + documentNumber + ", doc: "+ document);
        documents.put(documentNumber, new HashSet<>(Arrays.asList(document.split(" "))));
        System.out.println("After put to documents: " + documents);
    }

    public static String performSearch(final String search) {
        TreeSet<SearchResult> searchResults = new TreeSet<>(new resultComparator());
        System.out.println("Current documents: " + documents);
        HashSet<String> terms = new HashSet<>(Arrays.asList(search.split(" ")));
        // int termSize = terms.size();
        System.out.println("Perform search for : " + terms);
        for (Map.Entry<Integer, HashSet<String>> document : documents.entrySet()) {
            int matchCount = 0;
            HashSet<String> docSet = document.getValue();
            for (String term : terms) {
                if (docSet.contains(term)) {
                    System.out.println("Found match for term : " + term);
                    matchCount++;
                    System.out.println("matchCount : " + matchCount);
                }
            }

            if (matchCount > 0) {
                searchResults.add(new SearchResult(document.getKey(), matchCount));
                System.out.println("After added search result : " + searchResults);
            }
        }
        if (searchResults.isEmpty()) searchResults.add(new SearchResult(-1, 0));
        System.out.println("All search result: " + searchResults);

        StringBuilder results = new StringBuilder();
        int i = 0;
        for (SearchResult searchResult : searchResults) {
            if (i < 10) {
                results.append(searchResult);
                results.append(" ");
                i++;
            }
        }

        String finalResult = results.toString().trim();
        System.out.println("finalResult: " + finalResult);
        return finalResult;
    }


    /*
    Optimization notes: Due to hackerrank global timeout definitions per language, programs
    except for the most optimized will have a tough time passing the last test case.
    This is known and you will not be penalized for it.

    The below code handles input and output and should not require any modification - you should focus on the functions defined above.
     */
    public static void main(String args[] ) throws Exception {
        // Read input from STDIN. Print output to STDOUT
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        // Read documents
        for (int i = 0; i < N; i++) {
            storeDocument(br.readLine(), i);
        }

        final BufferedOutputStream output = new BufferedOutputStream(System.out);

        final int M = Integer.parseInt(br.readLine());
        // Read searches
        for (int j = 0; j < M; j++) {
            output.write((performSearch(br.readLine()) + "\n").getBytes());
        }

        output.flush();

        output.close();
        br.close();
    }
}