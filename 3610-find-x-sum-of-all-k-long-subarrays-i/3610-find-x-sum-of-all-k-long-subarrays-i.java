class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int size = nums.length;
        int[] ans = new int[size-k+1];
        for(int i=0; i<ans.length; i++){
            ans[i] = xSum(nums, i, i+k-1, x);
        }
        return ans;
    }

    public int xSum(int[] nums, int start, int end, int x){
        int[] freq = new int[50+1];
        int distinctElement = 0;
        int sum = 0;

        for(int i=start; i<=end; i++){
            sum += nums[i];
            if(freq[nums[i]] == 0) distinctElement++;
            freq[nums[i]]++;
        }

        if(distinctElement < x) return sum;
        sum = 0;

        for(int i=0; i<x; i++){
            int maxNum = -1;
            int maxFreq = -1;

            for(int j=50; j>=1; j--){
                if(freq[j] > maxFreq){
                    maxFreq = freq[j];
                    maxNum = j;
                }
            }

            if(maxNum != -1){
                sum += maxNum*maxFreq;
                freq[maxNum] = 0;
            }
        }
        return sum;
    }

}