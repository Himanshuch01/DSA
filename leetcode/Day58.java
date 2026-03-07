public class Day58 {

        public int minFlips(String s) {
            int stringLength = s.length();

            // Target pattern starting with "01" (alternating 0 and 1)
            String alternatingPattern = "01";

            // Count mismatches when comparing string with "0101..." pattern
            int mismatchCount = 0;
            for (int i = 0; i < stringLength; ++i) {
                // Use bitwise AND to alternate between index 0 and 1 of pattern
                // When i is even: i & 1 = 0 (compare with '0')
                // When i is odd: i & 1 = 1 (compare with '1')
                if (s.charAt(i) != alternatingPattern.charAt(i & 1)) {
                    ++mismatchCount;
                }
            }

            // Calculate minimum flips needed for both patterns:
            // - mismatchCount: flips needed for "0101..." pattern
            // - (stringLength - mismatchCount): flips needed for "1010..." pattern
            int minFlipsNeeded = Math.min(mismatchCount, stringLength - mismatchCount);

            // Simulate moving characters from beginning to end one by one
            // This handles the Type-1 operation where we can move front character to back
            for (int i = 0; i < stringLength; ++i) {
                // Remove contribution of character at position i from original position
                if (s.charAt(i) != alternatingPattern.charAt(i & 1)) {
                    --mismatchCount;
                }

                // Add contribution of character at position i when moved to end
                // New position would be (i + stringLength), so we check pattern at that position
                if (s.charAt(i) != alternatingPattern.charAt((i + stringLength) & 1)) {
                    ++mismatchCount;
                }

                // Update minimum flips considering both alternating patterns
                minFlipsNeeded = Math.min(minFlipsNeeded, Math.min(mismatchCount, stringLength - mismatchCount));
            }

            return minFlipsNeeded;
        }
    }


