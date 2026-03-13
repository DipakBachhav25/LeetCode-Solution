class Solution {
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        // 1. Find the fastest worker to set a realistic upper bound
        long minWorkerTime = workerTimes[0];
        for (int t : workerTimes) {
            minWorkerTime = Math.min(minWorkerTime, t);
        }
        
        // 2. Define the search space
        long low = 1;
        // Worst-case scenario: the fastest worker does ALL the work alone
        long high = minWorkerTime * (long) mountainHeight * (mountainHeight + 1) / 2;
        long ans = high;
        
        // 3. Binary Search for the minimum time
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            if (canFinish(mid, mountainHeight, workerTimes)) {
                ans = mid;        // This time works, record it
                high = mid - 1;   // Try to find a strictly smaller time limit
            } else {
                low = mid + 1;    // Time limit is too strict, we need more time
            }
        }
        
        return ans;
    }
    
    private boolean canFinish(long timeLimit, int mountainHeight, int[] workerTimes) {
        long totalReduced = 0;
        
        for (int t : workerTimes) {
            // Using the quadratic formula to find max mountain height (x) this worker can reduce.
            // Formula: x^2 + x - (2 * timeLimit / t) <= 0
            // x = (-1 + sqrt(1 + 8 * timeLimit / t)) / 2
            
            // We cast to double for the square root, but the math is stable enough for these constraints
            long max_x = (long) ((Math.sqrt(1.0 + 8.0 * ((double) timeLimit / t)) - 1.0) / 2.0);
            
            totalReduced += max_x;
            
            // Early termination: if we've already met the target height, return true
            if (totalReduced >= mountainHeight) {
                return true;
            }
        }
        
        return false;
    }
}