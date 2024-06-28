package mockwb;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class NewStarredJobs {

    LinkedHashSet<Integer> jobs;
    LinkedList<Integer> stars;

    NewStarredJobs() {
        jobs = new LinkedHashSet<>();
        stars = new LinkedList<>();
    }

    void add(int id) {
        // O(1)
        jobs.add(id);
    }

    void remove(int id) {
        // O(1)
        jobs.remove(id);
    }

    void star(int id) {
        // O(1)
        jobs.remove(id);
        // O(1)
        stars.push(id);
    }

    void show() {
        // O(n)
        System.out.print("[");
        for (int job : stars) {
            System.out.print(job + ",");
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
    }
}
