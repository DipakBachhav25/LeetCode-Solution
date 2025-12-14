class Solution {
    public int numberOfWays(String corridor) {
        final int MOD = (int) 1e9 + 7;
        char[] arr = corridor.toCharArray();
        int n = arr.length;

        List<Integer> seats = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == 'S') seats.add(i);
        }

        if (seats.size() % 2 != 0 || seats.size() < 2) return 0;

        long ways = 1;
        for (int i = 0; i < seats.size() - 2; i += 2) {
            int gap = seats.get(i + 2) - seats.get(i + 1);
            ways = (ways * gap) % MOD;
        }

        return (int) ways;
    }
}