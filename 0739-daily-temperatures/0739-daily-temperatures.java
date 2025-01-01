class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] arr = new int[len];
        Stack<Integer> s = new Stack<>();
        arr[len-1] = 0;
        s.push(len-1);
        for(int i = len-2; i>=0; i--){
            while(!s.isEmpty() && temperatures[i] >= temperatures[s.peek()]){
                s.pop();
            }

            if(!s.isEmpty()){
                arr[i] = s.peek() - i;
            } else {
                arr[i] = 0;
            }

            s.push(i);
        }
        return arr;
    }
}