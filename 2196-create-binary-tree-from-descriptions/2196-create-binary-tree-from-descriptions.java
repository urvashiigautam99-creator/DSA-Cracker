import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        // Map to store value -> TreeNode mapping
        Map<Integer, TreeNode> map = new HashMap<>();
        // Set to track all nodes that are children
        Set<Integer> children = new HashSet<>();

        for (int[] desc : descriptions) {
            int parentVal = desc[0];
            int childVal = desc[1];
            boolean isLeft = desc[2] == 1;

            // Get or create the parent node
            map.putIfAbsent(parentVal, new TreeNode(parentVal));
            TreeNode parent = map.get(parentVal);

            // Get or create the child node
            map.putIfAbsent(childVal, new TreeNode(childVal));
            TreeNode child = map.get(childVal);

            // Connect parent to child
            if (isLeft) {
                parent.left = child;
            } else {
                parent.right = child;
            }

            // Mark this node as a child
            children.add(childVal);
        }

        // The root node is the one that is in the map but never added to children set
        for (int parentVal : map.keySet()) {
            if (!children.contains(parentVal)) {
                return map.get(parentVal);
            }
        }

        return null;
    }
}