class Solution {

    public static boolean isVowel(char ch, Set<Character> vowels){
        return vowels.contains(Character.toLowerCase(ch));
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int n = words.length;
        int[] prefixSum = new int[n+1];

        for(int i=0; i<n; i++){
            String word = words[i];

            if(isVowel(word.charAt(0), vowels) && isVowel(word.charAt(word.length()-1), vowels)){
                prefixSum[i+1] = prefixSum[i]+1;
            } else {
                prefixSum[i+1] = prefixSum[i];
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int[] q : queries){
            int l = q[0];
            int r = q[1];
            res.add(prefixSum[r+1] - prefixSum[l]);
        }

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}