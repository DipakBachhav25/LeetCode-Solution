class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long minWorkerTime = workerTimes[0];
        for (int t : workerTimes) {
            minWorkerTime = Math.min(minWorkerTime, t);
        }

        long low = 1;
        long high = minWorkerTime * (long) mountainHeight * (mountainHeight + 1) / 2;
        long ans = high;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (canFinish(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    private boolean canFinish(long timeLimit, int mountainHeight, int[] workerTimes) {
        long totalReduced = 0;
        
        for (int t : workerTimes) {
            long max_x = (long) ((Math.sqrt(1.0 + 8.0 * ((double) timeLimit / t)) - 1.0) / 2.0);
            
            totalReduced += max_x;

            if (totalReduced >= mountainHeight) {
                return true;
            }
        }
        
        return false;
    }
}