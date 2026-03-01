class Solution {
    public int minPartitions(String n) {
        int maxDigit = 0;

        for(int i=0; i<n.length(); i++){
            int currDigit = n.charAt(i)-'0';
            maxDigit = Math.max(maxDigit, currDigit);
            if(maxDigit == 9) break;
        }

        return maxDigit;
    }
}