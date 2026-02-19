class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0;
        int prevLen = 0;
        int currLen = 1;

        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) == s.charAt(i-1)){
                currLen++;
            }else{
                prevLen = currLen;
                currLen = 1;
            }

            if(prevLen >= currLen) ans++;
        }

        return ans;
    }
}