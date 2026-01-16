class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Set<Integer> horizontalDistances = calculateAllDistances(hFences, m);
        Set<Integer> verticalDistances = calculateAllDistances(vFences, n);
      
        horizontalDistances.retainAll(verticalDistances);
        int maxSideLength = -1;
        final int MOD = (int) 1e9 + 7;
      
        for (int sideLength : horizontalDistances) {
            maxSideLength = Math.max(maxSideLength, sideLength);
        }

        return maxSideLength > 0 ? (int) (1L * maxSideLength * maxSideLength % MOD) : -1;
    }

    private Set<Integer> calculateAllDistances(int[] fences, int boundaryPosition) {
        int fenceCount = fences.length;
        int[] allPositions = Arrays.copyOf(fences, fenceCount + 2);
        allPositions[fenceCount] = 1;
        allPositions[fenceCount + 1] = boundaryPosition;
        Arrays.sort(allPositions);
        Set<Integer> distances = new HashSet<>();
      
        for (int i = 0; i < allPositions.length; ++i) {
            for (int j = 0; j < i; ++j) {
                distances.add(allPositions[i] - allPositions[j]);
            }
        }
      
        return distances;
    }
}