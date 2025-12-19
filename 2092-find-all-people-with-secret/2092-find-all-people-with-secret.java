class Solution {

    static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return;
            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[rb] < rank[ra]) parent[rb] = ra;
            else { parent[rb] = ra; rank[ra]++; }
        }
    }

    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        boolean[] knowsSecret = new boolean[n];
        knowsSecret[0] = true;
        knowsSecret[firstPerson] = true;

        Arrays.sort(meetings, Comparator.comparingInt(m -> m[2]));
        int total = meetings.length;

        // Reusable buffers to avoid per-group allocations
        boolean[] inGroup = new boolean[n];
        int[] indexOf = new int[n];
        Arrays.fill(indexOf, -1);
        int[] people = new int[n]; // at most n distinct people per time group

        for (int i = 0; i < total; ) {
            int j = i;
            int time = meetings[i][2];
            while (j + 1 < total && meetings[j + 1][2] == time) j++;

            // collect unique people in this time group
            int pSize = 0;
            for (int k = i; k <= j; k++) {
                int a = meetings[k][0];
                int b = meetings[k][1];
                if (!inGroup[a]) { inGroup[a] = true; indexOf[a] = pSize; people[pSize++] = a; }
                if (!inGroup[b]) { inGroup[b] = true; indexOf[b] = pSize; people[pSize++] = b; }
            }

            if (pSize > 0) {
                // Union-Find over indices 0..pSize-1
                UnionFind uf = new UnionFind(pSize);
                for (int k = i; k <= j; k++) {
                    int a = meetings[k][0];
                    int b = meetings[k][1];
                    uf.union(indexOf[a], indexOf[b]);
                }

                // mark components that already have the secret
                boolean[] compHasSecret = new boolean[pSize];
                for (int idx = 0; idx < pSize; idx++) {
                    int person = people[idx];
                    if (knowsSecret[person]) {
                        compHasSecret[uf.find(idx)] = true;
                    }
                }

                // propagate secret to all people in components that have it
                for (int idx = 0; idx < pSize; idx++) {
                    int person = people[idx];
                    if (compHasSecret[uf.find(idx)]) knowsSecret[person] = true;
                }
            }

            // reset buffers for this group
            for (int idx = 0; idx < pSize; idx++) {
                int person = people[idx];
                inGroup[person] = false;
                indexOf[person] = -1;
            }

            i = j + 1;
        }

        List<Integer> result = new ArrayList<>();
        for (int p = 0; p < n; p++) if (knowsSecret[p]) result.add(p);
        return result;
    }
}