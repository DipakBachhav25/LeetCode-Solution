class Solution {
    public int numSteps(String s) {
        int count = 0;
        int carry = 0;

        for(int i=s.length()-1; i>=1; i--){
            int lsb = s.charAt(i)-'0'+carry;

            if(lsb == 0){
                count++;
                carry = 0;
            }else if(lsb == 1){
                count += 2;
                carry = 1;
            }else{
                count++;
                carry = 1;
            }
        }

        return carry == 1 ? ++count : count;
    }
}