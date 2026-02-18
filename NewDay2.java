public class NewDay2 {
    class Solution {
        public boolean hasAlternatingBits(int n) {
            int previousBit = -1;

            while (n != 0) {
                int currentBit = n & 1;

                if (previousBit == currentBit) {
                    return false;
                }

                previousBit = currentBit;
                n >>= 1;
            }

            return true;
        }
    }

}
