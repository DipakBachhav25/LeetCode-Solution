class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0]-a[0] : a[1]-b[1]);
        int a = -1, b = -1;
        int ans = 0;

        for(int[] interval : intervals){
            int s = interval[0], e = interval[1];

            if(s <= a) continue;

            if(s <= b){
                ans += 1;
                a = b;
                b = e;
            }else{
                ans += 2;
                a = e-1;
                b = e;
            }
        }

        return ans;
    }
}