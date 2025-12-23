class Solution {
    public int maxTwoEvents(int[][] events) {
        
        // Arrays.sort(events, (a, b) -> a[0] - b[0]);
        // int n = events.length;

        // int[] tmp = new int[n + 1];
        // for (int i = n - 1; i >= 0; i--) {
        //     tmp[i] = Math.max(tmp[i + 1], events[i][2]);
        // }

        // int maxSum = 0;

        // for (int[] row : events) {
        //     int currentVal = row[2];
        //     int currentEndTime = row[1];

        //     int firstTrueIndex = binarySearch(events, currentEndTime);

        //     if (firstTrueIndex != -1) {
        //         currentVal += tmp[firstTrueIndex];
        //     }

        //     maxSum = Math.max(maxSum, currentVal);
        // }

        // return maxSum;

        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        // Max heap stores events by end time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        int maxSingle = 0;   // best single event value seen so far
        int maxSum = 0;

        for (int[] e : events) {
            int start = e[0], end = e[1], value = e[2];

            // Pop all events that end before current start
            while (!pq.isEmpty() && pq.peek()[1] < start) {
                maxSingle = Math.max(maxSingle, pq.poll()[2]);
            }

            // Combine current event with best non-overlapping
            maxSum = Math.max(maxSum, value + maxSingle);

            // Add current event to heap
            pq.offer(e);
        }

        return maxSum;

    }

    // public int binarySearch(int[][] events, int endTime) {
    //     int l = 0, r = events.length - 1;
    //     int result = -1;

    //     while (l <= r) {
    //         int mid = l + (r - l) / 2;
    //         if (events[mid][0] > endTime) {
    //             result = mid;
    //             r = mid - 1;
    //         } else {
    //             l = mid + 1;
    //         }
    //     }

    //     return result;
    // }



}