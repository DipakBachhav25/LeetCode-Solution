class Solution {
    public int repeatedNTimes(int[] nums) {
        // Map<Integer, Integer> map = new HashMap<>();

        // for(int n : nums){
        //     map.put(n, map.getOrDefault(n, 0)+1);
        // }

        // int ans = 0;

        // for(int n : nums){
        //     if(2 * map.get(n) == nums.length){
        //         ans = n;
        //         break;
        //     }
        // }

        // return ans;

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == nums[i+1] || nums[i] == nums[i+2]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }
}