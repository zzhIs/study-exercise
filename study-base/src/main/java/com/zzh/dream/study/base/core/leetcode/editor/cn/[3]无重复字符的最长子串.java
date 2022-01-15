package com.zzh.dream.study.base.core.leetcode.editor.cn;//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 6714 👎 0


import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)


class Solution003 {
    /**
     * 滑动窗口
     * 遍历字符（每次基于当前字符往后遍历计算最长不重复的字符串:
     * 1.删除当前下标头字符，依次往后遍历、如果存在重复的则不合适、如果不存在长度++，直接结束或者存在重复，
     * 2.计算当前最长，开始下一次遍历i++；
     * ）
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            //右指针不会往前回退，会接着前一轮循环的下标往后移动
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    /**
     * HashMap保存char和下标，每次新增的时候判断时候存在，如果存在更新遍历数组的下标递归遍历，计算每次遍历集合最大长度返回
     * 注意点：字符串是一个空串、递归注意最大值的传参
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int result = 0;
        return s2Map(s.toCharArray(), 0, result);
    }

    private static int s2Map(char[] chars, int start, int result) {
        if (chars == null || chars.length == 0) {
            return result;
        }
        Map<Character, Integer> temp = new HashMap<>(chars.length - start);
        for (int i = start; i < chars.length; i++) {
            if (!temp.keySet().contains(chars[i])) {
                temp.put(chars[i], i);
            } else {
                int size = temp.keySet().size();
                result = result > size ? result : size;
                return s2Map(chars, temp.get(chars[i]) + 1, result);
            }
        }
        int size = temp.keySet().size();
        result = result > size ? result : size;
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
