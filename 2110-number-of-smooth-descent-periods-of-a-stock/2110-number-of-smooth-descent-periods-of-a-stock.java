class Solution {
    public long getDescentPeriods(int[] prices) {
        long totalDescentPeriods = 0;
        int n = prices.length;

        // Each element is at least a descent period of length 1
        long currentLength = 1;
        totalDescentPeriods = 1;

        for (int i = 1; i < n; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                currentLength++;
            } else {
                currentLength = 1;
            }
            totalDescentPeriods += currentLength;
        }
        return totalDescentPeriods;
    }
}