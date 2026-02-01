class Solution {
    public int minimumCost(int[] nums) {
        // First element is always included in the sum
        int firstElement = nums[0];
      
        // Initialize the two smallest values from the remaining elements
        // Using 100 as initial value (assuming array elements are less than 100)
        int smallest = 100;
        int secondSmallest = 100;
      
        // Find the two smallest elements from index 1 onwards
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < smallest) {
                // Current element becomes the new smallest
                // Previous smallest becomes second smallest
                secondSmallest = smallest;
                smallest = nums[i];
            } else if (nums[i] < secondSmallest) {
                // Current element is between smallest and second smallest
                secondSmallest = nums[i];
            }
        }
      
        // Return sum of first element and two smallest from the rest
        return firstElement + smallest + secondSmallest;
    }
}