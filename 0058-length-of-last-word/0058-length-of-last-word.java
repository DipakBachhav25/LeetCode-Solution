class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        int lastLength = 0;
        for(int i=words.length-1; i>=0; i--){
            if(words[i] != " "){
                lastLength = words[i].length();
                break;
            }
        }

        return lastLength;
    }
}