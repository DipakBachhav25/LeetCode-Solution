class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        int count = 0;

        for (int c = 0; c < cols; c++) {
            char prev = strs[0].charAt(c);
            for (int r = 1; r < rows; r++) {
                char cur = strs[r].charAt(c);
                if (cur < prev) {
                    count++;
                    break;
                }
                prev = cur;
            }
        }
        return count;

    }
}