class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);

        int[] lastPos = new int[100001];
        Arrays.fill(lastPos, -1);
        int maxLen = 0;

        for(int i=0; i<n; i++){
            int val = nums[i];

            int c = (val%2 == 0) ? 1:-1;
            int l = lastPos[val]+1;

            st.update(1, 0, n-1, l, i, c);
            lastPos[val] = i;

            int start = st.findFirstZero(1, 0, n-1, i);

            if(start != -1){
                maxLen = Math.max(maxLen, i-start+1);
            }
        }

        return maxLen;
    }

    static class SegmentTree{
        int[] min, max, lazy;
        int n;

        public SegmentTree(int n){
            min = new int[4*n];
            max = new int[4*n];
            lazy = new int[4*n];
        }

        public void push(int node){
            if(lazy[node] != 0){
                lazy[2 * node] += lazy[node];
                min[2 * node] += lazy[node];
                max[2 * node] += lazy[node];

                lazy[2 * node + 1] += lazy[node];
                min[2 * node + 1] += lazy[node];
                max[2 * node + 1] += lazy[node];

                lazy[node] = 0;
            }
        }

        public void update(int node, int s, int e, int l, int r, int val){
            if (l > e || r < s) return;

            if (l <= s && e <= r) {
                lazy[node] += val;
                min[node] += val;
                max[node] += val;
                return;
            }

            push(node);
            int mid = (s + e) / 2;
            update(2 * node, s, mid, l, r, val);
            update(2 * node + 1, mid + 1, e, l, r, val);

            min[node] = Math.min(min[2 * node], min[2 * node + 1]);
            max[node] = Math.max(max[2 * node], max[2 * node + 1]);
        }

        public int findFirstZero(int node, int s, int e, int limit) {
            if (s > limit) return -1;
           
            if (min[node] > 0 || max[node] < 0) return -1;

            if (s == e) {
                return (min[node] == 0) ? s : -1;
            }

            push(node);
            int mid = (s + e) / 2;

            int res = findFirstZero(2 * node, s, mid, limit);
            if (res != -1) return res;
            return findFirstZero(2 * node + 1, mid + 1, e, limit);
        }
    }
}