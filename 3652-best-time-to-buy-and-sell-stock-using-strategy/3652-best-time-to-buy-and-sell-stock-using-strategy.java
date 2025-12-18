class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        int half = k / 2;

        long[] prefOrig = new long[n + 1];
        long[] prefPrice = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefOrig[i + 1] = prefOrig[i] + (long) strategy[i] * prices[i];
            prefPrice[i + 1] = prefPrice[i] + prices[i];
        }

        long originalProfit = prefOrig[n];
        long bestDelta = 0;

        for (int l = 0; l + k <= n; l++) {
            int mid = l + half;
            int r = l + k;

            long firstHalfOrig = prefOrig[mid] - prefOrig[l];
            long secondHalfOrig = prefOrig[r] - prefOrig[mid];
            long secondHalfPrice = prefPrice[r] - prefPrice[mid];

            long delta = -firstHalfOrig
                       + (secondHalfPrice - secondHalfOrig);

            if (delta > bestDelta) {
                bestDelta = delta;
            }
        }

        return originalProfit + bestDelta;
    }
}