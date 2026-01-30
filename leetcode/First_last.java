// User function Template for Java

class GFG {
    ArrayList<Integer> find(int arr[], int x) {
        ArrayList<Integer> result = new ArrayList<>();
        
        int first = firstOccurrence(arr, x);
        int last = lastOccurrence(arr, x);

        result.add(first);
        result.add(last);

        return result;
    }

    // Find first occurrence of x
    private int firstOccurrence(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                ans = mid;
                high = mid - 1;   // move left
            } 
            else if (arr[mid] < x) {
                low = mid + 1;
            } 
            else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Find last occurrence of x
    private int lastOccurrence(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                ans = mid;
                low = mid + 1;   // move right
            } 
            else if (arr[mid] < x) {
                low = mid + 1;
            } 
            else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
