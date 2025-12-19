// class Solution {
//     public List<List<Integer>> permute(int[] nums) {
//         List<List<Integer>> ans = new ArrayList<>();
//         List<Integer> sol = new ArrayList<>();
//         permute(nums, ans, sol);

//         return ans;
//     }

//     public static void permute(int[] nums, List<List<Integer>> ans, List<Integer> sol){
//         if(nums.length == sol.size()){
//             ans.add(new ArrayList<>(sol));
//             return;
//         }

//         for(int x : nums){
//             if(!sol.contains(x)){
//                 sol.add(x);
//                 permute(nums, ans, sol);
//                 sol.remove(sol.size()-1);
//             }
//         }
//     }
// }

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // track visited elements
        backtrack(nums, ans, new ArrayList<>(), used);
        return ans;
    }

    private void backtrack(int[] nums, List<List<Integer>> ans, List<Integer> sol, boolean[] used) {
        if (sol.size() == nums.length) {
            ans.add(new ArrayList<>(sol));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                sol.add(nums[i]);

                backtrack(nums, ans, sol, used);

                sol.remove(sol.size() - 1);
                used[i] = false; // backtrack
            }
        }
    }
}