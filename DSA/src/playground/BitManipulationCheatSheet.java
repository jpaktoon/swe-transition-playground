package playground;

public class BitManipulationCheatSheet {

    public static void main(String[] args) {
        int n = 11;
        int res = n ^ n; // result of n ^ n always be Zero
        int[] nums = {2, 3, 3, 4, 2};
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        System.out.println(single);
    }
}
