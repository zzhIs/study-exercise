package com.zzh.dream.study.base.core.leetcode.editor.cn;//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。 
//
// 例如: 
//给定的树 A: 
//
// 3 
// / \ 
// 4 5 
// / \ 
// 1 2 
//给定的树 B： 
//
// 4 
// / 
// 1 
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。 
//
// 示例 1： 
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true 
//
// 限制： 
//
// 0 <= 节点个数 <= 10000 
// Related Topics 树 深度优先搜索 二叉树 👍 403 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * 思路：
 * 确认好匹配成功的出口，初始B 不等于null，匹配到最终B为null，没有报不相等就是匹配成功；
 * 匹配失败的情况：a为空，或者a和b不相等；
 * 匹配节点：初始节点，初始节点的子节点；
 * }
 */
class Solution071 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) {
            return false;
        }
        return match(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private Boolean match(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }
        return match(a.left, b.left) && match(a.right, b.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
