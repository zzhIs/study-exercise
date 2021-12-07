package com.zzh.dream.study.base.core.leetcode.editor.cn;

/**
 * @description: TODO 类描述
 * @author: zhangzihao
 * @date: 04/12/2021
 **/
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
