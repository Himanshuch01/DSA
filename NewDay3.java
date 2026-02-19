public class NewDay3 {
    class Solution {
        public int countBinarySubstrings(String s) {
            // Initialize variables
            int index = 0;
            int length = s.length();

            // List to store counts of consecutive groups of same characters
            List<Integer> groupCounts = new ArrayList<>();

            // Count consecutive groups of 0s and 1s
            while (index < length) {
                int count = 1;

                // Count consecutive same characters
                while (index + 1 < length && s.charAt(index + 1) == s.charAt(index)) {
                    index++;
                    count++;
                }

                // Add the count of current group to list
                groupCounts.add(count);
                index++;
            }

            // Calculate result by taking minimum of adjacent group counts
            int result = 0;

            // For each pair of adjacent groups, add the minimum count
            // This represents the maximum valid substrings between those groups
            for (int i = 1; i < groupCounts.size(); i++) {
                result += Math.min(groupCounts.get(i - 1), groupCounts.get(i));
            }

            return result;
        }
    }
}
