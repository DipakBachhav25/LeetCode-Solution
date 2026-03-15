class Fancy {

    int MOD = 1000000007;
    long mult;
    long add;
    List<Integer> seq;

    public Fancy() {
        mult = 1;
        add = 0;
        seq = new ArrayList<>();
    }
    
    public void append(int val) {
        long v = (val-add)%MOD;
        if(v < 0) v += MOD;
        v = (v*modInverse(mult))%MOD;
        seq.add((int) v);
    }
    
    public void addAll(int inc) {
        add = (add+inc)%MOD;
    }
    
    public void multAll(int m) {
        mult = (mult*m)%MOD;
        add = (add*m)%MOD;
    }
    
    public int getIndex(int idx) {
        if(idx >= seq.size()) return -1;
        long storeVal = seq.get(idx);
        long actualVal = (storeVal*mult+add)%MOD;

        return (int) actualVal;
    }

    public long power(long base, long exp){
        long ans = 1;
        base %= MOD;
        while(exp > 0){
            if(exp%2 == 1) ans = (ans*base)%MOD;
            base = (base*base)%MOD;
            exp /= 2;
        } 

        return ans;   
    }

    public long modInverse(long n){
        return power(n, MOD-2);
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy obj = new Fancy();
 * obj.append(val);
 * obj.addAll(inc);
 * obj.multAll(m);
 * int param_4 = obj.getIndex(idx);
 */