class Solution {
    public int countPalindromicSubsequence(String s) {
        HashMap<Character, Integer> firstOccurence = new HashMap<Character, Integer>();
        HashMap<Character, Integer> lastOccurence = new HashMap<Character, Integer>();
        int n = s.length();

        for(int i=0; i<n; i++){
            char ch = s.charAt(i);
            if(!firstOccurence.containsKey(ch)){
                firstOccurence.put(ch, i);
            }
            lastOccurence.put(ch, i);
        }

        int count = 0;
        for(char ch : firstOccurence.keySet()){
            int f = firstOccurence.get(ch);
            int l = lastOccurence.get(ch);
            if(f == l) continue;
            HashSet<Character> set = new HashSet<>();
            for(int i=f+1; i<l; i++){
                set.add(s.charAt(i));
            }

            count += set.size();
        }

        return count;
    }
}