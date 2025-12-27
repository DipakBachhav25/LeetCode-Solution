class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((a, b) -> 
            a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0])
        );
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) availableRooms.offer(i);

        int[] meetingCount = new int[n];
        int mostUsedRoom = 0, maxCount = 0;

        for (int[] meeting : meetings) {
            long start = meeting[0], end = meeting[1];

            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                availableRooms.offer((int) busyRooms.poll()[1]);
            }

            int room;
            if (!availableRooms.isEmpty()) {
                room = availableRooms.poll();
                busyRooms.offer(new long[]{end, room});
            } else {
                long[] earliest = busyRooms.poll();
                room = (int) earliest[1];
                long newEnd = earliest[0] + (end - start);
                busyRooms.offer(new long[]{newEnd, room});
            }

            meetingCount[room]++;
            if (meetingCount[room] > maxCount || 
               (meetingCount[room] == maxCount && room < mostUsedRoom)) {
                mostUsedRoom = room;
                maxCount = meetingCount[room];
            }
        }
        return mostUsedRoom;
    }
}