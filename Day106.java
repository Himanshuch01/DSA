class Solution {
    public:
    vector<long long> distance(vector<int>& nums) {
        int n = nums.size();
        vector<long long> result(n);

        // Group indices by their values
        unordered_map<int, vector<int>> valueToIndices;
        for (int i = 0; i < n; ++i) {
            valueToIndices[nums[i]].push_back(i);
        }

        // Process each group of same values
        for (auto& [value, indices] : valueToIndices) {
            int groupSize = indices.size();

            // Initialize left and right sums for the first element in the group
            // leftSum: sum of distances to all elements on the left
            long long leftSum = 0;
            // rightSum: sum of distances to all elements on the right
            // Initially, all elements are on the right of index 0
            long long rightSum = -1LL * groupSize * indices[0];
            for (int index : indices) {
                rightSum += index;
            }

            // Calculate distance sum for each element in the group
            for (int i = 0; i < groupSize; ++i) {
                // Total distance is sum of left distances and right distances
                result[indices[i]] = leftSum + rightSum;

                // Update left and right sums for the next element
                if (i + 1 < groupSize) {
                    int gap = indices[i + 1] - indices[i];
                    // Elements on the left (including current) will increase distance by gap
                    leftSum += gap * (i + 1);
                    // Elements on the right will decrease distance by gap
                    rightSum -= gap * (groupSize - i - 1);
                }
            }
        }

        return result;
    }
};

//java
class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] result = new long[n];

        // Group indices by their values
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Calculate sum of distances for each group of same values
        for (List<Integer> indices : valueToIndices.values()) {
            int groupSize = indices.size();

            // Initialize left sum (sum of distances to elements on the left)
            long leftSum = 0;

            // Initialize right sum (sum of distances to elements on the right)
            // Initially, all elements are on the right of index 0
            long rightSum = -1L * groupSize * indices.get(0);
            for (int index : indices) {
                rightSum += index;
            }

            // Process each position in the group
            for (int i = 0; i < groupSize; i++) {
                // Total distance for current position is sum of left and right distances
                result[indices.get(i)] = leftSum + rightSum;

                // Update left and right sums for next iteration
                if (i + 1 < groupSize) {
                    int currentIndex = indices.get(i);
                    int nextIndex = indices.get(i + 1);
                    int gap = nextIndex - currentIndex;

                    // Moving from position i to i+1:
                    // - Elements 0 to i are now on the left, distance increases by gap * (i+1)
                    leftSum += gap * (i + 1L);

                    // - Elements i+1 to m-1 are on the right, distance decreases by gap * (m-i-1)
                    rightSum -= gap * (groupSize - i - 1L);
                }
            }
        }

        return result;
    }
}
