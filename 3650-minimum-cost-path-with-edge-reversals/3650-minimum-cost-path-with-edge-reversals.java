class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();

        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] e : edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];

            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, 2*w});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[0] = 0;
        pq.offer(new long[]{0, 0});

        while(!pq.isEmpty()){
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long currCost = curr[1];

            if(currCost > dist[u]) continue;

            if(u == n-1) return (int) currCost;

            for(int[] neighbor : graph.get(u)){
                int v = neighbor[0];
                int weight = neighbor[1];

                if(dist[u]+weight < dist[v]){
                    dist[v] = dist[u]+weight;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }

        return -1;
    }
}