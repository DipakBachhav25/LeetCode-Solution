class Solution {
    public String getHappyString(int n, int k) {
        int totalPossibleStrings = 3*(1 << (n-1));

        if(k > totalPossibleStrings) return "";

        StringBuilder sb = new StringBuilder();
        k = k-1;

        int groupSize = 1 << (n-1);
        int firstCharIdx = k/groupSize;

        sb.append((char) ('a' + firstCharIdx));
        k %= groupSize;

        for(int i=1; i<n; i++){
            groupSize = 1 << (n-1-i);
            int nextCharIdx = k/groupSize;

            char lastChar = sb.charAt(sb.length()-1);
            char[] availablChars = new char[2];
            int idx = 0;

            for(char ch='a'; ch<='c'; ch++){
                if(ch != lastChar) availablChars[idx++] = ch;
            }

            sb.append(availablChars[nextCharIdx]);
            k %= groupSize;
        }

        return sb.toString();
    }
}