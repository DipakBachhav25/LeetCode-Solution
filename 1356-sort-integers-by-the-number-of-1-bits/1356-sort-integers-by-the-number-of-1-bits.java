class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;

        for(int i=0; i<n; i++){
            int bitCount = Integer.bitCount(arr[i]);
            arr[i] = (bitCount << 16) | arr[i];
        }

        Arrays.sort(arr);

        for(int i=0; i<n; i++){
            arr[i] &= 0xFFFF;
        }

        return arr;
    }
}