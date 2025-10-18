class Solution {
    public String reverseVowels(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = 0;
        int j = s.length()-1;
        String vowels = "aeiouAEIOU";
        while(i <= j){
            char ithChar = sb.charAt(i);
            char jthChar = sb.charAt(j);
            if(vowels.indexOf(ithChar) != -1 && vowels.indexOf(jthChar) != -1){
                sb.setCharAt(i, jthChar);
                sb.setCharAt(j, ithChar);
                i++; j--;
            }
            if(vowels.indexOf(ithChar) == -1) i++;
            else if(vowels.indexOf(jthChar) == -1) j--;
        }

        return sb.toString();
    }
}