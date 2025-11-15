class Solution {
    public int climbStairs(int n) {
        int[] fibonacci = new int[n+2];
        fibonacci[0] = 0;
        fibonacci[1] = 1;
        for(int i=2; i<fibonacci.length; i++){
            fibonacci[i] = fibonacci[i-1]+fibonacci[i-2];
        }
        return fibonacci[n+1];
    }
}