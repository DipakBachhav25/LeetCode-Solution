class Solution {
    public int binaryGap(int n) {
        int maxGap = 0;
        int idx = 0;
        int prevOneIdx = -1;

        while(n != 0){
            if((n & 1) == 1){
                if(prevOneIdx != -1){
                    maxGap = Math.max(maxGap, idx-prevOneIdx);
                }
                prevOneIdx = idx;
            }

            n = n >> 1;
            idx++;
        }

        return maxGap;
    }
}