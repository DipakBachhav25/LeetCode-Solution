class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int countOnes = 0;
        for(int num : nums) if(num == 1) countOnes++;

        if(countOnes > 0) return n-countOnes;

        int minLengthSubArr = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            int currGCD = nums[i];
            for(int j=i+1; j<n; j++){
                currGCD = findGCD(currGCD, nums[j]);
                if(currGCD == 1){
                    minLengthSubArr = Math.min(minLengthSubArr, j-i+1);
                    break;
                }
            }
        }

        return minLengthSubArr==Integer.MAX_VALUE ? -1:(minLengthSubArr-1)+(n-1); 
    }

    private int findGCD(int x, int y){
        while(y != 0){
            int tmp = y;
            y = x%y;
            x = tmp;
        }
        return x;
    }
}