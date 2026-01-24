class Solution {
    public int minPairSum(int[] nums) {
        // Arrays.sort(nums);
        // int l = 0;
        // int r = nums.length-1;
        // int ans = 0;

        // while(l < r){
        //     ans = Math.max(ans, (nums[l]+nums[r]));
        //     l++; r--;
        // }

        // return ans;

        // The above solution takes O(nlogn) time complexity


        // This solution take O(n) time complexity
        int[] freq = new int[100001];
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        for(int n : nums){
            freq[n]++;
            minVal = Math.min(minVal, n);
            maxVal = Math.max(maxVal, n);
        }

        int low = minVal;
        int high = maxVal;
        int ans = 0;

        while(low <= high){
            if(freq[low] == 0) low++;
            else if(freq[high] == 0) high--;
            else{
                ans = Math.max(ans, low+high);
                int pairsCount = (low == high) ? freq[low]/2 : Math.min(freq[low], freq[high]);

                freq[low] -= pairsCount;
                freq[high] -= pairsCount;
            }
        }

        return ans;
    }
}