class Solution {
    public int countPermutations(int[] complexity) {
        int MOD = 1_000_000_007;

        if (Arrays.stream(complexity, 1, complexity.length)
                  .anyMatch(x -> x <= complexity[0])) {
            return 0;
        }

        long ans = 1;
        for (int i = 1; i < complexity.length; i++) {
            ans = (ans * i) % MOD;
        }

        return (int) ans;

    }
}