package com.zzh.dream.study.base.core.leetcode.editor.cn;//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 栈 递归 链表 双指针 👍 208 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

/**
 * 思路：利用栈将链表翻转，再遍历
 */
class Solution021 {
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        Stack<Integer> stack = new Stack<>();
        while (head!= null) {
            stack.push(head.val);
            head = head.next;
        }
        int len = stack.size();
        int[] result = new int[len];
        for (int i = 0; i <len ; i++) {
            result[i] = stack.pop();
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
