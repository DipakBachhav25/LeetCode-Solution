class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> arr1 = new HashSet<>();
        Set<Integer> interSections = new HashSet<>();

        for(int n : nums1) arr1.add(n);

        for(int n : nums2){
            if(arr1.contains(n)) interSections.add(n);
        }

        int[] ans = new int[interSections.size()];

        int i=0;
        for(int n : interSections) ans[i++] = n;

        return ans;
    }
}