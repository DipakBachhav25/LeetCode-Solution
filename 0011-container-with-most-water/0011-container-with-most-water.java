class Solution {
    public int maxArea(int[] height) {
        int len = height.length;

        if(len == 1){
            return 0;
        }
        int leftPointer = 0;
        int rightPointer = len-1;
        int maxWater = 0;

        while(leftPointer < rightPointer){
            int minHeight = Math.min(height[leftPointer], height[rightPointer]);
            int width = rightPointer - leftPointer;
            int water = minHeight * width;
            maxWater = Math.max(water, maxWater);

            if(height[leftPointer] < height[rightPointer]){
                leftPointer++;
            } else {
                rightPointer--;
            }
        }
        return maxWater;
    }
}