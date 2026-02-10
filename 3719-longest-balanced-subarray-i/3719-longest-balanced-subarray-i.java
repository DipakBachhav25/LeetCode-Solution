class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        int[] visited = new int[100001];
        int flag = 0;

        for(int i=0; i<n; i++){
            flag++;
            int evenCount = 0;
            int oddCount = 0;

            for(int j=i; j<n; j++){
                int curr = nums[j];

                if(visited[curr] != flag){
                    visited[curr] = flag;
                    if(curr%2 == 0) evenCount++;
                    else oddCount++;
                }

                if(evenCount == oddCount){
                    maxLen = Math.max(maxLen, j-i+1);
                }
            }
        }

        return maxLen;
    }
}