class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int cand1 = -1, cand2 = -1;
        int count1 = 0, count2 = 0;

        for(int n : nums){
            if(cand1 == n) count1++;
            else if(cand2 == n) count2++;
            else if(count1 == 0){
                cand1 = n;
                count1++;
            }
            else if(count2 == 0){
                cand2 = n;
                count2++;
            }
            else{
                count1--;
                count2--;
            }
        }

        List<Integer> ans = new ArrayList<>();
        count1 = 0; count2 = 0;

        for(int n : nums){
            if(cand1 == n) count1++;
            if(cand2 == n) count2++;
        }

        if(count1 > nums.length/3) ans.add(cand1);
        if(count2 > nums.length/3 && cand1 != cand2) ans.add(cand2);

        return ans;
    }
}