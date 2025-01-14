class Solution {

    private static boolean isInArray(int[] arr, int num, int index){
        for(int i=0; i<=index; i++){
            if(arr[i] == num){
                return true;
            }
        }
        return false;
    }

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] ans = new int[n];
        HashSet<Integer> visited = new HashSet<>();

        for(int i=0; i<n; i++){
            visited.add(A[i]);
            visited.add(B[i]);
            ans[i] = 0;
            for(int num : visited){
                if(isInArray(A, num, i) && isInArray(B, num, i)) ans[i]++;
            }
        }

        return ans;
    }
}