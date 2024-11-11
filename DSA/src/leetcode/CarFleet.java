package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class CarFleet {
    // 10
    // . 1 . . . . . . . . 10 -> (10 - 1) / 3 = 3 hrs
    // . . . . 4 . . . . . 10 -> (10 - 4) / 2 = 3 hrs

    // 10
    // . . . . 4 . . . . . 10 -> (10 - 4) / 2 = 3 hrs
    // . 1 . . . . . . . . 10 -> (10 - 1) / 2 = 4.5 hrs
    // 0 . . . . . . . . . 10 -> (10 - 0) / 1 = 10 hrs
    // . . . . . . . 7 . . 10 -> (10 - 7) / 1 = 3 hrs

    // 10
    // . . . 3 . x . . . . 10 -> (10 - 3) / 2 = 7/2 hrs
    // . . 2 . . x . . . . 10 -> (10 - 2) / 3 = 8/3 hrs

    // check each hour where are each cars
    // add the cars with new position and (min) speed
    // until all fleet reach the destination, how many of them

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        // Array to hold (position, time to reach target)
        double[][] cars = new double[n][2];

        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = (double) (target - position[i]) / speed[i];
        }

        // Sort cars by position from farthest to closest to the target
        Arrays.sort(cars, Comparator.comparingDouble(a -> -a[0]));

        for (double[] car : cars) {
            System.out.println(car[0]);
        }

        int fleets = 0;
        double lastTime = 0;

        // Traverse cars to count fleets
        for (int i = 0; i < n; i++) {
            double time = cars[i][1];
            // If the current car takes longer, it forms a new fleet
            if (time > lastTime) {
                fleets++;
                lastTime = time;
            }
        }

        return fleets;
    }


    public static void main(String[] args) {
        CarFleet cf = new CarFleet();
        int fleets = cf.carFleet(31, new int[] {5,26,18,25,29,21,22,12,19,6}, new int[] {7,6,6,4,3,4,9,7,6,4});
        System.out.println(fleets);
    }
}
