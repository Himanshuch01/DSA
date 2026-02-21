public class NewDay5 {
    class Solution {
        // Set containing all prime numbers up to 19 (maximum possible bit count for 32-bit integer)
        private static final Set<Integer> PRIME_NUMBERS = Set.of(2, 3, 5, 7, 11, 13, 17, 19);

        /**
         * Counts how many integers in the range [left, right] have a prime number of set bits
         * in their binary representation.
         *
         * @param left  the starting value of the range (inclusive)
         * @param right the ending value of the range (inclusive)
         * @return the count of numbers with prime number of set bits
         */
        public int countPrimeSetBits(int left, int right) {
            int primeSetBitCount = 0;

            // Iterate through each number in the given range
            for (int currentNumber = left; currentNumber <= right; currentNumber++) {
                // Get the count of set bits (1s) in binary representation
                int setBitCount = Integer.bitCount(currentNumber);

                // Check if the bit count is a prime number
                if (PRIME_NUMBERS.contains(setBitCount)) {
                    primeSetBitCount++;
                }
            }

            return primeSetBitCount;
        }
    }
}
