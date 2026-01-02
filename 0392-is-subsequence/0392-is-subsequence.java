class Solution {
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        boolean[] next = new boolean[m + 1];
        boolean[] curr = new boolean[m + 1];
        
        for (int j = 0; j <= m; j++) next[j] = true;
        
        for (int i = n - 1; i >= 0; i--) {
            curr[m] = false;
            for (int j = m - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    curr[j] = next[j + 1];
                } else {
                    curr[j] = curr[j + 1];
                }
            }
            next = curr.clone();
        }
        
        return next[0];
    }
}