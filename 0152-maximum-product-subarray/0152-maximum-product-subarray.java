class Solution {
    public int maxProduct(int[] nums) {

        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;
        int leftToRight = 1;
        int rightToLeft = 1;

        for(int i=0; i<n; i++){
            if(leftToRight == 0) leftToRight = 1;
            else if(rightToLeft == 0) rightToLeft = 1;

            leftToRight *= nums[i];
            rightToLeft *= nums[n-i-1];

            maxProduct = Math.max(maxProduct, Math.max(leftToRight, rightToLeft));
        }

        return maxProduct;
    }
}