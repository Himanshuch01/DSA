public class Day107 {

        public int furthestDistanceFromOrigin(String moves) {

            int leftCount = countCharacter(moves, 'L');
            int rightCount = countCharacter(moves, 'R');
            int wildcardCount = countCharacter(moves, '_');

            return Math.abs(leftCount - rightCount) + wildcardCount;
        }

        private int countCharacter(String str, char targetChar) {
            int count = 0;

            // Iterate through each character in the string
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == targetChar) {
                    count++;
                }
            }

            return count;
        }
    }
//// in cpp
//class Solution {
//    public:
//    int furthestDistanceFromOrigin(string moves) {
//        // Count occurrences of left moves
//        int leftCount = 0;
//        // Count occurrences of right moves
//        int rightCount = 0;
//        // Count occurrences of wildcards (can be either left or right)
//        int wildcardCount = 0;
//
//        // Iterate through each character in the moves string
//        for (char move : moves) {
//            if (move == 'L') {
//                leftCount++;
//            } else if (move == 'R') {
//                rightCount++;
//            } else if (move == '_') {
//                wildcardCount++;
//            }
//        }

        // The maximum distance is achieved by:
        // 1. Taking the absolute difference between left and right moves
        // 2. Adding all wildcards to the direction that increases distance
//        return abs(leftCount - rightCount) + wildcardCount;
//    }
//};
