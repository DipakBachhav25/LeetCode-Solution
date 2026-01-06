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
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        // q.add(null);

        int maxSum = Integer.MIN_VALUE;
        int ans = 1;
        // int currSum = 0;
        int currLevel = 1;

        // This solution takes 11ms time
        // while(!q.isEmpty()){
        //     TreeNode currNode = q.remove();
        //     if(currNode == null){
        //         currLevel++;
        //         if(currSum > maxSum){
        //             maxSum = currSum;
        //             ans = currLevel;
        //         }
        //         currSum = 0;
        //         if(q.isEmpty()) break;
        //         else q.add(null);
        //     }else{
        //         currSum += currNode.val;
        //         if(currNode.left != null) q.add(currNode.left);
        //         if(currNode.right != null) q.add(currNode.right);
        //     }
        // }

        // return ans;

        // This solution takes 9ms time which is better than above solution
        while(!q.isEmpty()){
            int size = q.size();
            int currSum = 0;

            for(int i=0; i<size; i++){
                TreeNode currNode = q.poll();
                currSum += currNode.val;

                if(currNode.left != null) q.add(currNode.left);
                if(currNode.right != null) q.add(currNode.right);
            }

            if(currSum > maxSum){
                maxSum = currSum;
                ans = currLevel;
            }

            currLevel++;
        }

        return ans;
    }
}