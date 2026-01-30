public class Solution {
    public int minimumDifference(int[] nums, int k) {
        // Sort the array in ascending order to group similar values together
        Arrays.sort(nums);
      
        // Initialize minimum difference with a large value (maximum possible difference)
        int minDifference = 100000;
      
        // Iterate through all possible windows of size k
        // We stop at nums.length - k + 1 to ensure we have k elements in each window
        for (int i = 0; i <= nums.length - k; i++) {
            // Calculate the difference between the maximum (last) and minimum (first) 
            // elements in the current window of size k
            int currentDifference = nums[i + k - 1] - nums[i];
          
            // Update the minimum difference if current window has smaller difference
            minDifference = Math.min(minDifference, currentDifference);
        }
      
        // Return the minimum difference found among all windows
        return minDifference;
    }
}
