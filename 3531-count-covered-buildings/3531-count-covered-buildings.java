class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        // Map<Integer, int[]> yRangeAtX = new HashMap<>();
        // Map<Integer, int[]> xRangeAtY = new HashMap<>();

        // for (int[] building : buildings) {
        //     int x = building[0], y = building[1];

        //     yRangeAtX.compute(x, (k, v) -> {
        //         if (v == null) return new int[]{y, y};
        //         v[0] = Math.min(v[0], y);
        //         v[1] = Math.max(v[1], y);
        //         return v;
        //     });

        //     xRangeAtY.compute(y, (k, v) -> {
        //         if (v == null) return new int[]{x, x};
        //         v[0] = Math.min(v[0], x);
        //         v[1] = Math.max(v[1], x);
        //         return v;
        //     });
        // }

        // int covered = 0;
        // for (int[] building : buildings) {
        //     int x = building[0], y = building[1];

        //     int[] yRange = yRangeAtX.get(x);
        //     int[] xRange = xRangeAtY.get(y);

        //     boolean hasBelow = yRange[0] < y;
        //     boolean hasAbove = y < yRange[1];
        //     boolean hasLeft = xRange[0] < x;
        //     boolean hasRight = x < xRange[1];

        //     if (hasBelow && hasAbove && hasLeft && hasRight) {
        //         covered++;
        //     }
        // }

        // return covered;

        int MAX = 100001;
        int[] minYAtX = new int[MAX];
        int[] maxYAtX = new int[MAX];
        int[] minXAtY = new int[MAX];
        int[] maxXAtY = new int[MAX];

        Arrays.fill(minYAtX, Integer.MAX_VALUE);
        Arrays.fill(minXAtY, Integer.MAX_VALUE);

        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            minYAtX[x] = Math.min(minYAtX[x], y);
            maxYAtX[x] = Math.max(maxYAtX[x], y);
            minXAtY[y] = Math.min(minXAtY[y], x);
            maxXAtY[y] = Math.max(maxXAtY[y], x);
        }

        int covered = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            boolean hasBelow = minYAtX[x] < y;
            boolean hasAbove = y < maxYAtX[x];
            boolean hasLeft = minXAtY[y] < x;
            boolean hasRight = x < maxXAtY[y];
            if (hasBelow && hasAbove && hasLeft && hasRight) covered++;
        }
        return covered;
    }
}