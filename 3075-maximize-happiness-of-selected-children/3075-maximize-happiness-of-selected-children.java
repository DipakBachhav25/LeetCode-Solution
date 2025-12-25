class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {

        Arrays.sort(happiness);
        long maxSum = 0;
        int n = happiness.length;

        for (int i = 0; i < k; i++) {
            int adjusted = happiness[n - 1 - i] - i;
            maxSum += Math.max(adjusted, 0);
        }
        return maxSum;

    }
}