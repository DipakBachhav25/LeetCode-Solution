class Solution {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;

        long[] prefixSum = new long[n+1];
        for(int i=0; i<n; i++) prefixSum[i+1] = prefixSum[i]+stations[i];

        long[] power = new long[n];
        for(int i=0; i<n; i++){
            int left = Math.max(0, i-r);
            int right = Math.min(n-1, i+r);
            power[i] = prefixSum[right+1]-prefixSum[left];
        }

        long l = 0;
        long h = prefixSum[n]+k;
        while(l < h){
            long mid = (l+h+1) >>> 1;
            if(canMake(power, r, k, mid)) l = mid;
            else h = mid-1;
        }

        return l;
    }

    private boolean canMake(long[] power, int r, long k, long t){
        int n = power.length;
        long[] diff = new long[n+1];
        long sum = 0;
        long remain = k;

        for(int i=0; i<n; i++){
            sum += diff[i];
            long total = power[i]+sum;
            if(total < t){
                long need = t-total;
                if(need > remain) return false;
                remain -= need;
                sum += need;
                int pos = Math.min(n-1, i+r);
                int end = Math.min(n-1, pos+r);
                diff[end+1] -= need;
            }
        }
        return true;
    }
}