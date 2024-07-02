package mockwb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public class NewStarredJobs {

    LinkedHashSet<Integer> jobs;
    LinkedHashSet<Integer> stars;

    NewStarredJobs() {
        jobs = new LinkedHashSet<>();
        stars = new LinkedHashSet<>();
    }

    void add(int id) {
        // O(1)
        jobs.add(id);
    }

    void remove(int id) {
        // O(1)
        jobs.remove(id);
        // O(1)
        stars.remove(id);
    }

    void star(int id) {
        // O(1)
        jobs.remove(id);
        // O(1)
        stars.add(id);
    }

    void show() {
        System.out.print("[");
        if (!stars.isEmpty()) {
            // O(n)
            ArrayList<Integer> reversedStars = new ArrayList<>(stars);
            // Reverse the ArrayList - O(n)
            Collections.reverse(reversedStars);
            // O(n)
            for (int job : reversedStars) {
                System.out.print(job + ",");
            }
        }

        for (int job : jobs) {
            System.out.print(job + ",");
        }
        System.out.println("\b]");
    }

    public static void main(String[] args) {
        NewStarredJobs sol = new NewStarredJobs();
        sol.add(1);
        sol.add(2);
        sol.add(3);
        // [1, 2, 3]
        sol.show();
        sol.remove(1);
        sol.star(3);
        // [3, 2]
        sol.show();
        sol.add(4);
        sol.add(5);
        sol.star(4);
        // [4, 3, 2, 5]
        sol.show();
        // new test
        sol.remove(4);
        sol.star(5);
        // [5,4,2]
        sol.show();
    }
}
