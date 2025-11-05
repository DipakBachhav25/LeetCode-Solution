class Solution {

    static class Entry {
        int val;
        int freq;
        Entry(int v, int f) { val = v; freq = f; }
    }

    static class EliteCmp implements Comparator<Entry> {
        public int compare(Entry a, Entry b) {
            if (a.freq != b.freq) return Integer.compare(a.freq, b.freq); // lower freq is "smaller"
            return Integer.compare(a.val, b.val); // lower val is "smaller"
        }
    }

    static class WaitingCmp implements Comparator<Entry> {
        public int compare(Entry a, Entry b) {
            if (a.freq != b.freq) return Integer.compare(b.freq, a.freq); // higher freq first
            return Integer.compare(b.val, a.val); // higher val first
        }
    }


    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        if (n == 0 || k == 0) return new long[0];
        long[] ans = new long[n - k + 1];

        // frequency map for current window
        HashMap<Integer, Integer> cnt = new HashMap<>();

        // two heaps and an authoritative set of elite members
        PriorityQueue<Entry> elite = new PriorityQueue<>(new EliteCmp());     // min-heap of elite (worst at top)
        PriorityQueue<Entry> waiting = new PriorityQueue<>(new WaitingCmp()); // max-heap of waiting (best at top)
        HashSet<Integer> inElite = new HashSet<>(); // current distinct values that are in elite

        long sumElite = 0L;

        // build initial counts for first window
        for (int i = 0; i < k; ++i) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
        }

        // push all distinct values into waiting initially
        for (Map.Entry<Integer,Integer> e : cnt.entrySet()) {
            waiting.offer(new Entry(e.getKey(), e.getValue()));
        }

        // promote top x (or fewer if distinct < x) from waiting to elite
        int need = Math.min(x, cnt.size());
        while (inElite.size() < need && !waiting.isEmpty()) {
            Entry cand = pollValidWaiting(waiting, cnt, inElite);
            if (cand == null) break;
            // promote
            inElite.add(cand.val);
            elite.offer(cand);
            sumElite += (long) cand.val * cand.freq;
        }

        ans[0] = sumElite;

        // slide window
        for (int i = 1; i <= n - k; ++i) {
            int out = nums[i - 1];
            int in = nums[i + k - 1];

            // OUTGOING: decrement frequency of 'out'
            int oldOutFreq = cnt.getOrDefault(out, 0);
            if (oldOutFreq > 0) {
                int newOutFreq = oldOutFreq - 1;
                // if out is currently in elite, adjust sum immediately
                if (inElite.contains(out)) {
                    sumElite += (long) out * (newOutFreq - oldOutFreq); // = -out*oldOutFreq if removed
                }
                // update map
                if (newOutFreq == 0) cnt.remove(out);
                else cnt.put(out, newOutFreq);

                // push new snapshot into appropriate heap (lazy)
                if (newOutFreq > 0) {
                    if (inElite.contains(out)) elite.offer(new Entry(out, newOutFreq));
                    else waiting.offer(new Entry(out, newOutFreq));
                } else {
                    // newOutFreq == 0: value removed entirely; we don't push zero-frequency entries
                    // but ensure we eventually remove stale entries when popping heaps
                    // Also if it was inElite, remove it from inElite (we already adjusted sum)
                    if (inElite.contains(out)) inElite.remove(out);
                }
            }

            // INCOMING: increment frequency of 'in'
            int oldInFreq = cnt.getOrDefault(in, 0);
            int newInFreq = oldInFreq + 1;
            // if in is currently in elite, adjust sum immediately
            if (inElite.contains(in)) {
                sumElite += (long) in * (newInFreq - oldInFreq); // add in * 1
            }
            cnt.put(in, newInFreq);
            // push new snapshot
            if (inElite.contains(in)) elite.offer(new Entry(in, newInFreq));
            else waiting.offer(new Entry(in, newInFreq));

            // REBALANCE SIZE: ensure inElite.size() == min(x, distinct)
            need = Math.min(x, cnt.size());
            // promote while too small
            while (inElite.size() < need) {
                Entry cand = pollValidWaiting(waiting, cnt, inElite);
                if (cand == null) break;
                inElite.add(cand.val);
                elite.offer(cand);
                sumElite += (long) cand.val * cand.freq;
            }
            // demote while too large
            while (inElite.size() > need) {
                Entry worst = pollValidElite(elite, cnt, inElite);
                if (worst == null) break;
                inElite.remove(worst.val);
                sumElite -= (long) worst.val * worst.freq;
                // push latest snapshot into waiting (it still exists with that freq > 0)
                if (cnt.getOrDefault(worst.val, 0) > 0) waiting.offer(new Entry(worst.val, worst.freq));
            }

            // SWAP while best waiting is better than worst elite
            while (true) {
                Entry bestWaiting = peekValidWaiting(waiting, cnt, inElite);
                Entry worstElite = peekValidElite(elite, cnt, inElite);
                if (bestWaiting == null || worstElite == null) break;
                if (compareEntries(bestWaiting, worstElite) > 0) {
                    // perform swap
                    // remove them
                    bestWaiting = pollValidWaiting(waiting, cnt, inElite);
                    worstElite = pollValidElite(elite, cnt, inElite);
                    if (bestWaiting == null || worstElite == null) break;
                    // update sets and sums
                    inElite.remove(worstElite.val);
                    sumElite -= (long) worstElite.val * worstElite.freq;

                    inElite.add(bestWaiting.val);
                    sumElite += (long) bestWaiting.val * bestWaiting.freq;

                    // push swapped snapshots into heaps (lazy)
                    if (cnt.getOrDefault(worstElite.val, 0) > 0) waiting.offer(new Entry(worstElite.val, worstElite.freq));
                    elite.offer(bestWaiting);
                } else break;
            }

            ans[i] = sumElite;
        }

        return ans;
    }

    private int compareEntries(Entry a, Entry b) {
        if (a.freq != b.freq) return Integer.compare(a.freq, b.freq);
        return Integer.compare(a.val, b.val);
    }

    private Entry pollValidWaiting(PriorityQueue<Entry> waiting, HashMap<Integer,Integer> cnt, HashSet<Integer> inElite) {
        while (!waiting.isEmpty()) {
            Entry e = waiting.poll();
            int curFreq = cnt.getOrDefault(e.val, 0);
            // valid if the snapshot matches current frequency, freq>0, and value is not currently in elite
            if (curFreq == e.freq && curFreq > 0 && !inElite.contains(e.val)) return e;
            // otherwise discard stale snapshot and continue
        }
        return null;
    }

    private Entry peekValidWaiting(PriorityQueue<Entry> waiting, HashMap<Integer,Integer> cnt, HashSet<Integer> inElite) {
        while (!waiting.isEmpty()) {
            Entry e = waiting.peek();
            int curFreq = cnt.getOrDefault(e.val, 0);
            if (curFreq == e.freq && curFreq > 0 && !inElite.contains(e.val)) return e;
            // otherwise discard stale on top and continue
            waiting.poll();
        }
        return null;
    }

    private Entry pollValidElite(PriorityQueue<Entry> elite, HashMap<Integer,Integer> cnt, HashSet<Integer> inElite) {
        while (!elite.isEmpty()) {
            Entry e = elite.poll();
            int curFreq = cnt.getOrDefault(e.val, 0);
            // valid if snapshot matches current freq, freq>0, and value is currently in elite
            if (curFreq == e.freq && curFreq > 0 && inElite.contains(e.val)) return e;
            // If freq==0 but we thought it's in elite (rare), we will have removed inElite earlier when freq went 0.
            // discard stale snapshots
        }
        return null;
    }

    private Entry peekValidElite(PriorityQueue<Entry> elite, HashMap<Integer,Integer> cnt, HashSet<Integer> inElite) {
        while (!elite.isEmpty()) {
            Entry e = elite.peek();
            int curFreq = cnt.getOrDefault(e.val, 0);
            if (curFreq == e.freq && curFreq > 0 && inElite.contains(e.val)) return e;
            elite.poll();
        }
        return null;
    }
}