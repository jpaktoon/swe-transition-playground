package leetcode;

import java.util.PriorityQueue;

public class TotalCostToHireKWorkers {

    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> h1 = new PriorityQueue<>();
        PriorityQueue<Integer> h2 = new PriorityQueue<>();

        int n = costs.length;
        long ans = 0;
        for (int i = 0; i < candidates; i++) {
            h1.add(costs[i]);
        }
        for (int i = Math.max(n - candidates, candidates); i < n; i++) {
            h2.add(costs[i]);
        }

        int h1Next = candidates;
        int h2Next = n - candidates - 1;

        for (int i = 0; i < k; i++) {
            int leftMin = h1.size() == 0 ? Integer.MAX_VALUE : h1.peek();
            int rightMin =  h2.size() == 0 ? Integer.MAX_VALUE : h2.peek();
            if (leftMin <= rightMin) {
                ans += h1.poll();
                if (h1Next <= h2Next) {
                    h1.add(costs[h1Next]);
                    h1Next++;
                }
            } else {
                ans += h2.poll();
                if (h1Next <= h2Next) {
                    h2.add(costs[h2Next]);
                    h2Next--;
                }
            }
        }
        return ans;
    }

    public long totalCostWithCustomClass(int[] costs, int k, int candidates) {
        class Candidate implements Comparable {
            int index;
            long cost;
            boolean isHead;
            public Candidate(final int index, final long cost, final boolean isHead) {
                this.index = index;
                this.cost = cost;
                this.isHead = isHead;
            }

            @Override
            public int compareTo(Object o) {
                Candidate other = (Candidate) o;
                if (this.cost == other.cost)
                {
                    return this.index - other.index;
                } else {
                    return (int) (this.cost - other.cost);
                }
            }

            @Override
            public String toString() {
                return String.format("{cost: %d index: %d isHead: %b}",this.cost, this.index, this.isHead);
            }
        }

        int costSize = costs.length;
        // Create priorityQueue;
        PriorityQueue<Candidate> priorityQueueCosts = new PriorityQueue<>();

        int headIndex = candidates; // next from head
        int tailIndex = costs.length - 1 - candidates; // prev of tail

        // candidates * 2 <= costSize
        if (candidates * 2 <= costSize) {
            // first k elements and Last k elements
            for (int i = 0; i < candidates; i++) {
                priorityQueueCosts.add(new Candidate(i, costs[i], true));
                int indexFromTail = costs.length - 1 - i;
                priorityQueueCosts.add(new Candidate(indexFromTail, costs[indexFromTail], false));
            }
        } else { // k * 2 > costSize
            for (int cost : costs) {
                // either head or tail or whatever index, choose the lowest cost
                priorityQueueCosts.add(new Candidate(0, cost, true));
                headIndex = tailIndex = 0;
            }
        }

        long totalCost = 0;
        // Calculate totalCost
        for (int i = 0; i < k; i++) {
            System.out.println("priorityQueueCosts: "+ priorityQueueCosts);
            if (priorityQueueCosts.isEmpty()) {
                System.out.println("Empty costs");
                break; // edge case
            }
            if (candidates * 2 < costSize) {
                Candidate pollCost = priorityQueueCosts.poll();
                totalCost += pollCost.cost;
                costSize--;
                // add more candidate
                if (headIndex <= tailIndex) { // prevent circle
                    if (pollCost.isHead) {
                        // from head
                        priorityQueueCosts.add(new Candidate(headIndex, costs[headIndex], true));
                        headIndex++;
                    } else {
                        // from tail
                        priorityQueueCosts.add(new Candidate(tailIndex, costs[tailIndex], false));
                        tailIndex--;
                    }
                }

                System.out.printf("Round %d, poll cost %d, index %d, total = %d, size = costSize %d%n",i, pollCost.cost, pollCost.index, totalCost, costSize);
            } else { // Just pick up the lowest cost
                Candidate pollCost = priorityQueueCosts.poll();
                totalCost += pollCost.cost;
                System.out.printf("Round %d, poll cost %d, index %d, total = %d, size = costSize %d%n",i, pollCost.cost, pollCost.index, totalCost, costSize);
                break;
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        int[] costs = {2,2,2,2,2,2,1,4,5,5,5,5,5,2,2,2,2,2,2,2,2,2,2,2,2,2};
        TotalCostToHireKWorkers workers = new TotalCostToHireKWorkers();
        long total = workers.totalCost(costs, 7, 3);
    }
}
