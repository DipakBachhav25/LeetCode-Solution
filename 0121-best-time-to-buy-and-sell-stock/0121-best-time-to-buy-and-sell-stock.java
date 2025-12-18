class Solution {
    public int maxProfit(int[] prices) {
        int minVal = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int p : prices){
            minVal = Math.min(minVal, p);
            maxProfit = Math.max(maxProfit, p-minVal);
        }

        return maxProfit;
    }
}