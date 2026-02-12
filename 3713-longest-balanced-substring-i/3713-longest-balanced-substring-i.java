class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxLen = 0;

        for(int i=0; i<n; i++){
            if(n-i <= maxLen) break;

            int[] freq = new int[26];
            int distCount = 0;
            int maxFreq = 0;

            for(int j=i; j<n; j++){
                int idx = s.charAt(j)-'a';
                if(freq[idx] == 0){
                    distCount++;
                }

                freq[idx]++;
                maxFreq = Math.max(maxFreq, freq[idx]);
                int currLen = j-i+1;

                if(currLen == distCount*maxFreq){
                    maxLen = Math.max(maxLen, currLen);
                }
            }
        }

        return maxLen;
    }
}