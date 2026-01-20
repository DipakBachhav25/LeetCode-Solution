class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for(int i=0; i<n; i++){
            while((1 <= nums[i] && nums[i] <= n) && (nums[i] != nums[nums[i]-1])){
                int t = nums[i];
                nums[i] = nums[nums[i]-1];
                nums[t-1] = t;
            }
        }

        for(int i=1; i<=n; i++){
            if(i != nums[i-1]) return i;
        }

        return n+1;
    }
}