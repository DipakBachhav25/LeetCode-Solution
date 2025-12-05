class Solution {
    public int reverseBits(int n) {
        int reverseBit = 0;

        for(int i=0; i<32; i++){
            reverseBit |= (n & 1) << (31-i);
            n >>= 1;
        }

        return reverseBit;
    }
}