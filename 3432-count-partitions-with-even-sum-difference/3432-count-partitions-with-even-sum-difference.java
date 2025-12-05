class Solution {
    public int countPartitions(int[] nums) {
        int totalSum = 0;
        for(int n : nums) totalSum += n;

        int count = 0;
        int leftSum = 0;
        for(int i=0; i<nums.length-1; i++){
            leftSum += nums[i];
            int rightSum = totalSum - leftSum;
            if(Math.abs((leftSum-rightSum)) % 2 == 0) count++;
        }

        return count;
    }
}