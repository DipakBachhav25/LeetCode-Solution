class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();

        int leftBoundary = 0;
        while(leftBoundary < n && directions.charAt(leftBoundary) == 'L') leftBoundary++;

        int rightBoundary = n-1;
        while(rightBoundary >= 0 && directions.charAt(rightBoundary) == 'R') rightBoundary--;

        int collisionCount = 0;
        for(int i=leftBoundary; i<=rightBoundary; i++){
            if(directions.charAt(i) != 'S') collisionCount++;
        }

        return collisionCount;

    }
}