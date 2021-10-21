package com.zzh.dream.study.base.core.alg;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 二叉树中和为某一值的路径
 输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 输入：{10,5,12,4,7},22
 返回值：[[10,5,7],[10,12]]
                10
            5       12

         4      7
 *
 * @author: zhangzihao
 * @date: 09/06/2021
 **/
public class TestTreeFindPath {

    public static void main(String[] args) {
        int[] a = {10,5,12,4,7};
        findPath(TreeUtil.buildTree(a), 22).stream().forEach(list ->{
            list.stream().forEach(it -> System.out.print(it+" "));
            System.out.println("");
            System.out.println("===========================");
        });
    }

    /**
     * 本人解法，需要优化
     * 得到每一条路径，然后筛选合适的路线
     */

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (null == root || target < root.val ) {
            return result;
        }
        treeTraverse(root, new ArrayList<>(),result);
        return (ArrayList<ArrayList<Integer>>) result.stream().filter(it -> target == it.stream().reduce(0, (a, b) -> a + b).intValue())
                .sorted(Comparator.comparing(it ->it.size(),Comparator.nullsLast(Comparator.naturalOrder()))).collect(Collectors.toList());
    }

    private static void treeTraverse(TreeNode root,ArrayList<Integer> integers,ArrayList<ArrayList<Integer>> result){
        if (null == root) {
            return;
        }
        integers.add(root.val);
        if (null == root.left && null == root.right) {
            result.add(integers);
            return;
        }
        //必须使用深拷贝，避免数据干扰
        ArrayList<Integer> tempLeft = (ArrayList<Integer>) integers.clone() ,tempRight = (ArrayList<Integer>) integers.clone();
        if (null != root.left) {
            treeTraverse(root.left,tempLeft,result);
        }

        if (null != root.right) {
            treeTraverse(root.right,tempRight,result);
        }
    }



    /**
     * newCoder 解法
     */
    private static ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
    private static Stack<Integer> path = new Stack();

    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) {
            return new ArrayList<>();
        }
        find(root, target, 0);
        return paths;
    }

    private static void find(TreeNode root, int target, int cur) {
        if (cur == target && root == null) {
            paths.add(new ArrayList(path));

            return;
        }
        if (root == null) {
            return;
        }

        path.push(root.val);
        find(root.left, target, cur + root.val);
        path.pop();

        if (root.left == null && root.right == null) {
            return;
        }

        path.push(root.val);
        find(root.right, target, cur + root.val);
        path.pop();
    }

}
