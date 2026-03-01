class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;

        for(char ch : n.toCharArray()){
            int currDigit = ch-'0';
            maxDigit = Math.max(maxDigit, currDigit);
            if(maxDigit == 9) break;
        }

        return maxDigit;
    }
}