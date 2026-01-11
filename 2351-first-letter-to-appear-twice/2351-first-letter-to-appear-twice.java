class Solution {
    public char repeatedCharacter(String s) {
        int bitMask = 0;
        char ans = ' ';
        for(char ch : s.toCharArray()){
            int b = ch - 'a';
            if((bitMask & (1 << b)) > 0){
                ans = ch;
                break;
            }
            bitMask |= (1 << b);
        }

        return ans;
    }
}