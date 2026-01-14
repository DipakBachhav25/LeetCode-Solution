class SegmentTree {
    private final int n;
    private final List<Integer> xs;
    private final int[] coveredCount;
    private final int[] coveredWidth;

    public SegmentTree(List<Integer> xs) {
        this.xs = xs;
        this.n = xs.size() - 1;
        this.coveredCount = new int[4 * n];
        this.coveredWidth = new int[4 * n];
    }

    // Adds val to the range [i, j].
    public void add(int i, int j, int val) {
        add(0, 0, n - 1, i, j, val);
    }

    // Returns the covered width of xs[0..n - 1].
    public int getCoveredWidth() {
        return coveredWidth[0];
    }

    private void add(int treeIndex, int lo, int hi, int i, int j, int val) {
        if (j <= xs.get(lo) || xs.get(hi + 1) <= i)
            return;
        if (i <= xs.get(lo) && xs.get(hi + 1) <= j) {
            coveredCount[treeIndex] += val;
        } else {
            int mid = (lo + hi) / 2;
            add(2 * treeIndex + 1, lo, mid, i, j, val);
            add(2 * treeIndex + 2, mid + 1, hi, i, j, val);
        }
        if (coveredCount[treeIndex] > 0) {
            coveredWidth[treeIndex] = xs.get(hi + 1) - xs.get(lo);
        } else if (lo == hi) {
            coveredWidth[treeIndex] = 0;
        } else {
            coveredWidth[treeIndex] = coveredWidth[2 * treeIndex + 1] + coveredWidth[2 * treeIndex + 2];
        }
    }
}

class Solution {
    public double separateSquares(int[][] squares) {
        List<Event> events = new ArrayList<>();
        TreeSet<Integer> xs = new TreeSet<>();

        for (int[] square : squares) {
            int x = square[0];
            int y = square[1];
            int l = square[2];
            events.add(new Event(y, 1, x, x + l));
            events.add(new Event(y + l, -1, x, x + l));
            xs.add(x);
            xs.add(x + l);
        }

        events.sort(Comparator.comparingInt(e -> e.y));

        double halfArea = getArea(events, xs) / 2.0;
        long area = 0;
        int prevY = 0;
        List<Integer> xsList = new ArrayList<>(xs);
        SegmentTree tree = new SegmentTree(xsList);

        for (Event e : events) {
            int coveredWidth = tree.getCoveredWidth();
            long areaGain = (long) coveredWidth * (e.y - prevY);
            if (area + areaGain >= halfArea)
                return prevY + (halfArea - area) / (double) coveredWidth;
            area += areaGain;
            tree.add(e.xl, e.xr, e.delta);
            prevY = e.y;
        }

        throw new RuntimeException("No solution found");
    }

    private long getArea(List<Event> events, TreeSet<Integer> xs) {
        long totalArea = 0;
        int prevY = 0;
        List<Integer> xsList = new ArrayList<>(xs);
        SegmentTree tree = new SegmentTree(xsList);
        for (Event e : events) {
            totalArea += (long) tree.getCoveredWidth() * (e.y - prevY);
            tree.add(e.xl, e.xr, e.delta);
            prevY = e.y;
        }
        return totalArea;
    }

    private static class Event {
        int y, delta, xl, xr;

        Event(int y, int delta, int xl, int xr) {
            this.y = y;
            this.delta = delta;
            this.xl = xl;
            this.xr = xr;
        }
    }
}