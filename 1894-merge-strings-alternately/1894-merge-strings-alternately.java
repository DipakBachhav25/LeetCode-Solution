class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        String s = "";
        int i=0, j=0;
        while(i<m && j<n){
            s += word1.charAt(i++);
            s += word2.charAt(j++);
        }

        while(i<m){
            s += word1.charAt(i++);
        }

        while(j<n){
            s += word2.charAt(j++);
        }

        return s;
    }
}