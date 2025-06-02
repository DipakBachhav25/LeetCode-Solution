class Solution {
    public int findGCD(int[] nums) {
        int maxNum = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;
        int j;

        for(int i=0; i<nums.length; i++){
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[i]);
        }
        for(j=minNum; j>0; j--){
            if((minNum%j == 0) && (maxNum%j == 0)) break;
        }

        return j;
    }
}