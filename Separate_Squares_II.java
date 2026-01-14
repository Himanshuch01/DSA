import java.util.*;

class Solution {
    // Arrays for Segment Tree
    private int[] count;
    private long[] length;
    private List<Integer> X;

    public double separateSquares(int[][] squares) {
        // 1. Coordinate Compression for X-axis
        Set<Integer> xSet = new HashSet<>();
        for (int[] sq : squares) {
            xSet.add(sq[0]);
            xSet.add(sq[0] + sq[2]);
        }
        X = new ArrayList<>(xSet);
        Collections.sort(X);
        Map<Integer, Integer> xMap = new HashMap<>();
        for (int i = 0; i < X.size(); i++) xMap.put(X.get(i), i);

        // 2. Create and Sort Events (Sweep Line along Y-axis)
        List<Event> events = new ArrayList<>();
        for (int[] sq : squares) {
            int x1 = xMap.get(sq[0]);
            int x2 = xMap.get(sq[0] + sq[2]); // Exclusive end index in X array
            events.add(new Event(sq[1], 1, x1, x2));
            events.add(new Event(sq[1] + sq[2], -1, x1, x2));
        }
        events.sort((a, b) -> Integer.compare(a.y, b.y));

        // 3. Initialize Segment Tree
        int n = X.size();
        count = new int[4 * n];
        length = new long[4 * n];

        // 4. Sweep Line to calculate areas
        long totalArea = 0;
        // Store strips: [y_start, strip_height, active_width]
        List<long[]> strips = new ArrayList<>(); 
        
        for (int i = 0; i < events.size(); i++) {
            // Process the interval before this event
            if (i > 0) {
                long h = events.get(i).y - events.get(i - 1).y;
                if (h > 0) {
                    long w = length[1]; // Root of segment tree holds total active width
                    long stripArea = w * h;
                    totalArea += stripArea;
                    strips.add(new long[]{events.get(i - 1).y, h, w});
                }
            }
            // Update active squares
            update(1, 0, n - 1, events.get(i).x1, events.get(i).x2, events.get(i).type);
        }

        // 5. Find the split line
        double target = totalArea / 2.0;
        double currentArea = 0;

        for (long[] strip : strips) {
            long yStart = strip[0];
            long h = strip[1];
            long w = strip[2];
            double stripArea = (double) w * h;

            if (currentArea + stripArea >= target) {
                // Determine how much height into this strip is needed
                double neededArea = target - currentArea;
                return yStart + (neededArea / w);
            }
            currentArea += stripArea;
        }

        return events.get(0).y; // Fallback
    }

    // Segment Tree Update
    // Node covers interval X[tl] to X[tr]
    private void update(int node, int tl, int tr, int l, int r, int val) {
        if (l >= r) return;
        if (l == tl && r == tr) {
            count[node] += val;
        } else {
            int tm = (tl + tr) / 2;
            update(2 * node, tl, tm, l, Math.min(r, tm), val);
            update(2 * node + 1, tm, tr, Math.max(l, tm), r, val);
        }

        // Update length covered by this node
        if (count[node] > 0) {
            length[node] = (long) X.get(tr) - X.get(tl);
        } else {
            // If leaf, length is 0, else sum of children
            if (tr - tl == 1) length[node] = 0;
            else length[node] = length[2 * node] + length[2 * node + 1];
        }
    }

    // Minimalist Helper Class
    private static class Event {
        int y, type, x1, x2;
        Event(int y, int type, int x1, int x2) {
            this.y = y; this.type = type; this.x1 = x1; this.x2 = x2;
        }
    }
}