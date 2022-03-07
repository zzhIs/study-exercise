package com.zzh.dream.study.base.core.leetcode.editor.cn;//给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,1,2]
//输出：[1,2]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
// Related Topics 链表 👍 734 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution83 {

    /**
     * 递归
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    /**
     * 解法一:链表遍历
     * @param head
     * @return
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode currentNode = head;
        while (null != currentNode.next) {
            if (currentNode.val == currentNode.next.val) {
                currentNode.next = currentNode.next.next;
            }else{
                currentNode = currentNode.next;
            }
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
