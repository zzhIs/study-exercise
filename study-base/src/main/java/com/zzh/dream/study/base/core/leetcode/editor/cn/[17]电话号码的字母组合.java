package com.zzh.dream.study.base.core.leetcode.editor.cn;//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 👍 1680 👎 0


import cn.hutool.crypto.asymmetric.Sign;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length()==0) {
            return null;
        }
        Map<Character,String> rule = new HashMap<>();
        rule.put('2',"abc");
        rule.put('3', "def");
        rule.put('4', "ghi");
        rule.put('5', "jkl");
        rule.put('6', "mno");
        rule.put('7', "pqrs");
        rule.put('8', "tuv");
        rule.put('9', "wxyz");

        for (int i = 0; i <digits.length() ; i++) {
            char c = digits.charAt(i);
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
