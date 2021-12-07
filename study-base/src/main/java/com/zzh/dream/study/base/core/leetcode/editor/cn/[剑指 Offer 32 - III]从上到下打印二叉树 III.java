package com.zzh.dream.study.base.core.leetcode.editor.cn;//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
//  [20,9],
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
// Related Topics 树 广度优先搜索 二叉树 👍 156 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution063 {
    /**
     * 此题与II 的区别在于，每一行的打印顺序交替执行。先左到右，再从右到左
     * 故得到结果之后，根据循环次数转换结果数组,也可以在新增的时候判断是偶数层则每次往下标0的位置插入(倒插法)
     * @param root
     * @return
     */
    /**
     * 解法二
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result  = new LinkedList<>();
        Deque<TreeNode> temp = new LinkedList<>();
        temp.offer(root);
        int loop = 0;
        while (!temp.isEmpty()){
            int size = temp.size();
            loop ++;
            List<Integer> nodeInteger = new LinkedList<>();
            for (int i = 0; i <size ; i++) {
                TreeNode pop = temp.pop();
                if (pop != null) {
                    if (loop %2 == 1) {
                        nodeInteger.add(pop.val);
                    }else{
                        nodeInteger.add(0,pop.val);
                    }
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

    /**
     * 解法一
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result  = new LinkedList<>();
        Deque<TreeNode> temp = new LinkedList<>();
        temp.offer(root);
        int loop = 0;
        while (!temp.isEmpty()){
            int size = temp.size();
            loop ++;
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
                result.add(loop %2 == 1 ? nodeInteger : reverse(nodeInteger));
            }
        }
        return result;
    }

    private  List<Integer> reverse(List<Integer> param){
        List<Integer> result = new ArrayList<>();
        for (int i = param.size()-1; i >= 0 ; i--) {
            result.add(param.get(i));
        }
        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
