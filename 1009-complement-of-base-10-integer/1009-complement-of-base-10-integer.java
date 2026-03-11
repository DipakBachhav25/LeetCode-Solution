class Solution {
    public int bitwiseComplement(int n) {
        if(n == 0) return 1;

        int bitMask = 1;

        while(bitMask < n) bitMask = (bitMask << 1) | 1;

        return n^bitMask;
    }
}