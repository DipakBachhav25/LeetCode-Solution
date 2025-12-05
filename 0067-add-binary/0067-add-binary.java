class Solution {
    public String addBinary(String a, String b) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i= a.length()-1;
        int j = b.length()-1;

        while(i >= 0 && j >= 0){
            int currA = a.charAt(i) - '0';
            int currB = b.charAt(j) - '0';
            carry += (currA + currB);
            sb.append(carry%2);
            carry /= 2;
            i--; j--;
        }
        while(i >= 0){
            int currA = a.charAt(i) - '0';
            carry += currA;
            sb.append(carry%2);
            carry /= 2;
            i--;
        }
        while(j >= 0){
            int currB = b.charAt(j) - '0';
            carry += currB;
            sb.append((carry%2));
            carry /= 2;
            j--;
        }

        if(carry != 0) sb.append(carry);

        return sb.reverse().toString();
    }
}