class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[len-1];
        String longestPrefix = "";

        for(int i=0; i<first.length(); i++){
            if(first.charAt(i) == last.charAt(i)){
                longestPrefix += first.charAt(i);
            } else {
                break;
            }
        }

        return longestPrefix;
    }
}