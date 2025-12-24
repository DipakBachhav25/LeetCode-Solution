class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {

        int[] arr = new int[51];
        for(int n : capacity){
            arr[n]++;
        }

        int totalApples = 0;
        for(int n : apple) totalApples += n;

        int count = 0;

        for(int i=50; i>=1; i--){

            while(arr[i] != 0 && totalApples > 0){
                count++;
                totalApples -= i;
                arr[i]--;
            }

            if(totalApples <= 0) break;
        }

        return count;
    }
}