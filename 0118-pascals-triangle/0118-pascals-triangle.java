class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> mainList = new ArrayList<>();
        for(int i=1; i<=numRows; i++){
            int c = 1;
            List<Integer> l = new ArrayList<>();
            for(int j=1; j<=i; j++){
                l.add(c);
                c = c * (i-j)/j;
            }
            mainList.add(l);
        }

        return mainList;
    }
}