class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        return IntStream.range(0, bottomLeft.length)
            .parallel()
            .mapToLong(i -> {
                long localMax = 0;
                int rect1LeftX = bottomLeft[i][0];
                int rect1BottomY = bottomLeft[i][1];
                int rect1RightX = topRight[i][0];
                int rect1TopY = topRight[i][1];

                for (int j = i + 1; j < bottomLeft.length; ++j) {
                    int rect2LeftX = bottomLeft[j][0];
                    int rect2BottomY = bottomLeft[j][1];
                    int rect2RightX = topRight[j][0];
                    int rect2TopY = topRight[j][1];

                    // Skip if no overlap
                    if (rect1RightX <= rect2LeftX || rect2RightX <= rect1LeftX ||
                        rect1TopY <= rect2BottomY || rect2TopY <= rect1BottomY) {
                        continue;
                    }

                    int overlapWidth = Math.min(rect1RightX, rect2RightX) - Math.max(rect1LeftX, rect2LeftX);
                    int overlapHeight = Math.min(rect1TopY, rect2TopY) - Math.max(rect1BottomY, rect2BottomY);
                    int squareSideLength = Math.min(overlapWidth, overlapHeight);

                    if (squareSideLength > 0) {
                        localMax = Math.max(localMax, 1L * squareSideLength * squareSideLength);
                    }
                }
                return localMax;
            })
            .max()
            .orElse(0);
    }
}