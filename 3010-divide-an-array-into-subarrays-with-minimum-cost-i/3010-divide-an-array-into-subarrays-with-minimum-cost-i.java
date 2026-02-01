class Solution {
    public int minimumCost(int[] nums) {
        int initialCost = nums[0];
        int min1 = 100;
        int min2 = 100;

        for(int i=1; i<nums.length; i++){
            if(nums[i] < min1){
                min2 = min1;
                min1 = nums[i];
            }else if(nums[i] < min2){
                min2 = nums[i];
            }
        }

        return initialCost+min1+min2;
    }
}