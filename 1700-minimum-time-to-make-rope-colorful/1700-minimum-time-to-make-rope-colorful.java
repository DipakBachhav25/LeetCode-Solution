class Solution {
    public int minCost(String colors, int[] neededTime) {
        int minTime = 0;
        int i=0;
        while(i < neededTime.length){
            int sum = neededTime[i];
            int j = i+1;
            int max = neededTime[i];
            while(j < neededTime.length && colors.charAt(i) == colors.charAt(j)){
                max = Math.max(max, neededTime[j]);
                sum += neededTime[j];
                j++;
            }
            minTime += sum-max;
            i = j;
        }
        return minTime;
    }
}