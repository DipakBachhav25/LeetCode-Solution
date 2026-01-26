class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> ans = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;

        for(int i = 1; i < arr.length; i++) {
            int diff = arr[i]-arr[i-1];
            minDiff = Math.min(minDiff, diff);
        }

        for(int i = 1; i < arr.length; i++) {
            if(arr[i]-arr[i-1] == minDiff){
                ans.add(new ArrayList<>(List.of(arr[i-1], arr[i])));
            }
        }

        return ans;
    }
}