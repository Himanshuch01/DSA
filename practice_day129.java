public class practice_day129 {

        public boolean canReach(int[] arr, int start) {
            // Use BFS to explore all reachable indices
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(start);

            while (!queue.isEmpty()) {
                // Get the current index to process
                int currentIndex = queue.poll();

                // Check if we've reached a position with value 0 (goal)
                if (arr[currentIndex] == 0) {
                    return true;
                }

                // Store the jump distance before marking as visited
                int jumpDistance = arr[currentIndex];

                // Mark current position as visited by setting it to -1
                arr[currentIndex] = -1;

                // Calculate and check both possible jump positions
                for (int nextIndex : List.of(currentIndex + jumpDistance, currentIndex - jumpDistance)) {
                    // Only add valid, unvisited positions to the queue
                    if (nextIndex >= 0 && nextIndex < arr.length && arr[nextIndex] >= 0) {
                        queue.offer(nextIndex);
                    }
                }
            }

            // No path to a position with value 0 was found
            return false;
        }
    }


