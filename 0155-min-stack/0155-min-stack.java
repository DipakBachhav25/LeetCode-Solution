class MinStack {
    Stack<Integer> s;
    Stack<Integer> minStack;
    public MinStack() {
        s = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        if(s.isEmpty()){
            minStack.push(val);
            s.push(val);
            return;
        }
        s.push(val);
        minStack.push(Math.min(val, minStack.peek()));
    }
    
    public void pop() {
        s.pop();
        minStack.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */