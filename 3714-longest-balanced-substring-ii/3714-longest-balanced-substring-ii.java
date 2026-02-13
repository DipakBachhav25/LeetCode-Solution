class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int maxLen = 0;

        int currentRun = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                currentRun++;
            } else {
                currentRun = 1;
            }
            if (currentRun > maxLen) maxLen = currentRun;
        }

        int[] seen = new int[2 * n + 1];
        
        maxLen = Math.max(maxLen, solveTwoOptimized(s, 'a', 'b', 'c', seen, n));
        maxLen = Math.max(maxLen, solveTwoOptimized(s, 'a', 'c', 'b', seen, n));
        maxLen = Math.max(maxLen, solveTwoOptimized(s, 'b', 'c', 'a', seen, n));

        maxLen = Math.max(maxLen, solveThreeOptimized(s));

        return maxLen;
    }

    private int solveTwoOptimized(String s, char c1, char c2, char forbidden, int[] seen, int n) {
        int max = 0;
        Arrays.fill(seen, -2);
        int start = 0;
        
        seen[0 + n] = -1; 

        int diff = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == forbidden) {
                start = i + 1;
                diff = 0;
                seen[0 + n] = i; 
            } else {
                if (c == c1) diff++;
                else if (c == c2) diff--;
                
                int mappedIndex = diff + n;
                if (seen[mappedIndex] >= start - 1) {
                    max = Math.max(max, i - seen[mappedIndex]);
                } else {
                    seen[mappedIndex] = i;
                }
            }
        }
        return max;
    }

    private int solveThreeOptimized(String s) {
        int max = 0;
        int n = s.length();
        Map<Long, Integer> map = new HashMap<>();

        long initialKey = (((long)0) << 32) | (0 & 0xFFFFFFFFL);
        map.put(initialKey, -1);
        
        int a = 0, b = 0, c = 0;
        
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') a++;
            else if (ch == 'b') b++;
            else if (ch == 'c') c++;
            
            int diff1 = a - b;
            int diff2 = b - c;
     
            long key = (((long)diff1) << 32) | (diff2 & 0xFFFFFFFFL);
            
            if (map.containsKey(key)) {
                max = Math.max(max, i - map.get(key));
            } else {
                map.put(key, i);
            }
        }
        return max;
    }
}