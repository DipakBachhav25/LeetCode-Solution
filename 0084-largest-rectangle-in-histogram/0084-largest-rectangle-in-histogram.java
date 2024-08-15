class Solution {
    public int largestRectangleArea(int[] heights) {
        int[] leftSmall = new int[heights.length];
        int[] rightSmall = new int[heights.length];
        Stack<Integer> s = new Stack<>();
        int len = heights.length;

        // Step1: Find left smallest element for each index
        for(int i=0; i<len; i++){
            int curr = heights[i];
            while(! s.isEmpty() && curr <= heights[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                leftSmall[i] = -1;
            } else {
                leftSmall[i] = s.peek();
            }
            s.push(i);
        }
        s = new Stack<>();

        // Step2: Find right smallest element for each index
        for(int i=len-1; i>=0; i--){
            int curr = heights[i];
            while(! s.isEmpty() && curr <= heights[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                rightSmall[i] = len;
            } else {
                rightSmall[i] = s.peek();
            }
            s.push(i);
        }

        // Step3: Find max rectangle for each index and return
        int maxArea = 0;
        for(int i=0; i<len; i++){
            int height = heights[i];
            int width = rightSmall[i] - leftSmall[i] - 1;
            int area = height * width;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}