class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        Stack<Integer> s = new Stack<>();
        s.push(0);
        for(int n : nums){
            while(!s.isEmpty() && s.peek() > n) s.pop();
            if(s.peek() < n){
                ans++;
                s.push(n);
            }
        }
        return ans;
    }
}