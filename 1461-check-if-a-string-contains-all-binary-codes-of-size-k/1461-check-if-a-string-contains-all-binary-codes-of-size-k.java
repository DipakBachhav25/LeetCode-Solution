class Solution {
    public boolean hasAllCodes(String s, int k) {
        int possibleCodes = 1 << k;
        boolean[] seen = new boolean[possibleCodes];

        int val = 0;
        int allOnes = possibleCodes-1;

        for(int i=0; i<s.length(); i++){
            val = ((val << 1) & allOnes) | (s.charAt(i)-'0');
            if(i >= k-1){
                if(!seen[val]){
                    seen[val] = true;
                    possibleCodes--;

                    if(possibleCodes == 0) return true;
                }
            }
        }

        return false;
    }
}