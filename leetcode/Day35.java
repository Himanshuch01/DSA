public class Day35  {
    public int longestBalanced(String s) {
        int n = s.length();
        int ans = 1; // at least one character is always balanced

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;

                int min = Integer.MAX_VALUE;
                int max = 0;

                for (int f : freq) {
                    if (f > 0) {
                        min = Math.min(min, f);
                        max = Math.max(max, f);
                    }
                }

                if (min == max) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
}

