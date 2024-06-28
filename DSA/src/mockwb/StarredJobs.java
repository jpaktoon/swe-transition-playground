package mockwb;

import java.util.*;

public class StarredJobs {
    private final TreeSet<Job> treeSet;

    static class Job implements Comparable<Job>{
        int jobId;
        int priority;

        Job (int jobId, int priority) {
            this.jobId = jobId;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return String.valueOf(this.jobId);
        }

        @Override
        public int compareTo(Job other) {
            if (this.jobId != other.jobId) {
                // in case it is not the same job, descending order
                return other.priority - this.priority;
            } else {
                // in case it is the same job
                return 0;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Job other = (Job) obj;
            return other.jobId == this.jobId;
        }
    }

    public StarredJobs() {
        treeSet = new TreeSet<>();
    }

    public void add(int id) {
        if (treeSet.isEmpty()) {
            treeSet.add(new Job(id, 0));
        } else {
            // O(log n)
            int leastPriority = treeSet.last().priority - 1;
            treeSet.add(new Job(id, leastPriority));
        }
    }

    void star(int id) {
        if (!treeSet.isEmpty()) {
            remove(id);
            // O(log n)
            int topPriority = treeSet.first().priority + 1;
            treeSet.add(new Job(id, topPriority));
        }
    }

    void remove(int id) {
        // O(n)
        treeSet.removeIf(job -> job.jobId == id);
    }

    public void show() {
        // O(n)
        System.out.println(treeSet);
    }

    public static void main(String[] args) {
        StarredJobs sol = new StarredJobs();
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