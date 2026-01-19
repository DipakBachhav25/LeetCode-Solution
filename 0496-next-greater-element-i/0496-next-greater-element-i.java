class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // int n = nums2.length;
        // Stack<Integer> st = new Stack<>();
        // Map<Integer, Integer> map = new HashMap<>();
        // int[] tmp = new int[n];
        // st.push(n-1);
        // tmp[n-1] = -1;
        // map.put(nums2[n-1], n-1);

        // for(int i=n-2; i>=0; i--){
        //     int curr = nums2[i];
        //     map.put(curr, i);

        //     while(!st.isEmpty() && curr >= nums2[st.peek()]){
        //         st.pop();
        //     }

        //     if(st.isEmpty()){
        //         tmp[i] = -1;
        //     }else{
        //         tmp[i] = nums2[st.peek()];
        //     }
        //     st.push(i);
        // }

        // for(int i=0; i<nums1.length; i++){
        //     int t = nums1[i];
        //     nums1[i] = tmp[map.get(t)];
        // }

        // return nums1;

        int n = nums2.length;
        Map<Integer, Integer> nextGreater = new HashMap<>(n*2);
        Deque<Integer> st = new ArrayDeque<>();

        for(int num : nums2){
            while(!st.isEmpty() && num > st.peek()){
                nextGreater.put(st.pop(), num);
            }
            st.push(num);
        }
        while(!st.isEmpty()) nextGreater.put(st.pop(), -1);

        for(int i=0; i<nums1.length; i++){
            nums1[i] = nextGreater.get(nums1[i]);
        }

        return nums1;
    }
}