/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private long maxProductValue = 0;
    private List<Long> subtreeSums = new ArrayList<>();

    public int maxProduct(TreeNode root) {

        final int MODULO = (int) 1e9 + 7;
        long totalSum = dfs(root);

        for (long sum : subtreeSums) {
            long product = sum * (totalSum - sum);
            maxProductValue = Math.max(maxProductValue, product);
        }

        return (int) (maxProductValue % MODULO);

    }

    private long dfs(TreeNode node) {
        
        if (node == null) return 0;

        long sum = node.val + dfs(node.left) + dfs(node.right);
        subtreeSums.add(sum);
        return sum;
    }

}