class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        
        boolean[] flag = new boolean[1001];
        for(int n : nums1) flag[n] = true;

        int i=0;
        for(int n : nums2){
            if(flag[n] == true){
                nums1[i++] = n;
                flag[n] = false;
            }
        }

        return Arrays.copyOf(nums1, i);
    }
}