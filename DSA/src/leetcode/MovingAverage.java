package leetcode;

import main.datastructures.Queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {

    public class MovingAverageSlidingWindows {
        Integer[] windows;
        int lastUsedIndex;
        int size;

        public MovingAverageSlidingWindows(int size) {
            this.windows = new Integer[size];
            this.lastUsedIndex = 0; // O ... size-1
            this.size = size;
        }

        public double next(int val) {
            this.windows[this.lastUsedIndex] = val;
            this.lastUsedIndex = (this.lastUsedIndex + 1) % this.size;
            return avg(this.windows);
        }

        public double avg(Integer[] nums) {
            int count = 0;
            int total = 0;
            for (Integer num : nums) {
                if (num != null) {
                    count++;
                    total += num;
                }
            }
            return (double) total / count;
        }
    }

    Deque<Integer> window = new ArrayDeque<>();
    int size;
    int sum;

    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0;
    }

    public double next(int val) {
        window.offer(val);
        int off = (window.size() > this.size) ? window.poll() : 0;
        this.sum = this.sum - off + val;
        return 1.0 * this.sum / window.size();
    }
}
