package com.zzh.dream.study.base.core.leetcode.editor.cn;//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：2
//解释：有两种方法可以爬到楼顶。
//1. 1 阶 + 1 阶
//2. 2 阶 
//
// 示例 2： 
//
// 
//输入：n = 3
//输出：3
//解释：有三种方法可以爬到楼顶。
//1. 1 阶 + 1 阶 + 1 阶
//2. 1 阶 + 2 阶
//3. 2 阶 + 1 阶
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 45 
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 2201 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution70 {

    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
    /**
     * 解法一：递归
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }

    /**
     * 解法二：递归
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        Map<Integer,Integer> temp = new HashMap<>();
        if (temp.containsKey(n)) {
            return temp.get(n);
        }else{
            int result = climbStairs3(n - 1) + climbStairs3(n - 2);
            temp.put(n,result);
            return result;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
