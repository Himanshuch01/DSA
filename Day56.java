public class Day56 {

        public int minOperations(String s) {
            // Count operations needed to make string start with '0' (pattern: 010101...)
            int operationsForPattern0 = 0;
            int stringLength = s.length();

            // Check each character against the expected pattern starting with '0'
            for (int i = 0; i < stringLength; i++) {
                // For pattern starting with '0':
                // - Even indices (0, 2, 4, ...) should have '0'
                // - Odd indices (1, 3, 5, ...) should have '1'
                // Using bitwise AND: i & 1 gives 0 for even indices, 1 for odd indices
                char expectedChar = "01".charAt(i & 1);

                // If current character doesn't match expected, we need an operation
                if (s.charAt(i) != expectedChar) {
                    operationsForPattern0++;
                }
            }

            // The other pattern (starting with '1') would need (stringLength - operationsForPattern0) operations
            // Return the minimum of the two patterns
            return Math.min(operationsForPattern0, stringLength - operationsForPattern0);
        }
    }

