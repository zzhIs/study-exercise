package com.zzh.dream.study.base.core.leetcode.editor.cn;//给你一个链表数组，每个链表都已经按升序排列。
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 链表 分治 堆（优先队列） 归并排序 👍 1703 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.concurrent.ConcurrentHashMap;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution015 {


    //递归,将多条链表递归变成两条链表，然后和合并两个链表一样的合并
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode lNode = merge(lists, start, mid);
        ListNode rNode = merge(lists, mid + 1, end);
        return merge2Lists(lNode, rNode);
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge2Lists(l1.next, l2);
            return l1;
        }
        l2.next = merge2Lists(l1, l2.next);
        return l2;
    }


    /**
     * 解题思路：每次选择数组中最小的节点，然后将节点向后移动
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode result = new ListNode(0, null);
        ListNode temp = result;
        int index = 0;
        while (index >= 0) {
            index = findMinNode(lists);
            if (index >= 0) {
                ListNode minNode = lists[index];
                lists[index] = minNode.next;
                temp.next = minNode;
                temp = temp.next;
            }
        }
        return result.next;
    }

    private int findMinNode(ListNode[] lists) {
        int min = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null && min >= lists[i].val) {
                index = i;
                min = lists[i].val;
            }
        }
        return index;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
