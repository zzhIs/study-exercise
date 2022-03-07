package com.zzh.dream.study.base.core.leetcode.editor.cn;//给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
//
// 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。 
//
// 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并
//的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
//解释：需要合并 [1,2,3] 和 [2,5,6] 。
//合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
//解释：需要合并 [1] 和 [] 。
//合并结果是 [1] 。
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0], m = 0, nums2 = [1], n = 1
//输出：[1]
//解释：需要合并的数组是 [] 和 [1] 。
//合并结果是 [1] 。
//注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -10⁹ <= nums1[i], nums2[j] <= 10⁹ 
// 
//
// 
//
// 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？ 
// Related Topics 数组 双指针 排序 👍 1291 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution88 {

    /**
     * 解法一：申请一个额外数组，使用双指针遍历数组，比较大小
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int len = m+n;
        int[] temp = new int[len];
        for (int index = 0, nums1Index = 0,nums2Index = 0;index < len-1; index ++) {
            if (nums1Index > m ) { //数组1取完 ，完全取数组2
                temp[index] = nums2[nums2Index++];
            } else if (nums2Index> n) {//数组2取完 ，完全取数组1
                temp[index] = nums1[nums1Index++];
            }else if (nums1[nums1Index] > nums2[nums2Index]) { // 数组一的数据大于数组2的数据，去数组一的数据
                temp[index] = nums2[nums2Index++];
            }else{// 数组二的数据大于数组一的数据，去数组二的数据
                temp[index] = nums1[nums1Index++];
            }
        }
    }


    /**
     * 解法三：逆序双指针，由于nums1有额外的空间且升序，直接使用nums1的尾部空间比较，不用额外的开辟空间
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n;
        for (int index = k - 1, nums1Index = m - 1, nums2Index = n - 1; index >= 0; index--) {
            if (nums1Index < 0) { // nums1已经取完，完全去nums2
                nums1[index] = nums2[nums2Index--];
            } else if (nums2Index < 0) {// nums1已经取完,则nums1不变
                break;
            } else if (nums1[nums1Index] > nums2[nums2Index]) {  // 如果num1的值大于num2的值，则取nums1的值
                nums1[index] = nums1[nums1Index--];
            } else { // 如果num2的值大于num1的值，则取nums2的值
                nums1[index] = nums2[nums2Index--];
            }
        }
    }


    /**
     * 解法一：先合并，再调用快排方法排序
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
