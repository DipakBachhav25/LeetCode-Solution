class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> sol = new ArrayList<>();
        permute(nums, ans, sol);

        return ans;
    }

    public static void permute(int[] nums, List<List<Integer>> ans, List<Integer> sol){
        if(nums.length == sol.size()){
            ans.add(new ArrayList<>(sol));
            return;
        }

        for(int x : nums){
            if(!sol.contains(x)){
                sol.add(x);
                permute(nums, ans, sol);
                sol.remove(sol.size()-1);
            }
        }
    }
}