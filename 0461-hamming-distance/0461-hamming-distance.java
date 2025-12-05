class Solution {
    public int hammingDistance(int x, int y) {
        int diff = x ^ y;
        return Integer.bitCount(diff);
    }
}