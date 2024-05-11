package leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// https://leetcode.com/problems/relative-ranks/description/
public class RelativeRanks {
    public static String[] findRelativeRanks(int[] score) {
        final String[] medals = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        PriorityQueue<Integer> scores = new PriorityQueue<>(Comparator.reverseOrder());
        // create the heap for scores
        // Total  O(n log n)
        for (int s : score) { // O(n)
            scores.add(s); // O(log n)
        }
        // Adding the second and third loops to the code won't affect the big O time complexity
        // of the first part (adding elements to the PriorityQueue)
        // Map rank to medal
        HashMap<Integer, String> ranks = new HashMap<>();
        for (int i = 0; i < score.length; i++) { // beware using scores.size() since poll() will remove element from heap
            if (i < medals.length) {
                ranks.put(scores.poll(), medals[i]);
            } else {
                ranks.put(scores.poll(), String.valueOf(i + 1));
            }
        }
        // create result array
        String[] results = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            results[i] = ranks.get(score[i]);
        }
        return results;
    }

    public static void main(String[] args) {
        findRelativeRanks(new int[]{5, 4, 3, 2, 1});
    }
}
