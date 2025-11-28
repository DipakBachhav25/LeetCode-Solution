class Solution {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] graph = new ArrayList[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        return dfs(0, -1, graph, values, k).count;
    }

    private static Result dfs(int node, int parent, List<Integer>[] graph, int[] values, int k) {
        long sum = values[node];
        int count = 0;

        for (int neighbor : graph[node]) {
            if (neighbor != parent) {
                Result child = dfs(neighbor, node, graph, values, k);
                sum += child.sum;
                count += child.count;
            }
        }

        if (sum % k == 0) {
            count++;
        }

        return new Result(sum, count);
    }

    private static class Result {
        long sum;
        int count;
        Result(long sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
}