class Solution {

    static class TrieNode{
        TrieNode[] children = new TrieNode[26];
        int id = -1;
    }

    void insert(TrieNode root, String s, int id){
        TrieNode node = root;
        for(char c : s.toCharArray()){
            int idx = c - 'a';
            if(node.children[idx] == null){
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.id = id;
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Map<String, Integer> strToId = new HashMap<>();
        int idCounter = 0;

        for(String s : original){
            if(!strToId.containsKey(s)) strToId.put(s, idCounter++);
        }

        for(String s : changed){
            if(!strToId.containsKey(s)) strToId.put(s, idCounter++);
        }

        int m = idCounter;
        long[][] dist = new long[m][m];
        long INF = Long.MAX_VALUE/2;

        for(long[] r : dist) Arrays.fill(r, INF);
        for(int i=0; i<m; i++) dist[i][i] = 0;

        for (int i = 0; i < original.length; i++) {
            int u = strToId.get(original[i]);
            int v = strToId.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                if (dist[i][k] == INF) continue;
                for (int j = 0; j < m; j++) {
                    if (dist[k][j] == INF) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        TrieNode root = new TrieNode();
        for (Map.Entry<String, Integer> entry : strToId.entrySet()) {
            insert(root, entry.getKey(), entry.getValue());
        }

        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == INF) continue;
            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }
            TrieNode pSource = root;
            TrieNode pTarget = root;

            for (int len = 1; i + len <= n; len++) {
                int charS = source.charAt(i + len - 1) - 'a';
                int charT = target.charAt(i + len - 1) - 'a';

                pSource = pSource.children[charS];
                pTarget = pTarget.children[charT];

                if (pSource == null || pTarget == null) break;

                if (pSource.id != -1 && pTarget.id != -1) {
                    long transCost = dist[pSource.id][pTarget.id];
                    if (transCost < INF) {
                        dp[i + len] = Math.min(dp[i + len], dp[i] + transCost);
                    }
                }
            }
        }

        return dp[n] >= INF ? -1 : dp[n];
    }
}