package com.zzh.dream.study.base.core.leetcode.editor.cn;//定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
//
// 
//
// 示例: 
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.min();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.min();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// 各函数的调用总次数不超过 20000 次 
// 
//
// 
//
// 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/ 
// Related Topics 栈 设计 👍 231 👎 0


import com.sun.org.apache.bcel.internal.generic.PUSH;

/**
 * 思路：两个栈，第一个保证栈的基本功能，第二个，插入的时候判断是否比之前的小，是则插入，
 * 始终保存最小值，出栈时也要验证，是否和最小值相等，相等则将最小值栈定元素出栈
 */
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

     Stack<Integer> stack = new Stack<>();
     Stack<Integer> stack2 = new Stack<>();

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public  void push(int x) {
        stack.push(x);
        if (stack2.isEmpty() || stack2.peek() >= x) {
            stack2.push(x);
        }
    }

    public  void pop() {
        Integer pop = stack.pop();
        if (pop.equals(stack2.peek())) {
            stack2.pop();
        }
    }

    public   int top() {
        return stack.peek();
    }

    public  int min() {
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        return 0;
    }

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
//leetcode submit region end(Prohibit modification and deletion)
