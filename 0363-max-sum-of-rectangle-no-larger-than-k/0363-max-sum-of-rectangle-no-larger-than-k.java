class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int maxSum = Integer.MIN_VALUE;

        for(int l=0; l<n; l++){
            int[] sums = new int[m];

            for(int r = l; r<n; r++){
                for(int i=0; i<m; i++){
                    sums[i] += matrix[i][r];
                }

                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int currSum = 0;
                for(int sum : sums){
                    currSum += sum;
                    Integer t = set.ceiling(currSum-k);
                    if(t != null){
                        maxSum = Math.max(maxSum, currSum-t);
                    }
                    set.add(currSum);
                }
            }
        }
        return maxSum;
    }
}