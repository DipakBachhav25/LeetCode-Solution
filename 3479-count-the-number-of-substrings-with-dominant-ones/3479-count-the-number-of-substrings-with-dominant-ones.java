class Solution {
    public int numberOfSubstrings(String s) {
        int subStringCount = 0;
        int n = s.length();
        for(int i=0; i*i<n; i++){
            int lastIdx = -1;
            int[] freq = new int[2];
            for(int start=0, end=0; end<n; end++){
                freq[s.charAt(end)-'0']++;
                while(start < end){
                    if(s.charAt(start) == '0' && freq[0] > i){
                        freq[0]--;
                        lastIdx = start;
                    }else if(s.charAt(start) == '1' && (freq[1]-1) >= (i*i)){
                        freq[1]--;
                    }else{
                        break;
                    }
                    start++;
                }
                if(freq[0] == i && freq[1] >= freq[0]*freq[0]){
                    subStringCount += (start-lastIdx);
                }
            }
        }
        return subStringCount;
    }
}