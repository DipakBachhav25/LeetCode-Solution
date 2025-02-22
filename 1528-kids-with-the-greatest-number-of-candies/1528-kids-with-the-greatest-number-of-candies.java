class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = Arrays.stream(candies).max().getAsInt();
        List<Boolean> l = new ArrayList<>();
        for(int i=0; i<candies.length; i++){
            if((candies[i] + extraCandies) >= maxCandies) l.add(true);
            else l.add(false);
        }

        return l;
    }
}