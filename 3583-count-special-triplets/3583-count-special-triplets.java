class Solution {
    public int specialTriplets(int[] nums) {
        int maxVal = 0;
        for(int n : nums) maxVal = Math.max(maxVal, n);

        int[] leftCounts = new int[2*maxVal+1];
        int[] rightCounts = new int[2*maxVal+1];

        for(int n : nums) rightCounts[n]++;

        long count = 0;
        int MOD = (int) 1e9+7;

        for(int n : nums){
            rightCounts[n]--;
            int targetVal = n*2;
            if(targetVal < rightCounts.length){
                long leftCount = leftCounts[targetVal];
                long rightCount = rightCounts[targetVal];
                count = (count + (leftCount * rightCount) % MOD) % MOD;
            }
            leftCounts[n]++;
        }

        return (int) count;
    }
}