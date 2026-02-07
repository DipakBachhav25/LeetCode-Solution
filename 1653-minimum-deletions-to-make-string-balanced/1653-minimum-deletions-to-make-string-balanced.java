class Solution {
    public int minimumDeletions(String s) {
        int bCount = 0;
        int ans = 0;

        for(char ch : s.toCharArray()){
            if(ch == 'b') bCount++;
            else{
                ans = Math.min(ans+1, bCount);
            }
        }

        return ans;
    }
}