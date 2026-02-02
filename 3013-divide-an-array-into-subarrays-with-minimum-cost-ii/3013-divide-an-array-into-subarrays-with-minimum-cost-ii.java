class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        long baseCost = nums[0];
        int n = nums.length;
        int target = k - 2;

        if (target == 0) {
            long minVal = Long.MAX_VALUE;
            for (int i = 1; i < n; i++) {
                minVal = Math.min(minVal, (long)nums[i]);
            }
            return baseCost + minVal;
        }

        WindowMinSum window = new WindowMinSum(target);
        long minTotalCost = Long.MAX_VALUE;

        for (int j = 2; j <= Math.min(n - 1, 1 + dist); j++) {
            window.add(nums[j]);
        }

        for (int i = 1; i <= n - 1 - target; i++) {
            if (window.hasEnough()) {
                long currentCost = baseCost + nums[i] + window.getSumLow();
                minTotalCost = Math.min(minTotalCost, currentCost);
            }

            if (i + 1 < n) {
                window.remove(nums[i + 1]);
            }

            if (i + 1 + dist < n) {
                window.add(nums[i + 1 + dist]);
            }
        }

        return minTotalCost;
    }

    static class WindowMinSum {
        private int capacity;
        private TreeMap<Integer, Integer> low;
        private TreeMap<Integer, Integer> high;
        private long sumLow;
        private int sizeLow;
        private int sizeHigh;

        public WindowMinSum(int capacity) {
            this.capacity = capacity;
            this.low = new TreeMap<>();
            this.high = new TreeMap<>();
            this.sumLow = 0;
            this.sizeLow = 0;
            this.sizeHigh = 0;
        }

        public void add(int val) {
            addMap(low, val);
            sumLow += val;
            sizeLow++;

            if (sizeLow > capacity) {
                int maxLow = low.lastKey();
                removeMap(low, maxLow);
                sumLow -= maxLow;
                sizeLow--;

                addMap(high, maxLow);
                sizeHigh++;
            }

            balance();
        }

        public void remove(int val) {
            if (high.containsKey(val)) {
                removeMap(high, val);
                sizeHigh--;
            } else if (low.containsKey(val)) {
                removeMap(low, val);
                sumLow -= val;
                sizeLow--;
            }
            balance();
        }

        private void balance() {
            while (sizeLow < capacity && sizeHigh > 0) {
                int minHigh = high.firstKey();
                removeMap(high, minHigh);
                sizeHigh--;

                addMap(low, minHigh);
                sumLow += minHigh;
                sizeLow++;
            }
 
            while (sizeLow > capacity) {
                 int maxLow = low.lastKey();
                removeMap(low, maxLow);
                sumLow -= maxLow;
                sizeLow--;

                addMap(high, maxLow);
                sizeHigh++;
            }

            while (sizeLow > 0 && sizeHigh > 0 && low.lastKey() > high.firstKey()) {
                int maxLow = low.lastKey();
                int minHigh = high.firstKey();
                
                removeMap(low, maxLow);
                sumLow -= maxLow;
                
                removeMap(high, minHigh);
                
                addMap(low, minHigh);
                sumLow += minHigh;
                
                addMap(high, maxLow);
            }
        }

        public long getSumLow() {
            return sumLow;
        }

        public boolean hasEnough() {
            return sizeLow == capacity;
        }

        private void addMap(TreeMap<Integer, Integer> map, int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        private void removeMap(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
        }
    }
}