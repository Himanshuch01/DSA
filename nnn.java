public class nnn {
    class Solution {
        public:
        vector<int> kLargest(vector<int>& arr, int k) {
            // Your code here
            priority_queue<int> pq;
            for(int i=0; i<arr.size(); i++){
                pq.push(arr[i]);
            }
            vector<int> ans;
            for(int i=0; i<k; i++){
                ans.push_back(pq.top());
                pq.pop();
            }
            return ans;
        }
    };
}
