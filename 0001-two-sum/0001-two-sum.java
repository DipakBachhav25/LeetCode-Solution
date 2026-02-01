// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         HashMap<Integer, Integer> map = new HashMap<>();
//         for(int i=0; i<nums.length; i++){
//             if(map.containsKey(target-nums[i])){
//                 return new int[]{map.get(target-nums[i]), i};
//             }
//             map.put(nums[i], i);
//         }

//         return new int[]{};
//     }
// }

class Solution {
    public int[] twoSum(int[] nums, int target) {
        //int[] pos=new int[2];
        int n=nums.length;
        for(int i=1;i<n;i++){
			for(int j=i;j<n;j++)
				if(nums[j]+nums[j-i]==target){
                    /*pos[0]=j-i;
                    pos[1]=j;
					return pos;*/
                    return new int[]{j-i,j};
                }
		}
		return new int[]{-1,-1}; 
    }
}