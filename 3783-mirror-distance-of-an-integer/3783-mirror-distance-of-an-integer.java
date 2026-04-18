class Solution {
    public int mirrorDistance(int n) {
        if(n >= 1 && n <= 9) return 0;
        String num = Integer.toString(n);
        int reversedNum = 0;

        for(int i=num.length()-1; i>=0; i--){
            int digit = num.charAt(i) - '0';
            reversedNum = (reversedNum*10)+digit;
        }

        return (int) Math.abs(n - reversedNum);
    }
}