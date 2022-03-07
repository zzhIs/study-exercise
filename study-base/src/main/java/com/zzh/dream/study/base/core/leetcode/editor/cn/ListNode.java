package com.zzh.dream.study.base.core.leetcode.editor.cn;

/**
 * @description: 链表数据结构
 * @author: zhangzihao
 * @date: 29/11/2021
 **/
public class ListNode {
     int val;
     ListNode next;

     public ListNode() {
     }

     ListNode(int x) { val = x; }

     public ListNode(int val, ListNode next) {
          this.val = val;
          this.next = next;
     }
}
