class Solution {
    public List<String> stringMatching(String[] words) {
        HashSet<String> wordSet = new HashSet<>();
        List<String> ans = new ArrayList<>();

        for(String word : words){
            wordSet.add(word);
        }

        for(String word : words){
            for(int i=0; i<word.length(); i++){
                for(int j=i+1; j<=word.length(); j++){
                    String subString = word.substring(i, j);
                    if(!subString.equals(word) && wordSet.contains(subString)){
                        if(!ans.contains(subString)) ans.add(subString);
                    }
                }
            }
        }

        return ans;
    }
}