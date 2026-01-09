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

    int maxDepth = -1;
    TreeNode ans = null;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        postOrder(root, 0);
        return ans;
    }

    public int postOrder(TreeNode root, int depth){
        if(root == null) return depth;

        int l = postOrder(root.left, depth+1);
        int r = postOrder(root.right, depth+1);

        if(l == r){
            maxDepth = Math.max(maxDepth, r);
            if(maxDepth == r) ans = root;
        }

        return Math.max(l, r);
    }
}