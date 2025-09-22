class Solution {

    public int numDigitSquareSum(int n){
        int num = 0;
        while(n != 0){
            int rem = n%10;
            num += rem*rem;
            n /= 10;
        }
        return num;
    }

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while(true){
            n = numDigitSquareSum(n);
            if(n == 1) return true;
            if(set.contains(n)) return false;
            set.add(n);
        }
    }
}