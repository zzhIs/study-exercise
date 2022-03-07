package com.zzh.dream.study.base.core.leetcode.editor.cn;//给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数
//字，并以数组的形式返回结果。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [4,3,2,7,8,2,3,1]
//输出：[5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1]
//输出：[2]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 10⁵ 
// 1 <= nums[i] <= n 
// 
//
// 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。 
// Related Topics 数组 哈希表 👍 899 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution448 {
    /**
     *原值-1 对n取模 找到对应的数组 值+n，一轮后，遍历数组，值大于n的就是缺失的
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n; //对n取模还原他原本的值
            nums[x] += n;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
