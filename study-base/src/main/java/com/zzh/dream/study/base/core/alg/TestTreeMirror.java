package com.zzh.dream.study.base.core.alg;

/**
 * 二叉树的镜像
 * 比如：    源二叉树
 *             8
 *            /  \
 *           6   10
 *          / \  / \
 *         5  7 9 11
 *         镜像二叉树
 *             8
 *            /  \
 *           10   6
 *          / \  / \
 *         11 9 7  5
 *
 * @author: zhangzihao
 * @date: 09/06/2021
 **/
public class TestTreeMirror {

    public TreeNode Mirror (TreeNode pRoot) {
        if (null == pRoot){
            return null;
        }
        TreeNode temp = Mirror(pRoot.left);
        pRoot.left = Mirror(pRoot.right);
        pRoot.right = temp;
        return pRoot;
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
