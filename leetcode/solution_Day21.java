class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // Define a large value to represent infinity (unreachable)
        final int INFINITY = 1 << 29;
      
        // Initialize a 2D array to store minimum transformation costs between characters
        // graph[i][j] represents the minimum cost to transform character i to character j
        int[][] graph = new int[26][26];
      
        // Initialize all transformation costs to infinity, except self-transformations (cost 0)
        for (int i = 0; i < 26; i++) {
            Arrays.fill(graph[i], INFINITY);
            graph[i][i] = 0; // Cost to transform a character to itself is 0
        }
      
        // Build the initial graph with direct transformation costs
        for (int i = 0; i < original.length; i++) {
            int fromChar = original[i] - 'a';  // Convert character to index (0-25)
            int toChar = changed[i] - 'a';     // Convert character to index (0-25)
            int transformCost = cost[i];
            // Keep the minimum cost if multiple transformations exist between same characters
            graph[fromChar][toChar] = Math.min(graph[fromChar][toChar], transformCost);
        }
      
        // Apply Floyd-Warshall algorithm to find shortest paths between all character pairs
        for (int intermediate = 0; intermediate < 26; intermediate++) {
            for (int start = 0; start < 26; start++) {
                for (int end = 0; end < 26; end++) {
                    // Check if path through intermediate character is shorter
                    graph[start][end] = Math.min(graph[start][end], 
                                                graph[start][intermediate] + graph[intermediate][end]);
                }
            }
        }
      
        // Calculate total minimum cost to transform source string to target string
        long totalCost = 0;
        int stringLength = source.length();
      
        for (int i = 0; i < stringLength; i++) {
            int sourceChar = source.charAt(i) - 'a';  // Convert to index (0-25)
            int targetChar = target.charAt(i) - 'a';  // Convert to index (0-25)
          
            // Only need transformation if characters are different
            if (sourceChar != targetChar) {
                // Check if transformation is possible
                if (graph[sourceChar][targetChar] >= INFINITY) {
                    return -1;  // Transformation impossible
                }
                totalCost += graph[sourceChar][targetChar];
            }
        }
      
        return totalCost;
    }
}