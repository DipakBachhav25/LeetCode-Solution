class Solution {
    public int countPartitions(int[] nums, int k) {
        final int MOD = (int) 1e9 + 7;
        int n = nums.length;

        int[] dp = new int[n + 1];
        int[] prefixSum = new int[n + 1];

        dp[0] = 1;
        prefixSum[0] = 1;

        Deque<Integer> minDeque = new ArrayDeque<>();
        Deque<Integer> maxDeque = new ArrayDeque<>();

        int left = 1;

        for (int right = 1; right <= n; right++) {
            int currentNum = nums[right - 1];
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= currentNum) {
                minDeque.pollLast();
            }
            minDeque.addLast(right - 1);
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= currentNum) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right - 1);

            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                if (minDeque.peekFirst() == left - 1) minDeque.pollFirst();
                if (maxDeque.peekFirst() == left - 1) maxDeque.pollFirst();
                left++;
            }

            int previousSum = (left >= 2) ? prefixSum[left - 2] : 0;
            dp[right] = (prefixSum[right - 1] - previousSum + MOD) % MOD;

            prefixSum[right] = (prefixSum[right - 1] + dp[right]) % MOD;
        }

        return dp[n];
    }
}