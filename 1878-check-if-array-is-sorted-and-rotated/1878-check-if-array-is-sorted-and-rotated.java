class Solution {
    public boolean check(int[] nums) {
        int count = 0;
        int len = nums.length;
        for(int i=1; i<len; i++){
            if(nums[i-1] > nums[i]){
                count++;
            }
        }

        if(nums[len-1] > nums[0]){
            count++;
        }

        return count <= 1;
    }
}