package com.zzh.dream.study.base.core.leetcode.editor.cn;//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：height = [4,3,2,1,4]
//输出：16
// 
//
// 示例 4： 
//
// 
//输入：height = [1,2,1]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
// Related Topics 贪心 数组 双指针 👍 3103 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution008 {

    /**
     * 双指针：
     * 步骤：1.左右指针指向左右边界
     * 2.左边小于右边，左指针++ ，右边小于左边 右边--
     * 3.记录面积最大值
     *
     * 面积==长+高；设置初始长度首尾则最大，那么每次比较的就是两个的最小高度，即决定面积的是两个高度最小的那个
     * 而移动宽度 宽读必然减小，所以，想要移动后面积增大，就必须选择高度相对小的移动，使其变大
     *
     * 变化的量初始时很多，一步一步减少变量，最后将决定变量归纳在高度较小的移动，比较面积
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        if (height.length ==1) {
            return height[0];
        }
        int start = 0, end = height.length - 1;
        int maxSize = 0;
        while (start < end) {
            int tmp = (end - start) * Math.min(height[start], height[end]);

            maxSize = Math.max(tmp, maxSize);

            if (height[start] >= height[end]) {
                end--;
            } else {
                start++;
            }
        }

        return maxSize;
    }

    /**
     * 暴力破解法超时了
     *
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int maxSize = 0;
        for (int i = 0; i < height.length - 1; i++) {
            int j = i;
            while (j < height.length) {
                int tmpSize = (j - i) * Math.min(height[i], height[j]);
                if (tmpSize > maxSize) {
                    maxSize = tmpSize;
                }
                j++;
            }
        }
        return maxSize;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
