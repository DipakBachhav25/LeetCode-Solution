class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int x = 0;

        for(int n : nums){
            if(count == 0)
                x = n;
            count += x == n ? 1:-1;
        }

        return x;
    }
}