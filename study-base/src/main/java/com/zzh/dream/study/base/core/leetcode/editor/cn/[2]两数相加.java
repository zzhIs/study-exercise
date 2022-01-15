package com.zzh.dream.study.base.core.leetcode.editor.cn;//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
// Related Topics 递归 链表 数学 👍 7317 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.net.CacheRequest;
import java.util.Stack;

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
class Solution002 {


    /**
     * 1.链表为空的默认节点值为0；
     * 2.链表的遍历，同时为空才算结束，且结束后要判断是否要新增一个节点；
     * 3.标记一个头节点，因为每次循环头结点都在变
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode pre = new ListNode(0);
        ListNode current = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {

            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            int sum = (v1 + v2) + carry;

            int value = sum % 10;
            carry = sum / 10;

            current.next = new ListNode(value);
            current = current.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry == 1) {
            current.next = new ListNode(1);
        }
        return pre.next;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
