public class PracticeV {
    class Solution {
        public int minimumEffort(int[][] tasks) {
            // Sort tasks by the difference between actual effort and minimum required effort
            // This ensures we complete tasks with smaller "extra effort needed" first
            // Sorting formula: actualEffort[i] - (minRequired[i] - actualEffort[i]) = 2*actualEffort[i] - minRequired[i]
            Arrays.sort(tasks, (task1, task2) ->
                    (task1[0] - task1[1]) - (task2[0] - task2[1])
            );

            int totalMinimumEffort = 0;  // The minimum initial effort needed
            int currentEffort = 0;       // Current available effort after completing tasks

            // Process each task in sorted order
            for (int[] task : tasks) {
                int actualEffortNeeded = task[0];   // Effort consumed to complete the task
                int minimumEffortRequired = task[1]; // Minimum effort required to start the task

                // If current effort is less than minimum required, we need to add more initial effort
                if (currentEffort < minimumEffortRequired) {
                    int additionalEffortNeeded = minimumEffortRequired - currentEffort;
                    totalMinimumEffort += additionalEffortNeeded;
                    currentEffort = minimumEffortRequired;
                }

                // Consume the actual effort to complete the task
                currentEffort -= actualEffortNeeded;
            }

            return totalMinimumEffort;
        }
    }
}
