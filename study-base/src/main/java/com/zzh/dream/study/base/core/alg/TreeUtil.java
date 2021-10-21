package com.zzh.dream.study.base.core.alg;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 二叉树操作工具类
 * @author: zhangzihao
 * @date: 10/06/2021
 **/
public class TreeUtil {

    /**
     * 根据数组初始化二叉树
     * @param arr
     * @return
     */
    public static TreeNode buildTree(int[] arr){
        if (null == arr || arr.length ==0) {
            return null;
        }

        TreeNode binaryTree = new TreeNode(arr[0]);//初始化根结点
        for (int i = 1; i < arr.length; i++) {
            insert(binaryTree, arr[i]);//初始化二叉树
        }
        return binaryTree;
    }

    /**
     *二叉树的右子结点比父结点大  左子结点比父结点小
     */
    private static void insert(TreeNode root, int data) {
        if (data > root.val) {
            if (root.right == null) {
                root.right = new TreeNode(data);
            } else {
                insert(root.right, data);
            }
        } else {
            if (root.left == null) {
                root.left = new TreeNode(data);
            } else {
                insert(root.left, data);
            }
        }
    }

    /**
     * 二叉树的遍历
     */

    /**
     * 前序遍历
     * @param root
     */
    public static void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }


    /**
     * 中序遍历
     * @param root
     */
    public static void inorder(TreeNode root){
        if(root != null){
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public static void postorder(TreeNode root){
        if(root != null){
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val + " ");
        }
    }



    /**
     * 层序遍历  利用队列先进先出来进行层层遍历
     * @param root
     */
    public static void sequenceOrder(TreeNode root){
        if(root != null){
            Queue<TreeNode> queue = new LinkedBlockingQueue<>();
            TreeNode binaryTree;
            queue.add(root);//将已初始化二叉树根结点放入队列之中
            while (!queue.isEmpty()){
                binaryTree = queue.remove();
                System.out.print(binaryTree.val + " ");//每次将上次进入队列的结点输出
                if(binaryTree.left != null){
                    queue.add(binaryTree.left);
                }
                if(binaryTree.right != null){
                    queue.add(binaryTree.right);
                }
            }
        }
    }
}
