class Solution {
    public int repeatedNTimes(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        int ans = 0;

        for(int n : nums){
            if(2 * map.get(n) == nums.length){
                ans = n;
                break;
            }
        }

        return ans;
    }
}