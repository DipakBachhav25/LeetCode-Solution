class Solution {
    public int maxScore(String s) {
        int n = s.length();
        int maxScore = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            String left = s.substring(0, i+1);
            String right = s.substring(i+1);

            if(left == "" || right == ""){
                break;
            }

            int zeroCount = (int)left.chars().filter(c -> c == '0').count();
            int oneCount = (int)right.chars().filter(c -> c == '1').count();

            maxScore = Math.max((zeroCount+oneCount), maxScore);
        }

        return maxScore;
    }
}