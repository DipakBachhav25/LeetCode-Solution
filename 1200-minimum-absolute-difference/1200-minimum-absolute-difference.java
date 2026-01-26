class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;

        // for(int i = 1; i < arr.length; i++) {
        //     int diff = arr[i]-arr[i-1];
        //     minDiff = Math.min(minDiff, diff);
        // }

        for(int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i-1];
            if(minDiff > diff){
                ans.clear();
                minDiff = diff;
                ans.add(Arrays.asList(arr[i-1], arr[i]));
            }else if(diff == minDiff){
                ans.add(Arrays.asList(arr[i-1], arr[i]));
            }
        }

        return ans;
    }
}