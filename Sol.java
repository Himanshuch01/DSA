public class Solution {
    public int minPairSum(int[] nums) {
        // Sort the array in ascending order
        Arrays.sort(nums);
      
        // Initialize the maximum pair sum
        int maxPairSum = 0;
        int arrayLength = nums.length;
      
        // Pair the smallest element with the largest, second smallest with second largest, etc.
        // This greedy approach minimizes the maximum pair sum
        for (int i = 0; i < arrayLength / 2; i++) {
            // Calculate the sum of current pair (element at index i and its corresponding element from the end)
            int currentPairSum = nums[i] + nums[arrayLength - i - 1];
          
            // Update the maximum pair sum if current pair sum is larger
            maxPairSum = Math.max(maxPairSum, currentPairSum);
        }
      
        // Return the minimized maximum pair sum
        return maxPairSum;
    }
}
 