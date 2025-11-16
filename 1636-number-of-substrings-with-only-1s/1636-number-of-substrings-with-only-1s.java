class Solution {
    public int numSub(String s) {
        long totalSubString = 0;
        long consecutiveOnes = 0;

        for(char ch : s.toCharArray()){
            if(ch == '1'){
                consecutiveOnes += 1;
                totalSubString += consecutiveOnes;
            }else{
                consecutiveOnes = 0;
            }
        }
        return totalSubString > Integer.MAX_VALUE ? (int)(totalSubString%((int)Math.pow(10, 9)+7)) : (int)totalSubString;
    }
}