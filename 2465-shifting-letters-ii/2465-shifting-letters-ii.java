class Solution {

    public static char shiftChar(char c, int shift){
        shift = (shift%26+26) % 26;
        return (char) ('a' + (c-'a'+shift) % 26);
    }

    public String shiftingLetters(String s, int[][] shifts) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int[] netShifts = new int[n];

        for(int[] shift : shifts){
            int start = shift[0];
            int end = shift[1];
            int dire = shift[2];

            int d = dire == 1 ? 1 : -1;
            netShifts[start] += d;

            if(end+1 < n){
                netShifts[end+1] -= d;
            }
        }

        int currShift = 0;
        for(int i=0; i<n; i++){
            currShift += netShifts[i];
            chars[i] = shiftChar(chars[i], currShift);
        }

        return new String(chars);
    }
}