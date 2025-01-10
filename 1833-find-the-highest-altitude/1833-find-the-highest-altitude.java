class Solution {
    public int largestAltitude(int[] gain) {
        int[] altitude = new int[gain.length+1];
        altitude[0] = 0;
        int i = 1;
        int maxAltitude = Integer.MIN_VALUE;
        int sum = 0;
        for(int element : gain){
            sum += element;
            altitude[i++] = sum;
        }

        for(int a : altitude){
            maxAltitude = Math.max(maxAltitude, a);
        }

        return maxAltitude;
    }
}