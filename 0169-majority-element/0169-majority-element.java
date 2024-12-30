class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int halfLength = nums.length / 2;
        return nums[halfLength];
    }
}