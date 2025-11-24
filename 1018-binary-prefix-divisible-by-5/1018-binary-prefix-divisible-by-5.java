class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> list = new ArrayList<>();
        int sum  = 0;
        for(int n : nums){
            sum = (sum*2 + n) % 5;
            list.add(sum == 0);
        }
        return list;
    }
}