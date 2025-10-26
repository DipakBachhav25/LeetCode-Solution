class Solution {
    public String convert(String s, int numRows) {

        if(numRows == 1) return s;

        StringBuilder[] sb = new StringBuilder[numRows+1];
        for(int i=0; i<sb.length; i++){
            sb[i] = new StringBuilder();
        }

        int colNum = 0;
        boolean goDown = true;

        for(char ch : s.toCharArray()){
            sb[colNum].append(ch);
            colNum = goDown ? colNum+1:colNum-1;
            if(colNum == numRows-1) goDown = false;
            else if (colNum == 0) goDown = true;
        }
        StringBuilder ans = new StringBuilder();
        for(StringBuilder tmp : sb){
            ans.append(tmp);
        }
        return ans.toString();
    }
}