class Solution {
    public long maxMatrixSum(int[][] matrix) {

        long maxSum = 0;
        long negatives = 0;
        long minVal = Integer.MAX_VALUE;
        for(int[] r : matrix){
            for(int n : r){
                negatives = n < 0 ? negatives+1 : negatives;
                int absVal = Math.abs(n);
                minVal = Math.min(absVal, minVal);
                maxSum += absVal;
            }
        }

        return negatives%2 == 0 ? maxSum : maxSum - 2*minVal;
    }
}