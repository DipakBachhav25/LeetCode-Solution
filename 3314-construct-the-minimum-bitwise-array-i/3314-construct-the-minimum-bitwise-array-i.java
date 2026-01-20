// class Solution {
//     public int[] minBitwiseArray(List<Integer> nums) {
//         int n = nums.size();
//         int[] ans = new int[n];

//         for (int i = 0; i < n; i++) {
//             int curr = nums.get(i);
//             if (curr == 2) {
//                 ans[i] = -1;
//             } else {
//                 ans[i] = curr - Integer.lowestOneBit(curr);
//             }
//         }

//         return ans;

//     }
// }

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int y = nums.get(i);

            // Special case: no valid answer exists for 2
            if (y == 2) {
                result[i] = -1;
                continue;
            }

            // Ignore bit0, find first zero from bit1 upward
            int shifted = y >>> 1;
            int p = Integer.numberOfTrailingZeros(~shifted); // count of trailing ones in (y >> 1)

            // Flip bit (p) in y (which is bit (j-1) with j = p + 1)
            result[i] = y ^ (1 << p);
        }

        return result;
    }
}