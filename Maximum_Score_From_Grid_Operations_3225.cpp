
    //cpp solution-
    #include <vector>
#include <algorithm>

    using namespace std;

    class Solution {
        public:
        long long maximumScore(vector<vector<int>>& grid) {
            int n = grid.size();

            // prefix[j][i] stores the sum of grid[0...i-1][j]
            vector<vector<long long>> prefix(n, vector<long long>(n + 1, 0));
            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    prefix[j][i + 1] = prefix[j][i] + grid[i][j];
                }
            }

            // Helper lambda to get the sum of grid[A...B][j]
            auto getSum = [&](int j, int A, int B) -> long long {
                if (A > B) return 0;
                B = min(B, n - 1);
                if (A >= n) return 0;
                return prefix[j][B + 1] - prefix[j][A];
            };

        const long long INF = 1e18;

            // dp[p][c] = max score for columns processed so far,
            // with previous column height 'p' and current column height 'c'
            vector<vector<long long>> dp(n + 1, vector<long long>(n + 1, -INF));

            // Base case before processing column 0
            // (conceptual column -1 has a height of 0)
            for (int c = 0; c <= n; c++) {
                dp[0][c] = 0;
            }

            for (int j = 0; j < n; j++) {
                vector<vector<long long>> dp_next(n + 1, vector<long long>(n + 1, -INF));

                for (int c = 0; c <= n; c++) {
                    // Precompute prefix maximums for the current 'c'
                    vector<long long> pref_max(n + 1, -INF);
                    pref_max[0] = dp[0][c];
                    for (int p = 1; p <= n; p++) {
                        pref_max[p] = max(pref_max[p - 1], dp[p][c]);
                    }

                    // Precompute suffix maximums for the current 'c'
                    vector<long long> suff_max(n + 1, -INF);
                    for (int p = n - 1; p >= 0; p--) {
                        long long val = dp[p + 1][c];
                        if (val != -INF) {
                            val += getSum(j, c, p);
                        }
                        suff_max[p] = max(suff_max[p + 1], val);
                    }

                    // Transition to the next column's height 'nxt'
                    for (int nxt = 0; nxt <= n; nxt++) {
                        long long v1 = -INF;
                        if (pref_max[nxt] != -INF) {
                            // Case 1: p <= nxt, so max(p, nxt) is nxt
                            v1 = pref_max[nxt] + getSum(j, c, nxt - 1);
                        }

                        // Case 2: p > nxt, so max(p, nxt) is p
                        long long v2 = suff_max[nxt];

                        dp_next[c][nxt] = max(v1, v2);
                    }
                }
                dp = dp_next;
            }

            // The conceptual column 'n' falls outside the grid, so its height must be 0
            long long ans = -INF;
            for (int c = 0; c <= n; c++) {
                ans = max(ans, dp[c][0]);
            }

            return ans;
        }
    };


