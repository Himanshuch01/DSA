public class solution_Day20 {

  import java.util.*;

class Solution {
    static class Node {
        int cost, r, c, used;
        Node(int a, int b, int c, int d) {
            cost = a; r = b; this.c = c; used = d;
        }
    }

    public int minCost(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int INF = Integer.MAX_VALUE;

        int[][][] dist = new int[m][n][k + 1];
        for (int[][] mat : dist)
            for (int[] row : mat)
                Arrays.fill(row, INF);

        // Collect all cells sorted by value
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                cells.add(new int[]{grid[i][j], i, j});
        cells.sort(Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Node(0, 0, 0, 0));
        dist[0][0][0] = 0;

        int[] dr = {0, 1};
        int[] dc = {1, 0};

        // pointer per teleport usage
        int[] ptr = new int[k + 1];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int cost = cur.cost, r = cur.r, c = cur.c, used = cur.used;

            if (cost > dist[r][c][used]) continue;
            if (r == m - 1 && c == n - 1) return cost;

            // Normal moves
            for (int d = 0; d < 2; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < m && nc < n) {
                    int ncst = cost + grid[nr][nc];
                    if (ncst < dist[nr][nc][used]) {
                        dist[nr][nc][used] = ncst;
                        pq.add(new Node(ncst, nr, nc, used));
                    }
                }
            }

            // Teleport optimization
            if (used < k) {
                while (ptr[used] < cells.size() && cells.get(ptr[used])[0] <= grid[r][c]) {
                    int[] cell = cells.get(ptr[used]++);
                    int nr = cell[1], nc = cell[2];
                    if (cost < dist[nr][nc][used + 1]) {
                        dist[nr][nc][used + 1] = cost;
                        pq.add(new Node(cost, nr, nc, used + 1));
                    }
                }
            }
        }
        return -1;
    }
}



}