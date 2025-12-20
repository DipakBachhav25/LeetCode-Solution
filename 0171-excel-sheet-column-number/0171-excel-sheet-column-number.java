class Solution {
    public int titleToNumber(String columnTitle) {
        int colNum = 0;
        for(char ch : columnTitle.toCharArray()){
            colNum = (colNum*26) + (ch - 'A' + 1);
        }

        return colNum;
    }
}