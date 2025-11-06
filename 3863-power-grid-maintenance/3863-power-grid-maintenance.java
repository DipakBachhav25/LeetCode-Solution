class Solution {

    static class DisjointSetUnion{
        int[] parent, rank;

        DisjointSetUnion(int n){
            parent = new int[n+1];
            rank = new int[n+1];
            for(int i=1; i<=n; i++) parent[i] = i;
        }

        int find(int x){
            if(x != parent[x]) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b){
            int pa = find(a), pb = find(b);
            if(pa == pb) return;
            if(rank[pa] < rank[pb]){
                parent[pa] = pb;
            }else if(rank[pb] < rank[pa]){
                parent[pb] = pa;
            }else{
                parent[pb] = pa;
                rank[pa]++;
            }
        }

    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        DisjointSetUnion dsu = new DisjointSetUnion(c);

        for(int[] con : connections){
            dsu.union(con[0], con[1]);
        }

        Map<Integer, TreeSet<Integer>> cs = new HashMap<>();
        for(int i=1; i<=c; i++){
            int x = dsu.find(i);
            cs.computeIfAbsent(x, k -> new TreeSet<>()).add(i);
        }

        boolean[] online = new boolean[c+1];
        Arrays.fill(online, true);

        List<Integer> res = new ArrayList<>();

        for(int[] q : queries){
            int type = q[0];
            int x = q[1];
            int r = dsu.find(x);

            if(type == 1){
                if(online[x]){
                    res.add(x);
                }else {
                    TreeSet<Integer> stations = cs.get(r);
                    if(stations == null || stations.isEmpty()){
                        res.add(-1);
                    }else {
                        while(!stations.isEmpty() && !online[stations.first()]){
                            stations.pollFirst();
                        }
                        res.add(stations.isEmpty() ? -1 : stations.first());
                    }
                }
            } else if(type == 2){
                if(online[x]){
                    online[x] = false;
                }
            }
        }
        int[] ans = new int[res.size()];
        int i=0;
        for(int n : res) ans[i++] = n;

        return ans;
    }
}