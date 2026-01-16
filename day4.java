class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // Get all possible horizontal distances between fences (including boundaries)
        Set<Integer> horizontalDistances = calculateAllDistances(hFences, m);
      
        // Get all possible vertical distances between fences (including boundaries)
        Set<Integer> verticalDistances = calculateAllDistances(vFences, n);
      
        // Find common distances that can form squares (equal horizontal and vertical sides)
        horizontalDistances.retainAll(verticalDistances);
      
        // Find the maximum square side length
        int maxSideLength = -1;
        final int MOD = (int) 1e9 + 7;
      
        for (int sideLength : horizontalDistances) {
            maxSideLength = Math.max(maxSideLength, sideLength);
        }
      
        // Calculate and return the area of the maximum square, or -1 if no square is possible
        return maxSideLength > 0 ? (int) (1L * maxSideLength * maxSideLength % MOD) : -1;
    }

    /**
     * Calculate all possible distances between fence positions including boundaries
     * @param fences Array of fence positions
     * @param boundaryPosition The boundary position (m for horizontal, n for vertical)
     * @return Set of all possible distances between any two positions
     */
    private Set<Integer> calculateAllDistances(int[] fences, int boundaryPosition) {
        int fenceCount = fences.length;
      
        // Create a new array including the original fences and two boundaries (1 and boundaryPosition)
        int[] allPositions = Arrays.copyOf(fences, fenceCount + 2);
        allPositions[fenceCount] = 1;                    // Starting boundary
        allPositions[fenceCount + 1] = boundaryPosition; // Ending boundary
      
        // Sort positions to calculate distances efficiently
        Arrays.sort(allPositions);
      
        // Store all possible distances between any two positions
        Set<Integer> distances = new HashSet<>();
      
        // Calculate distance between every pair of positions
        for (int i = 0; i < allPositions.length; ++i) {
            for (int j = 0; j < i; ++j) {
                distances.add(allPositions[i] - allPositions[j]);
            }
        }
      
        return distances;
    }
}