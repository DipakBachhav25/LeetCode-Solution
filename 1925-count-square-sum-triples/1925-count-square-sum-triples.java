class Solution {
    public int countTriples(int n) {
        int tripleCount = 0;
        Set<Integer> set = new HashSet<>();
        
        for (int i = 1; i <= n; i++) {
            set.add(i * i);
        }
        
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int sum = a * a + b * b;
                if (set.contains(sum)) {
                    tripleCount++;
                }
            }
        }
        
        return tripleCount;
    }
}