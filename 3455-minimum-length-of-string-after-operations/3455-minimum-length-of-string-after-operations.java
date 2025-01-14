class Solution {
    public int minimumLength(String s) {
        Map<Character, Integer> leftCount = new HashMap<>();
        Map<Character, Integer> rightCount = new HashMap<>();

        for(char c : s.toCharArray()){
            rightCount.put(c, rightCount.getOrDefault(c, 0)+1);
        }

        int n = s.length();
        int minLen = n;

        for(int i=0; i<n; i++){
            char curr = s.charAt(i);
            rightCount.put(curr, rightCount.get(curr)-1);

            if(leftCount.containsKey(curr) && rightCount.get(curr) > 0){
                int newLen = n - (leftCount.get(curr) + rightCount.get(curr) + 1);
                minLen = Math.min(newLen, minLen);
            }

            leftCount.put(curr, leftCount.getOrDefault(curr, 0) + 1);
        }

        return minLen;
    }
}