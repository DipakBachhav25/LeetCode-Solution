class Solution {
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {

        List<List<Integer>> hc = Arrays.stream(hierarchy)
        .map(innerArray -> IntStream.of(innerArray).boxed().collect(Collectors.toList())).collect(Collectors.toList());

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (List<Integer> h : hc) {
            adjList.get(h.get(0) - 1).add(h.get(1) - 1);
        }

        Map<String, Map<Integer, Integer>> memo = new HashMap<>();

        // dfs returns a map of cost -> profit
        class DFS {
            Map<Integer, Integer> dfs(int employee, boolean hasDiscount) {
                String key = employee + "," + hasDiscount;
                if (memo.containsKey(key)) {
                    return memo.get(key);
                }

                int cost = hasDiscount ? present[employee] / 2 : present[employee];
                int profit = future[employee] - cost;

                Map<Integer, Integer> buyCurrent = new HashMap<>();
                if (cost <= budget) {
                    buyCurrent.put(cost, profit);
                }
                Map<Integer, Integer> skipCurrent = new HashMap<>();
                skipCurrent.put(0, 0);

                for (int child : adjList.get(employee)) {
                    Map<Integer, Integer> childWithDiscount = dfs(child, true);
                    Map<Integer, Integer> childNoDiscount = dfs(child, false);

                    Map<Integer, Integer> newBuy = new HashMap<>();
                    for (Map.Entry<Integer, Integer> entry : buyCurrent.entrySet()) {
                        int spent = entry.getKey();
                        int prof = entry.getValue();
                        for (Map.Entry<Integer, Integer> childEntry : childWithDiscount.entrySet()) {
                            int childSpent = childEntry.getKey();
                            int childProf = childEntry.getValue();
                            int totalSpent = spent + childSpent;
                            if (totalSpent <= budget) {
                                int totalProf = prof + childProf;
                                newBuy.merge(totalSpent, totalProf, Math::max);
                            }
                        }
                    }
                    buyCurrent = newBuy;

                    Map<Integer, Integer> newSkip = new HashMap<>();
                    for (Map.Entry<Integer, Integer> entry : skipCurrent.entrySet()) {
                        int spent = entry.getKey();
                        int prof = entry.getValue();
                        for (Map.Entry<Integer, Integer> childEntry : childNoDiscount.entrySet()) {
                            int childSpent = childEntry.getKey();
                            int childProf = childEntry.getValue();
                            int totalSpent = spent + childSpent;
                            if (totalSpent <= budget) {
                                int totalProf = prof + childProf;
                                newSkip.merge(totalSpent, totalProf, Math::max);
                            }
                        }
                    }
                    skipCurrent = newSkip;
                }

                Map<Integer, Integer> result = new HashMap<>();
                for (Map.Entry<Integer, Integer> entry : buyCurrent.entrySet()) {
                    int spent = entry.getKey();
                    int prof = entry.getValue();
                    result.merge(spent, prof, Math::max);
                }
                for (Map.Entry<Integer, Integer> entry : skipCurrent.entrySet()) {
                    int spent = entry.getKey();
                    int prof = entry.getValue();
                    result.merge(spent, prof, Math::max);
                }

                memo.put(key, result);
                return result;
            }
        }

        DFS dfsObj = new DFS();
        Map<Integer, Integer> result = dfsObj.dfs(0, false);
        return result.values().stream().max(Integer::compareTo).orElse(0);
    }
}