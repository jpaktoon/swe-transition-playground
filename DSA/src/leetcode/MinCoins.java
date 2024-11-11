package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinCoins {

    //List<Integer> coins = new ArrayList<>(List.of(1, 2, 5, 10, 20, 50, 100, 500, 1000));
    List<Integer> coins = new ArrayList<>(List.of(357,239,73,52));

    public List<Integer> minCoins(int changes) {
        PriorityQueue<Integer> orderCoins = new PriorityQueue<>(Comparator.reverseOrder());
        orderCoins.addAll(coins); // O (n logn)
        List<Integer> chargeCoins = new ArrayList<>();

        while (changes != 0) {
            int highestCoin = orderCoins.poll();
            if (highestCoin <= changes ) {
                int numberOfCoins = changes / highestCoin;
                changes = changes - (numberOfCoins * highestCoin);
                for (int i = 0; i < numberOfCoins; i++) {
                    chargeCoins.add(highestCoin);
                }
            }
        }
        return chargeCoins;
    }

    public List<Integer> minCoinsV2(int changes) {
        Arrays.sort(coins.toArray()); // O (n log n)
        List<Integer> chargeCoins = new ArrayList<>();
        int maxCoinIndex = coins.size() - 1;
        while (changes != 0) {
            int highestCoin = coins.get(maxCoinIndex);
            if (highestCoin <= changes ) {
                int numberOfCoins = changes / highestCoin;
                changes = changes - (numberOfCoins * highestCoin);
                for (int i = 0; i < numberOfCoins; i++) {
                    chargeCoins.add(highestCoin);
                }
            }
            maxCoinIndex--; // next coin
        }
        return chargeCoins;
    }

    public static void main(String[] args) {
        MinCoins test = new MinCoins();
        //System.out.println(test.minCoins(100));
        System.out.println(test.minCoinsV2(9832));
    }
}
