class Solution {
    public int titleToNumber(String columnTitle) {
        int colNum = 0;
        for(int i=0; i<columnTitle.length(); i++){
            colNum = (colNum*26) + (columnTitle.charAt(i) - 'A' + 1);
        }

        return colNum;
    }
}