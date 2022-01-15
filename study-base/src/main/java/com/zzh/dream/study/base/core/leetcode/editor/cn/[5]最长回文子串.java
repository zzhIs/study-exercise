package com.zzh.dream.study.base.core.leetcode.editor.cn;//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
//
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4561 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution005 {
    /**
     * 思路：
     * 1.如果s[i][j]是回文串，那么s[i+1][j-1]也是回文串
     * 2.遍历字符串，
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        // cbadabc
        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                //对称的相等且 （范围小于3或者3这个串已经验证是回文）
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    //如果符合 [r]== [l] 且 [r+1][l-1]是回文串
                    // 则将[r][l]设置为true 并计算和之前的循环标记的最大长度比较，设置最大值
                    dp[l][r] = true;
                    //如果大于最大长度则更新最大长度
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
