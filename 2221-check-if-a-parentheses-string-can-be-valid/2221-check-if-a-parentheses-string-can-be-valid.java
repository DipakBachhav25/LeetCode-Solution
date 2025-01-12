class Solution {
    public boolean canBeValid(String s, String locked) {
        if(s.length()%2 == 1) return false;

        int open=0, close=0;
        for(int i=0; i<s.length(); i++){
            if(locked.charAt(i) == '1'){
                if(s.charAt(i) == '(') open++;
                else close++;
            } else {
                open++;
            }

            if(close > open) return false;
        }

        open=0; 
        close=0;
        for(int i=s.length()-1; i>=0; i--){
            if(locked.charAt(i) == '1'){
                if(s.charAt(i) == ')') close++;
                else open++;
            } else {
                close++;
            }

            if(close < open) return false;
        }

        return true;
    }
}