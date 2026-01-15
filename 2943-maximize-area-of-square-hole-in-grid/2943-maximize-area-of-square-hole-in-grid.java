class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxHorizontalGap = findMaxConsecutiveGap(hBars);
        int maxVerticalGap = findMaxConsecutiveGap(vBars);

        int maxSquareSide = Math.min(maxHorizontalGap, maxVerticalGap);
        return maxSquareSide * maxSquareSide;

    }

    private int findMaxConsecutiveGap(int[] bars) {
        if (bars == null || bars.length == 0) return 1;

        Set<Integer> set = new HashSet<>();
        for (int bar : bars) set.add(bar);

        int maxConsecutive = 1;
        for (int bar : set) {
            // Only start counting if it's the beginning of a sequence
            if (!set.contains(bar - 1)) {
                int current = bar;
                int length = 1;

                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }
                maxConsecutive = Math.max(maxConsecutive, length);
            }
        }
        return maxConsecutive + 1;
    }

}