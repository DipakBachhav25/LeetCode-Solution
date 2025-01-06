class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];

        int operations = 0;
        int balls = 0;

        for(int i=0; i<n; i++){
            ans[i] += operations;
            balls += boxes.charAt(i) - '0';
            operations += balls;
        }

        operations = 0;
        balls = 0;
        for(int i=n-1; i>=0; i--){
            ans[i] += operations;
            balls += boxes.charAt(i) - '0';
            operations += balls;
        }

        return ans;
    }
}