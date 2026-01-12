class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int minTime = 0;
        for(int r=1; r<points.length; r++){
            int x = Math.abs(points[r][0] - points[r-1][0]);
            int y = Math.abs(points[r][1] - points[r-1][1]);
            minTime += Math.max(x, y);
        }

        return minTime;
    }
}