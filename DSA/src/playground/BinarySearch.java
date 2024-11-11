package playground;

public class BinarySearch {
    public static int binarySearch(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // avoid potential overflow
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;  // target not found
    }

    public static int binarySearchClosestLower(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int closestLower = -1;  // Use -1 as a marker if no lower value is found

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == target) {
                return arr[mid];  // Exact match found
            } else if (arr[mid] < target) {
                closestLower = arr[mid];  // Update closest lower
                low = mid + 1;  // Search in the right half
            } else {
                high = mid - 1;  // Search in the left half
            }
        }

        // If no exact match, return the closest lower value or -1 if no such value exists
        return closestLower;
    }

    /*
    The array needs to be sorted for binary search to work correctly.
    Binary search divides the array into halves and relies on the order
    of elements to determine whether to search the left or right half.

    If the array is unsorted, the search logic would fail,
    as there's no guarantee about which half contains the target.
     */
    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 7, 9, 11};
        int target = 7;
        int result = binarySearch(numbers, target);
        System.out.println("Index of target: " + result);  // Output: Index of target: 3
    }
}