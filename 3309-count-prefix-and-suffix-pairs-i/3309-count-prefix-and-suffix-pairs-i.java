class Solution {

    public static boolean isPrefixAndSuffix(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        if(m > n) return false;

        for(int i=0; i<m; i++){
            if(s1.charAt(i) != s2.charAt(i) ||  s1.charAt(i) != s2.charAt(n-m+i)){
                return false;
            }
        }

        return true;
    }

    public int countPrefixSuffixPairs(String[] words) {
        int n = words.length;
        int count = 0;
        for(int i = 0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isPrefixAndSuffix(words[i], words[j])){
                    count++;
                }
            }
        }
        return count;
    }
}