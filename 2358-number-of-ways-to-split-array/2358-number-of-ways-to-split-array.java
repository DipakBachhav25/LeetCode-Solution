class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        long l = 0;
        long r = 0;
        long sum = 0;
        int count = 0;

        for(int i=0; i<n; i++){
            sum += nums[i];
        }

        for(int i=0; i<n-1; i++){
            l += nums[i];
            r = sum - l;
            if(l >= r) count++;
        }

        return count;
    }
}