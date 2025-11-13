class Solution {
    public int maxOperations(String s) {
        int minOperations = 0;
        int onesCount = 0;

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '1') {
                onesCount++;
            }else if(i == s.length()-1 || s.charAt(i+1) == '1'){
                minOperations += onesCount;
            }
        }
        return minOperations;
    }
}