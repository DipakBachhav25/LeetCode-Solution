class Solution {
    static class Expiry implements Comparable<Expiry> {
        int time;
        int user;
        Expiry(int time, int user) { this.time = time; this.user = user; }
        public int compareTo(Expiry o) { return Integer.compare(this.time, o.time); }
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        events.sort((a, b) -> {
            int ta = Integer.parseInt(a.get(1)), tb = Integer.parseInt(b.get(1));
            if (ta != tb) return Integer.compare(ta, tb);
            if (a.get(0).equals(b.get(0))) return 0;
            if (a.get(0).equals("OFFLINE")) return -1;
            if (b.get(0).equals("OFFLINE")) return 1;
            return 0;
        });

        int[] ans = new int[numberOfUsers];
        boolean[] online = new boolean[numberOfUsers];
        int[] lastHereSeen = new int[numberOfUsers];
        Arrays.fill(online, true);

        PriorityQueue<Expiry> pq = new PriorityQueue<>();
        int totalHereCount = 0;
        int allCount = 0;

        int currentTime = -1;
        for (List<String> ev : events) {
            String type = ev.get(0);
            int ts = Integer.parseInt(ev.get(1));
            String content = ev.get(2);

            if (ts != currentTime) {
                currentTime = ts;
            }
            while (!pq.isEmpty() && pq.peek().time <= ts) {
                Expiry ex = pq.poll();
                int u = ex.user;
                if (!online[u]) {
                    online[u] = true;
                    lastHereSeen[u] = totalHereCount;
                }
            }

            if (type.equals("OFFLINE")) {
                int u = Integer.parseInt(content);
                if (online[u]) {
                    ans[u] += (totalHereCount - lastHereSeen[u]);
                    online[u] = false;
                }
                pq.offer(new Expiry(ts + 60, u));
            } else {
                if (content.equals("ALL")) {
                    allCount++;
                } else if (content.equals("HERE")) {
                    totalHereCount++;
                } else {
                    String[] toks = content.trim().split("\\s+");
                    for (String tok : toks) {
                        if (tok.length() >= 3 && tok.startsWith("id")) {
                            int u = Integer.parseInt(tok.substring(2));
                            ans[u]++;
                        }
                    }
                }
            }
        }
        for (int u = 0; u < numberOfUsers; u++) {
            if (online[u]) {
                ans[u] += (totalHereCount - lastHereSeen[u]);
            }
        }
        if (allCount > 0) {
            for (int u = 0; u < numberOfUsers; u++) ans[u] += allCount;
        }

        return ans;
    }
}