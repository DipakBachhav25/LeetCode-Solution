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
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sortedVal = new ArrayList<>();
        inOrder(root, sortedVal);

        return buildBalancedTree(0, sortedVal.size()-1, sortedVal);
    }

    public void inOrder(TreeNode root, List<Integer> sortedVal){
        if(root == null) return;

        inOrder(root.left, sortedVal);
        sortedVal.add(root.val);
        inOrder(root.right, sortedVal);
    }

    public TreeNode buildBalancedTree(int s, int e, List<Integer> sortedVal){
        if(s > e) return null;

        int mid = s + (e-s)/2;

        TreeNode node = new TreeNode(sortedVal.get(mid));
        node.left = buildBalancedTree(s, mid-1, sortedVal);
        node.right = buildBalancedTree(mid+1, e, sortedVal);

        return node;
    }
}