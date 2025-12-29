class Solution {

    private List<Integer>[][] transitions = new ArrayList[7][7];
    private Map<Long, Boolean> memo = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        for (String triplet : allowed) {
            int a = triplet.charAt(0) - 'A';
            int b = triplet.charAt(1) - 'A';
            int c = triplet.charAt(2) - 'A';
            if (transitions[a][b] == null) transitions[a][b] = new ArrayList<>();
            transitions[a][b].add(c);
        }
        return dfs(bottom.toCharArray(), bottom.length(), new char[bottom.length()-1], 0);
    }

    private boolean dfs(char[] current, int len, char[] next, int nextLen) {
        if (len == 1) return true;
        if (nextLen + 1 == len) return dfs(next, nextLen, new char[nextLen-1], 0);

        long key = encodeState(current, len, next, nextLen);
        if (memo.containsKey(key)) return memo.get(key);

        int a = current[nextLen] - 'A';
        int b = current[nextLen+1] - 'A';
        List<Integer> options = transitions[a][b];
        if (options == null) {
            memo.put(key, false);
            return false;
        }

        for (int c : options) {
            next[nextLen] = (char) ('A' + c);
            if (dfs(current, len, next, nextLen+1)) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    private long encodeState(char[] current, int len, char[] next, int nextLen) {
        long hash = 0;
        for (int i = 0; i < len; i++) hash = (hash << 3) | (current[i] - 'A');
        hash = (hash << 16);
        for (int i = 0; i < nextLen; i++) hash = (hash << 3) | (next[i] - 'A');
        return hash;
    }
}