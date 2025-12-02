class Solution {
    private final long MOD = 1_000_000_007L;
    private final long INVMOD = 500000004L;
    public int countTrapezoids(int[][] points) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int[] p : points){
            int y = p[1];
            map.put(y, map.getOrDefault(y, 0)+1);
        }

        long s = 0L;
        long sumSq = 0L;
        for(int val : map.values()){
            if(val < 2) continue;
            long a = ((long) val * (val-1) / 2) % MOD;
            s = (s+a) % MOD;
            sumSq = (sumSq + (a*a) % MOD) % MOD;
        }

        long total = (((s*s) % MOD - sumSq) % MOD + MOD) % MOD;
        total = (total*INVMOD) % MOD;

        return (int) total;
    }
}