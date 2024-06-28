package mockwb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class StarredJobs {

    int leastPriority;

    Queue<Job> pQueue;

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

    StarredJobs() {
        leastPriority = 0;
        pQueue = new PriorityQueue<>();
    }

    void add(int id) {
        if (pQueue.isEmpty()) {
            // O(1)
            pQueue.add(new Job(id, 0));
        } else {
            leastPriority--;
            // O(log n)
            pQueue.add(new Job(id, leastPriority));
        }
    }

    void star(int id) {
        if (!pQueue.isEmpty()) {
            // O(1)
            int topPriority = pQueue.peek().priority + 1;
            Job starredJob = new Job(id, topPriority);
            // O(n)
            pQueue.remove(starredJob);
            // O(log n)
            pQueue.add(new Job(id, topPriority));
        }
    }

    void remove(int id) {
        Job removedJob = new Job(id, 0);
        if (!pQueue.isEmpty()) {
            // O(n)
            pQueue.remove(removedJob);
        }
    }

    void show() {
        List<Job> tempList = new ArrayList<>(pQueue);
        // O n(log n) -> merge sort
        tempList.sort(Comparator.naturalOrder());
        System.out.println(tempList);
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
