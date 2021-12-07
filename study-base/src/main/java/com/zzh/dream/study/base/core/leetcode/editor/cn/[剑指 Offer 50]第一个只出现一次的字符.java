package com.zzh.dream.study.base.core.leetcode.editor.cn;//在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
//
// 示例 1: 
//
// 
//输入：s = "abaccdeff"
//输出：'b'
// 
//
// 示例 2: 
//
// 
//输入：s = "" 
//输出：' '
// 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 50000 
// Related Topics 队列 哈希表 字符串 计数 👍 157 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution053 {
    public static char firstUniqChar(String s) {
        if (s == null || "".equals(s)) {
            return ' ';
        }
        Map<Character,Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            Integer count = 1;
            if  (map.containsKey(c)) {
                count = map.get(c) + 1;
            }
            map.put(c,count);
        }

        for (Character c : s.toCharArray()) {
            if  (map.containsKey(c) && map.get(c) == 1) {
                return c;
            }
        }
        return ' ';

    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
