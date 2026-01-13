class Solution {
    public double separateSquares(int[][] squares) {
        double l = Double.MAX_VALUE, h = Double.MIN_VALUE;

        for(int[] sq : squares){
            l = Math.min(l, sq[1]);
            h = Math.max(h, sq[1]+sq[2]);
        }

        for(int i=0; i<60; i++){
            double mid = (l+h)/2, d = 0;
            for(int[] sq : squares){
                double y = sq[1], line = sq[2], t = y+line;
                if(mid <= y) d += line*line;
                else if(mid >= t) d -= line*line;
                else d += (t-mid)*line - (mid-y)*line;
            }
            if(d > 0) l = mid;
            else h = mid;
        }

        return l;
    }
}