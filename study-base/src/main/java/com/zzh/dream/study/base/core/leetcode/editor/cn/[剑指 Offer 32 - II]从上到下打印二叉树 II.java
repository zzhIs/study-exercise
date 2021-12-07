package com.zzh.dream.study.base.core.leetcode.editor.cn;//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-
//traversal/ 
// Related Topics 树 广度优先搜索 二叉树 👍 160 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution062 {
    /**
     * 和前一题的区别是 每层单独打印，顾每层单独使用一个结合保存即可
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result  = new LinkedList<>();
        Deque<TreeNode> temp = new LinkedList<>();
        temp.offer(root);
        while (!temp.isEmpty()){
            int size = temp.size();
            List<Integer> nodeInteger = new LinkedList<>();
            for (int i = 0; i <size ; i++) {
                TreeNode pop = temp.pop();
                if (pop != null) {
                    nodeInteger.add(pop.val);
                    temp.offer(pop.left);
                    temp.offer(pop.right);
                }
            }
            if (nodeInteger.size() > 0) {
                result.add(nodeInteger);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
