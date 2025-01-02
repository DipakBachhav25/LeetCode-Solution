class Solution {
    public double minimumAverage(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int  i = 0; 
        int j = n-1;
        double minAvg = Double.MAX_VALUE;

        while(i < j){
            double avg = (nums[i] + nums[j]) / 2.0;
            minAvg = Math.min(avg, minAvg);
            i++;
            j--;
        }
        return minAvg;
    }
}