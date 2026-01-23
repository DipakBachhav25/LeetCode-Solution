class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;

        // Use long for values to prevent integer overflow when summing
        long[] val = new long[n];
        
        // Simulating Doubly Linked List using arrays
        int[] next = new int[n];
        int[] prev = new int[n];
        
        // To track removed indices
        boolean[] removed = new boolean[n];

        // Initialize DLL and Count Inversions
        int badCount = 0;
        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            next[i] = i + 1;
            prev[i] = i - 1;
            
            // Check inversion with the next element
            if (i < n - 1 && val[i] > nums[i + 1]) {
                badCount++;
            }
        }
        next[n - 1] = -1; // End of list marker

        // If already sorted, return 0
        if (badCount == 0) return 0;

        // Priority Queue stores long[]{sum, left_index}
        // Orders by Sum (asc), then Index (asc) for leftmost tie-breaking
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            int cmp = Long.compare(a[0], b[0]);
            if (cmp != 0) return cmp;
            return Long.compare(a[1], b[1]);
        });

        // Fill PQ with initial pairs
        for (int i = 0; i < n - 1; i++) {
            pq.offer(new long[]{val[i] + val[i + 1], i});
        }

        int ops = 0;

        // Simulation Loop
        while (badCount > 0 && !pq.isEmpty()) {
            long[] top = pq.poll();
            long sum = top[0];
            int i = (int) top[1];

            // --- Lazy Removal Validation ---
            // Check if 'i' was removed
            if (removed[i]) continue;
            
            // Check if 'next[i]' was removed or invalid
            int j = next[i];
            if (j == -1 || removed[j]) continue;
            
            // Check if the sum is up-to-date (values might have changed due to neighbor merges)
            if (val[i] + val[j] != sum) continue;

            // --- Perform Merge Operation ---
            ops++;

            // Identify neighbors
            int p = prev[i]; // Left neighbor of i
            int k = next[j]; // Right neighbor of j

            // 1. Remove influence of old pairs on badCount
            // Pre-merge: Check (p, i)
            if (p != -1 && val[p] > val[i]) badCount--;
            // Pre-merge: Check (i, j) -> this is the pair we are merging
            if (val[i] > val[j]) badCount--;
            // Pre-merge: Check (j, k)
            if (k != -1 && val[j] > val[k]) badCount--;

            // 2. Update Structure (Merge j into i)
            val[i] = sum;       // i takes the new sum
            removed[j] = true;  // j is removed
            next[i] = k;        // Link i to k
            if (k != -1) prev[k] = i; // Link k back to i
            // prev[i] remains p

            // 3. Add influence of new pairs on badCount
            // Post-merge: Check (p, i) with new val[i]
            if (p != -1 && val[p] > val[i]) badCount++;
            // Post-merge: Check (i, k) with new val[i] and new neighbor k
            if (k != -1 && val[i] > val[k]) badCount++;

            // 4. Push new potential pairs to PQ
            // New pair (p, i)
            if (p != -1) {
                pq.offer(new long[]{val[p] + val[i], p});
            }
            // New pair (i, k)
            if (k != -1) {
                pq.offer(new long[]{val[i] + val[k], i});
            }
        }

        return ops;
    }
}