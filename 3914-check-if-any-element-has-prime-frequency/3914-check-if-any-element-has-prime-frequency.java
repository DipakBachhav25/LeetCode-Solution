class Solution {

    public boolean isPrime(int n){
        int cnt = 0;
        for(int i=1; i<=Math.sqrt(n); i++){
            if(n%i == 0){
                cnt++;
                if(n / i != i){
                    cnt = cnt + 1;
                }
            }
        }
        if(cnt == 2) return true;
        else return false;
    }

    public boolean checkPrimeFrequency(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int n : nums){
            if(map.containsKey(n)) map.put(n, map.get(n)+1);
            else map.put(n, 1);
        }

        for(int n : map.values()){
            if(isPrime(n)) return true;
        }

        return false;
    }
}