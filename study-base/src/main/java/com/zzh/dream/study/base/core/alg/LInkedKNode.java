package com.zzh.dream.study.base.core.alg;

/**
 * @description: 链表中倒数第k个结点
 * 输入一个链表，输出该链表中倒数第k个结点。如果该链表长度小于k，请返回空。
 * 输入：{1,2,3,4,5},1  ,返回值：{5}
 *
 * 思路：定义一个链表m先跑k个节点，然后和输入的节点同时跑，当m 到最后一个节点时，原始节点为倒数k个节点
 *
 * @author: zhangzihao
 * @date: 09/06/2021
 **/
public class LInkedKNode {

    public ListNode FindKthToTail (ListNode pHead, int k) {
        if (null == pHead || k <= 0 ) {
            return null;
        }

        ListNode kNode = pHead;
        for (int i = 0; i < k ; i++) {
            if (null == kNode) {
                return null;
            }
            kNode = kNode.next;
        }

        while(kNode != null){
            pHead = pHead.next;
            kNode = kNode.next;
        }
        return pHead;
    }

      public class ListNode {
        int val;
        ListNode next = null;
        public ListNode(int val) {
         this.val = val;
        }
     }

}
