package com.zzh.dream.study.base.core.leetcode.editor.cn;//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 👍 2905 👎 0


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution012 {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0 || s.length() % 2 == 1) {
            return false;
        }

        Map<Character, Character> rule = new HashMap<>();
        rule.put('}', '{');
        rule.put(']', '[');
        rule.put(')', '(');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (rule.containsKey(s.charAt(i))) {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (!pop.equals(rule.get(s.charAt(i)))) {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
