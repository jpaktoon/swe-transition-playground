package leetcode;

public class TargetSum {
    static int ways;
    static int limit;

    public static int findTargetSumWays(int[] nums, int target) {
        ways = 0;
        limit = nums.length;

        findTargetSumWays(nums, target, 0);

        return ways;
    }

    public static void findTargetSumWays(int[] nums, int target, int index) {

        if (index == limit)
        {
            if (target == 0) ways++;
            return;
        }

        // for addition
        findTargetSumWays(nums, target - nums[index] ,index + 1);

        // for subtraction
        findTargetSumWays(nums, target + nums[index] ,index + 1);
    }

    public static void main(String[] args) {
        findTargetSumWays(new int[] {1,1,1,1,1}, 3);
        System.out.println(ways);
    }
}
