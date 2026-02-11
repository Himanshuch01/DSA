public class Day34 {
}
import java.util.Arrays;

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        // Segment Tree to store the difference (Distinct Even - Distinct Odd)
        // for all start indices 'i' relative to the current end index 'j'.
        SegmentTree st = new SegmentTree(n);

        // Stores the last seen position of each number.
        // Constraints say nums[i] <= 10^5.
        int[] lastPos = new int[100005];
        Arrays.fill(lastPos, -1);

        int maxLen = 0;

        for (int j = 0; j < n; j++) {
            int val = nums[j];

            // 1. Activate the current index 'j' in the Segment Tree.
            // Initially, all indices are set to a large value (INF).
            // We set index j to 0 effectively by subtracting INF.
            // We do this before the range update so the current number counts itself correctly.
            st.activate(j);

            // 2. Determine the update value based on parity.
            // Even adds 1 to the diff (E - O), Odd subtracts 1.
            int diff = (val % 2 == 0) ? 1 : -1;

            // 3. Update the range of start indices affected by this new number.
            // The distinct count changes for subarrays starting after the previous occurrence of 'val'.
            int prev = lastPos[val];
            st.update(1, 0, n - 1, prev + 1, j, diff);

            // 4. Update the last position of the current value.
            lastPos[val] = j;

            // 5. Query the Segment Tree for the leftmost index 'i' where value is 0.
            // If found, update the maximum length.
            int leftmostIndex = st.findFirstZero(1, 0, n - 1);
            if (leftmostIndex != -1) {
                maxLen = Math.max(maxLen, j - leftmostIndex + 1);
            }
        }

        return maxLen;
    }

    // Segment Tree Implementation
    static class SegmentTree {
        int n;
        int[] min;
        int[] max;
        int[] lazy;
        static final int INF = (int) 1e9;

        public SegmentTree(int n) {
            this.n = n;
            this.min = new int[4 * n];
            this.max = new int[4 * n];
            this.lazy = new int[4 * n];
            // Initialize tree with INF (representing inactive indices)
            build(1, 0, n - 1);
        }

        private void build(int node, int start, int end) {
            if (start == end) {
                min[node] = INF;
                max[node] = INF;
                return;
            }
            int mid = (start + end) / 2;
            build(2 * node, start, mid);
            build(2 * node + 1, mid + 1, end);
            pushUp(node);
        }

        // Set index idx to 0 (conceptually) by subtracting INF
        public void activate(int idx) {
            update(1, 0, n - 1, idx, idx, -INF);
        }

        public void update(int node, int start, int end, int l, int r, int val) {
            if (l > end || r < start) return;

            if (l <= start && end <= r) {
                apply(node, val);
                return;
            }

            pushDown(node);
            int mid = (start + end) / 2;
            update(2 * node, start, mid, l, r, val);
            update(2 * node + 1, mid + 1, end, l, r, val);
            pushUp(node);
        }

        // Find the leftmost index in the range [0, n-1] where the value is 0
        public int findFirstZero(int node, int start, int end) {
            // Pruning: if 0 is not within the range [min, max], it doesn't exist here.
            if (min[node] > 0 || max[node] < 0) {
                return -1;
            }

            if (start == end) {
                return (min[node] == 0) ? start : -1;
            }

            pushDown(node);
            int mid = (start + end) / 2;

            // Try left child first to find the leftmost index
            int res = findFirstZero(2 * node, start, mid);
            if (res != -1) return res;

            // If not found in left, try right
            return findFirstZero(2 * node + 1, mid + 1, end);
        }

        private void apply(int node, int val) {
            min[node] += val;
            max[node] += val;
            lazy[node] += val;
        }

        private void pushDown(int node) {
            if (lazy[node] != 0) {
                apply(2 * node, lazy[node]);
                apply(2 * node + 1, lazy[node]);
                lazy[node] = 0;
            }
        }

        private void pushUp(int node) {
            min[node] = Math.min(min[2 * node], min[2 * node + 1]);
            max[node] = Math.max(max[2 * node], max[2 * node + 1]);
        }
    }
}