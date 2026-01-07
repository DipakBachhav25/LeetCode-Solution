class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int idx = 0;

        int l = 0, r = n-1;

        while(l <= r){
            if(Math.abs(nums[l]) > Math.abs(nums[r])){
                ans[idx] = nums[l] * nums[l];
                l++;
            }else{
                ans[idx] = nums[r] * nums[r];
                r--;
            }
            idx++;
        }

        l = 0; r = n-1;

        while(l < r){
            int t = ans[l];
            ans[l] = ans[r];
            ans[r] = t;
            l++; r--;
        }

        return ans;
    }
}