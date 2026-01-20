public class Solution {
    /**
     * Finds the maximum area of water that can be contained between two vertical lines.
     * Uses two-pointer technique to efficiently find the optimal container.
     * 
     * @param height Array where height[i] represents the height of line at position i
     * @return Maximum area of water that can be contained
     */
    public int maxArea(int[] height) {
        // Initialize two pointers at the start and end of the array
        int leftPointer = 0;
        int rightPointer = height.length - 1;
      
        
        int maxAreaFound = 0;
      
       
        while (leftPointer < rightPointer) {
           
            int currentHeight = Math.min(height[leftPointer], height[rightPointer]);
            int currentWidth = rightPointer - leftPointer;
            int currentArea = currentHeight * currentWidth;
          
            
            maxAreaFound = Math.max(maxAreaFound, currentArea);
          
        
            if (height[leftPointer] < height[rightPointer]) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }
      
        return maxAreaFound;
    }
}
