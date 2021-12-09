package com.zzh.dream.study.base.core.leetcode.editor.cn;//请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。 
//
// 1 
// / \ 
// 2 2 
// / \ / \ 
//3 4 4 3 
//但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的: 
//
// 1 
// / \ 
// 2 2 
// \ \ 
// 3 3 
//
// 
//
// 示例 1： 
//
// 输入：root = [1,2,2,3,4,4,3]
//输出：true
// 
//
// 示例 2： 
//
// 输入：root = [1,2,2,null,3,null,3]
//输出：false 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 1000 
//
// 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 265 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution073 {
    /**
     * 思路：
     * 1.每一层的节点，使用栈和使用队列最终得到的节点相同--没有实现
     * 2.node.left().var == node.right().var
     * and node.left().left() == node.right().right
     * and node.right().left() == node.left().right
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return match(root.left, root.right);

    }

    private Boolean match(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        return (left != null && right != null) && (left.val == right.val)
                && match(left.left,right.right) && match(left.right,right.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
