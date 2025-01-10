class Solution {
    public static int[] countFreq(String s){
        int[] freq = new int[26];
        for(int i=0; i<s.length(); i++){
            freq[s.charAt(i) - 'a']++;
        }

        return freq;
    }

    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] freq = new int[26];
        for(String word : words2){
            int[] temp = countFreq(word);
            for(int i=0; i<26; i++){
                freq[i] = Math.max(temp[i], freq[i]);
            }
        }
        
        List<String> ans = new ArrayList<>();
        for(String word : words1){
            int[] temp = countFreq(word);
            boolean flag = true;
            for(int i=0; i<26; i++){
                if(freq[i] > temp[i]){
                    flag = false;
                    break;
                }
            }

            if(flag){
                ans.add(word);
            }
        }

        return ans;
    }
}