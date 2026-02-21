class Solution {
    public int countPrimeSetBits(int left, int right) {
        int primeCounts = 0;

        for(int i=left; i<=right; i++){
            int setBitCount = Integer.bitCount(i);
            if(((665772 >> setBitCount) & 1) == 1) primeCounts++;
        }

        return primeCounts;
    }
}