package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FractionalKnapsack {
    public static class Item {
        int weight;
        int value;
        double ratio;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = 1.0 * value / weight;
        }
    }

    public void knapSack (List<Item> items, int capacity) {
        items.sort(Comparator.comparingDouble(x -> x.ratio));
        int maxRatioItem = items.size() - 1;
        double totalValue = 0;
        while (capacity > 0 && maxRatioItem >= 0) {
            if (capacity > items.get(maxRatioItem).weight) {
                capacity = capacity - items.get(maxRatioItem).weight;
                System.out.println("Add item value: " + items.get(maxRatioItem).ratio + " per kg for " +  items.get(maxRatioItem).weight + " kg(s).");
                totalValue += items.get(maxRatioItem).value;
            } else {
                System.out.println("Add item value: " + items.get(maxRatioItem).ratio + " per kg for " +  capacity + " kg(s).");
                totalValue += items.get(maxRatioItem).ratio * capacity;
                capacity = 0;
            }
            maxRatioItem--;
        }
        System.out.println("Total value: " + totalValue);
        System.out.println("Still available for : " + capacity + " kg(s).");
    }

    public static void main(String[] args) {
        FractionalKnapsack ks = new FractionalKnapsack();
        List<Item> items = new ArrayList<>(List.of(
                new Item(20, 100), // 5 per kg
                new Item(30, 120), // 4 per kg
                new Item(10, 60) // 6 per kg
        ));

        ks.knapSack(items, 50);
    }
}
