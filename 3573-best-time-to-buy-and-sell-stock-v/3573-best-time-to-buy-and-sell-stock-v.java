class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long NEG = Long.MIN_VALUE / 4;

        long[] profit = new long[k + 1];
        long[] longHold = new long[k + 1];
        long[] shortHold = new long[k + 1];

        Arrays.fill(longHold, NEG);
        Arrays.fill(shortHold, NEG);

        longHold[0] = -prices[0];
        shortHold[0] = prices[0];

        for (int day = 1; day < n; day++) {
            int price = prices[day];

            for (int t = k; t >= 1; t--) {
                profit[t] = Math.max(
                    profit[t],
                    Math.max(longHold[t - 1] + price, shortHold[t - 1] - price)
                );
                longHold[t - 1] = Math.max(longHold[t - 1], profit[t - 1] - price);
                shortHold[t - 1] = Math.max(shortHold[t - 1], profit[t - 1] + price);
            }
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, profit[t]);
        }
        return ans;
    }
}