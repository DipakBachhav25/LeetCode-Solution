class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long totalMins = 0L;
        for(int x : batteries) totalMins += x;

        long l = 0L, r = totalMins/n;
        while(l <= r){
            long mid = l + (r-l)/2;
            if(canRunComputers(n, batteries, mid)) l = mid+1;
            else r = mid-1;
        }

        return r;
    }

    public static boolean canRunComputers(int n, int[] batteries, long time){
        if(time == 0) return true;
        long req = time * (long) n;
        long s = 0L;

        for(int b : batteries){
            s += Math.min((long) b, time);
            if(s >= req) return true;
        }

        return s >= req;
    }

    // public long maxRunTime(int n, int[] batteries){
    //     Arrays.sort(batteries);
    //     long totalMins = 0L;
    //     for(int x : batteries) totalMins += x;

    //     for(int i=batteries.length-1; i>=0; i--){
    //         if(batteries[i] > totalMins/n){
    //             totalMins -= batteries[i];
    //             n--;
    //         }else{
    //             return totalMins/n;
    //         }
    //     }
    //     return totalMins/n;
    // }
}