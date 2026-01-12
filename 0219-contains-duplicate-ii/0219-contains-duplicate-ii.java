class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        int i = 0;

        while(i < n){
            int j = i+1;
            while(j < n && Math.abs(i-j) <=k){
                if(nums[i] == nums[j]) return true;
                j++;
            }
            i++;
        }

        return false;
    }
}