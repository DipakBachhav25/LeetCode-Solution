class Solution {
    public int countTrapezoids(int[][] points) {
        int n = points.length;
        if (n < 4) return 0;

        int n2 = n * (n - 1) / 2;
        long[] slopeKey   = new long[n2];
        long[] interKey   = new long[n2];
        long[] midKey     = new long[n2];
        long[] slope2Key  = new long[n2];

        int k = 0;
        for (int i = 0; i < n - 1; i++) {
            int x0 = points[i][0];
            int y0 = points[i][1];
            for (int j = i + 1; j < n; j++, k++) {
                int x1 = points[j][0];
                int y1 = points[j][1];

                int a = y1 - y0;
                int b = x0 - x1;
                int c = y0 * x1 - y1 * x0;

                if (a == 0 && b < 0) {
                    b = -b;
                    c = -c;
                } else if (a < 0) {
                    a = -a;
                    b = -b;
                    c = -c;
                }

                int gm = gcd(a, b);
                int gc = gcd(gm, c);

                int slopeNum = a / gm;
                int slopeDen = b / gm;

                int interNum = c / gc;
                int interDen = gm / gc;

                int midX = x0 + x1;
                int midY = y0 + y1;

                long slopePacked  = pack2(slopeNum, slopeDen);
                long interPacked  = pack2(interNum, interDen);
                long midPacked    = pack2(midX, midY);

                slopeKey[k]  = slopePacked;
                interKey[k]  = interPacked;
                midKey[k]    = midPacked;
                slope2Key[k] = slopePacked;
            }
        }

        sortTwoKeys(slopeKey, interKey, 0, n2 - 1);
        sortTwoKeys(midKey, slope2Key, 0, n2 - 1);

        long cnt = 0;

        {
            List<Integer> groups = new ArrayList<>();
            long curSlope = slopeKey[0];
            long curInter = interKey[0];
            int sameSlopeCount = 1;
            int sameLineCount = 1;

            for (int i = 1; i < n2; i++) {
                long s = slopeKey[i];
                long b = interKey[i];

                if (s == curSlope) {
                    sameSlopeCount++;
                    if (b == curInter) {
                        sameLineCount++;
                    } else {
                        groups.add(sameLineCount);
                        sameLineCount = 1;
                        curInter = b;
                    }
                } else {
                    groups.add(sameLineCount);
                    cnt += cntShapes(sameSlopeCount, groups) / 2;

                    groups.clear();
                    curSlope = s;
                    curInter = b;
                    sameSlopeCount = sameLineCount = 1;
                }
            }
            groups.add(sameLineCount);
            cnt += cntShapes(sameSlopeCount, groups) / 2;
        }

        {
            List<Integer> groups = new ArrayList<>();
            long curMid = midKey[0];
            long curSlope = slope2Key[0];
            int sameMidCount = 1;
            int sameSlopeCount = 1;

            for (int i = 1; i < n2; i++) {
                long mid = midKey[i];
                long s   = slope2Key[i];

                if (mid == curMid) {
                    sameMidCount++;
                    if (s == curSlope) {
                        sameSlopeCount++;
                    } else {
                        groups.add(sameSlopeCount);
                        sameSlopeCount = 1;
                        curSlope = s;
                    }
                } else {
                    groups.add(sameSlopeCount);
                    cnt -= cntShapes(sameMidCount, groups) / 2;

                    groups.clear();
                    curMid = mid;
                    curSlope = s;
                    sameMidCount = sameSlopeCount = 1;
                }
            }
            groups.add(sameSlopeCount);
            cnt -= cntShapes(sameMidCount, groups) / 2;
        }

        return (int) cnt;
    }

    private static int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        if (a == 0) return b;
        if (b == 0) return a;
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private static long pack2(int x, int y) {
        return (((long) x) << 32) ^ (y & 0xffffffffL);
    }

    private static void sortTwoKeys(long[] key1, long[] key2, int left, int right) {
        if (left >= right) return;
        int i = left;
        int j = right;
        int mid = left + ((right - left) >>> 1);
        long pivot1 = key1[mid];
        long pivot2 = key2[mid];

        while (i <= j) {
            while (compare(key1[i], key2[i], pivot1, pivot2) < 0) i++;
            while (compare(key1[j], key2[j], pivot1, pivot2) > 0) j--;
            if (i <= j) {
                swap(key1, i, j);
                swap(key2, i, j);
                i++;
                j--;
            }
        }
        if (left < j) sortTwoKeys(key1, key2, left, j);
        if (i < right) sortTwoKeys(key1, key2, i, right);
    }

    private static int compare(long a1, long b1, long a2, long b2) {
        if (a1 < a2) return -1;
        if (a1 > a2) return 1;
        return Long.compare(b1, b2);
    }

    private static void swap(long[] arr, int i, int j) {
        long tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static long cntShapes(int total, List<Integer> lineCounts) {
        long sum = 0;
        for (int x : lineCounts) {
            sum += (long) x * (total - x);
        }
        return sum;
    }
}