package com.zzh.dream.study.base.core.leetcode.editor.cn;//定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//
// 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/ 
// Related Topics 递归 链表 👍 336 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 *思路，使用两个新的节点，一个pre  一个tep  pre用来指向前一个节点，tep用来保存当前节点的下一个节点
 */
class Solution022 {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        while(head!=null){
            //临时节点保存原链表下一个节点
            ListNode tmp = head.next ;

            //原头结点指向前一个节点
            head.next  = pre;
            //
            pre = head;
            //当前节点向原链表的下一个节点移动
            head = tmp;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
