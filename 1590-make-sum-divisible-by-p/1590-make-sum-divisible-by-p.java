class Solution {
    public int minSubarray(int[] nums, int p) {
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        int rem = (int)(totalSum % p);
        if (rem == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int n = nums.length;
        int minLength = n;
        long prefixSum = 0;

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            int currRem = (int)(prefixSum % p);

            int reminder = (currRem - rem + p) % p;
            Integer prevIndex = map.get(reminder);
            if (prevIndex != null) {
                minLength = Math.min(minLength, i - prevIndex);
                if (minLength == 1) return 1; // early exit
            }

            map.put(currRem, i);
        }

        return minLength == n ? -1 : minLength;
    }
}