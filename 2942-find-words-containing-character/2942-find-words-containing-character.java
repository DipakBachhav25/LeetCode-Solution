class Solution {
    public List<Integer> findWordsContaining(String[] words, char x) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<words.length; i++){
            for(int j=0; j<words[i].length(); j++){
                if(words[i].indexOf(x) != -1){
                    list.add(i);
                    break;
                }
            }
        }

        return list;
    }
}