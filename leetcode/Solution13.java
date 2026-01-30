public import java.util.*;

class Solution13 {
    public int minCost(int n, int[][] edges) {
        // Build the graph
        // adj[u] contains list of {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            
            // 1. Normal move: u -> v with cost w
            adj.get(u).add(new int[]{v, w});
            
            // 2. Reverse move: v -> u with cost 2*w
            // (If we are at v, and there is an incoming edge u->v, 
            // we can reverse it to go v->u)
            adj.get(v).add(new int[]{u, 2 * w});
        }
        
        // Dijkstra's Algorithm
        // PriorityQueue stores {cost, node}, ordered by cost
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, 0}); // Start at node 0 with cost 0
        
        int[] minCosts = new int[n];
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        minCosts[0] = 0;
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cost = current[0];
            int u = current[1];
            
            if (u == n - 1) {
                return cost;
            }
            
            // Optimization: if we found a shorter way to u already, skip
            if (cost > minCosts[u]) {
                continue;
            }
            
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                
                if (cost + weight < minCosts[v]) {
                    minCosts[v] = cost + weight;
                    pq.offer(new int[]{minCosts[v], v});
                }
            }
        }
        
        return -1;
    }
} 