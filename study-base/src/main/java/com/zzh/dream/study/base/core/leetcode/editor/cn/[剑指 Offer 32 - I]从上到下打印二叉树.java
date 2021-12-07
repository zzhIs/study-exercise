package com.zzh.dream.study.base.core.leetcode.editor.cn;//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
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
// 返回： 
//
// [3,9,20,15,7]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 143 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
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
class Solution061 {
    //宽度优先遍历
//    二叉树宽度遍历
//    使用队列，一层一层的处理，每层遍历完之后，将下一层的数据存入到队列中；
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> tep = new LinkedList<>();
        tep.offer(root);
        while (!tep.isEmpty()){
            int len = tep.size();
            for (int i = 0; i <len ; i++) {
                TreeNode pop = tep.pop();
                if (pop != null) {
                    result.add(pop.val);
                    tep.offer(pop.left);
                    tep.offer(pop.right);
                }
            }
        }
        //集合转数组
//        return result.stream().mapToInt(it ->it).toArray();
        int[] finalResult = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            finalResult[i]=result.get(i);
        }
        return finalResult;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
