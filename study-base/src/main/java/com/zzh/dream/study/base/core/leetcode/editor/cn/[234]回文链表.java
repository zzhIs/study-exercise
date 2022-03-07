package com.zzh.dream.study.base.core.leetcode.editor.cn;//给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,2,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围[1, 10⁵] 内 
// 0 <= Node.val <= 9 
// 
//
// 
//
// 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 1265 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

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
class Solution234 {

    /**
     * 使用快慢指针，寻找中间节点，然后将后面的节点反转，再一一比较前半部分和反转后的是否相等
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head,fast = head;
        while(fast !=null && fast.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast !=null) {
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }

        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null,cur = head;
        while(cur !=null){
            //暂存原来的下一个节点
            ListNode next = cur.next;
            //修改当前节点的下一个节点
            cur.next = pre;
            //设置下一个的前驱结点
            pre = cur;
            //设置当前节点
            cur = next;
        }
        return pre;
    }

    /**
     * 使用数组存储链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
