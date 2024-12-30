class Solution {
    public boolean containsDuplicate(int[] nums) {

        if(nums.length == 1){
            return false;
        }

        Arrays.sort(nums);
        boolean duplicate = false;

        for(int i=1; i<nums.length; i++){
            if(nums[i] == nums[i-1]){
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }
}