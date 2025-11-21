class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int count = 0;

        int[] firstIdx = new int[26];
        int[] lastIdx = new int[26];
        Arrays.fill(firstIdx, -1);
        Arrays.fill(lastIdx, -1);

        for(int i=0; i<n; i++){
            int idx = s.charAt(i)-'a';
            if(firstIdx[idx] == -1) firstIdx[idx] = i;
            lastIdx[idx] = i;
        }

        for(int c=0; c<26; c++){
            if(((firstIdx[c] != -1) && (lastIdx[c] != -1)) && (firstIdx[c] < lastIdx[c])){
                Set<Character> set = new HashSet<>();
                for(int i=firstIdx[c]+1; i<lastIdx[c]; i++){
                    set.add(s.charAt(i));
                }
                count += set.size();
            }
        }
        return count;
    }
}