class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while(columnNumber != 0){
            char ch = (char) ('A' + (columnNumber-1)%26);
            sb.append(ch);
            columnNumber = (columnNumber-1)/26;
        }

        return sb.reverse().toString();
    }
}