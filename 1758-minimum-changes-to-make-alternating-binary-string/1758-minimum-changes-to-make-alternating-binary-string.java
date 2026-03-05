class Solution {
    public int minOperations(String s) {
        return Math.min(helper(s, '0'), helper(s, '1'));
    }

    public static int helper(String s, char prevChar){
        int count = 0;

        for(int i=0; i<s.length(); i++){
            char curr = s.charAt(i);
            if(curr == prevChar) {
                count++;
            }
            prevChar = prevChar == '0' ? '1':'0';
        }

        return count;
    }
}