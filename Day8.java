class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
      
        for (int i = 0; i < n; i++) {
            int currentNum = nums.get(i);
          
            // Special case: no valid answer exists for 2
            // Because for any x, x | (x+1) cannot equal 2
            if (currentNum == 2) {
                result[i] = -1;
            } else {
                // Find the first 0 bit starting from position 1
                // Then flip the bit at position (j-1) to get the minimum value
                for (int bitPosition = 1; bitPosition < 32; bitPosition++) {
                    // Check if the bit at current position is 0
                    if ((currentNum >> bitPosition & 1) == 0) {
                        // XOR with 1 shifted left by (bitPosition - 1) 
                        // This flips the bit at position (bitPosition - 1)
                        result[i] = currentNum ^ (1 << (bitPosition - 1));
                        break;
                    }
                }
            }
        }
      
        return result;
    }
}