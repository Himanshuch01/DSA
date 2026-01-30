class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxHorizontalGap = findMaxConsecutiveGap(hBars);
        int maxVerticalGap = findMaxConsecutiveGap(vBars);

        int maxSquareSide = Math.min(maxHorizontalGap, maxVerticalGap);

        return maxSquareSide * maxSquareSide;
    }

    private int findMaxConsecutiveGap(int[] bars) {
        Arrays.sort(bars);

        int maxConsecutive = 1;
        int currentConsecutive = 1;

        for (int i = 1; i < bars.length; ++i) {
            if (bars[i] == bars[i - 1] + 1) {
                currentConsecutive++;
                maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
            } else {
                currentConsecutive = 1;
            }
        }

        return maxConsecutive + 1;
    }
}
