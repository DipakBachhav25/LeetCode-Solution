class Solution {
    class DSU {
        int[] parent;

        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public DSU(int[] parent) {
            this.parent = parent.clone();
        }

        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }

        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }

        public void flatten(int n) {
            for (int i = 0; i < n; i++) find(i);
        }
    }

    public int maxStability(int n, int[][] edges, int k) {
        int[][] mustEdges = new int[edges.length][];
        int[][] optEdges = new int[edges.length][];
        int mustCount = 0;
        int optCount = 0;
        
        int minMustStrength = Integer.MAX_VALUE;
        DSU initialCheck = new DSU(n);
        int totalComponents = n;

        for (int[] edge : edges) {
            if (edge[3] == 1) {
                mustEdges[mustCount++] = edge;
                minMustStrength = Math.min(minMustStrength, edge[2]);
            } else {
                optEdges[optCount++] = edge;
            }
            if (initialCheck.union(edge[0], edge[1])) {
                totalComponents--;
            }
        }

        if (totalComponents > 1) return -1;

        DSU baseDsu = new DSU(n);
        int baseEdgesAdded = 0;

        for (int i = 0; i < mustCount; i++) {
            int[] edge = mustEdges[i];
            if (!baseDsu.union(edge[0], edge[1])) {
                return -1; 
            }
            baseEdgesAdded++;
        }

        baseDsu.flatten(n);

        int low = 1;
        int high = minMustStrength == Integer.MAX_VALUE ? 200000 : minMustStrength;
        int maxStability = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            if (isPossible(n, baseDsu.parent, baseEdgesAdded, optEdges, optCount, k, mid)) {
                maxStability = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return maxStability;
    }

    private boolean isPossible(int n, int[] baseParent, int baseEdgesAdded, int[][] optEdges, int optCount, int k, int target) {
        DSU dsu = new DSU(baseParent);
        int edgesAdded = baseEdgesAdded;
        int upgradesUsed = 0;

        for (int i = 0; i < optCount; i++) {
            if (optEdges[i][2] >= target) {
                if (dsu.union(optEdges[i][0], optEdges[i][1])) {
                    edgesAdded++;
                }
            }
        }
        
        if (edgesAdded == n - 1) return true;

        for (int i = 0; i < optCount; i++) {
            if (optEdges[i][2] < target && optEdges[i][2] * 2 >= target) {
                if (dsu.union(optEdges[i][0], optEdges[i][1])) {
                    edgesAdded++;
                    upgradesUsed++;
                }
            }
            
            if (edgesAdded == n - 1) {
                return upgradesUsed <= k;
            }
        }

        return false;
    }
}