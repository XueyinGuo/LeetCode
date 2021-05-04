package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 4. 寻找两个正序数组的中位数
    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。

    示例 1：

    输入：nums1 = [1,3], nums2 = [2]
    输出：2.00000
    解释：合并数组 = [1,2,3] ，中位数 2
    示例 2：

    输入：nums1 = [1,2], nums2 = [3,4]
    输出：2.50000
    解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
    示例 3：

    输入：nums1 = [0,0], nums2 = [0,0]
    输出：0.00000
    示例 4：

    输入：nums1 = [], nums2 = [1]
    输出：1.00000
 *
 * @Date 2021/5/3 12:33
 */

public class L4_MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0))
            return 0;
        int m = nums1 == null ? 0 : nums1.length;
        int n = nums2 == null ? 0 : nums2.length;

        if (m == 0) {
            return justOneArr(nums2);


        } else if (n == 0)
            return justOneArr(nums1);
        else {
            /* 底层函数找的是第 K 大的 */
            int mid = (m + n) / 2;
            if ((m + n) % 2 != 0) {
                /* 所以这里需要的是 第 （mid + 1）大的数字 */
                return getUpMedian(nums1, nums2, mid + 1);
            } else {
                int up = getUpMedian(nums1, nums2, mid);
                int down = getUpMedian(nums1, nums2, mid + 1);
                return ((double) up + (double) down) / 2;
            }

        }
    }

    private double justOneArr(int[] nums) {
        int len = nums.length;
        int mid = len / 2;
        if (len % 2 != 0) {
            return nums[mid];
        } else
            return ((double) nums[mid - 1] + (double) nums[mid]) / 2;

    }

    public static int getUpMedian(int[] arr1, int[] arr2, int k) {

        int total = arr1.length + arr2.length;
        if (k > total)
            throw new RuntimeException("Are you out of your FUCKING mind, you just give me an invalid K !!!");
        int[] shorts = arr1.length > arr2.length ? arr2 : arr1;
        int[] longs = shorts == arr1 ? arr2 : arr1;
        /*
         * 如果 k <= shorts 的长度， 直接调用获取上中位数方法，查找 0 --- k-1 的上中位数
         * */
        int sLen = shorts.length;
        int lLen = longs.length;
        if (k <= shorts.length) {
            return getUpMedian(shorts, longs, 0, k - 1, 0, k - 1);
        } else if (k > sLen && k <= longs.length) {
            /*
             * 此时需要寻找的 k 比短数组长， 比长数组短，
             * 比如 k = 13
             *
             *
             * 【即使干过了短数组中全部，这也是顶多第12 13】
             * 这俩不可能                                                            这俩也不可能【在自己数组中就已经是 第 14 15 了，不能是整体的 第 13 了】
             * |    |                                                               |      |
             * 1‘   2’   3‘   4’   5‘   6’   7‘   8’   9‘   10’   11‘   12’   13‘   14’   15‘
             * 1    2    3    4    5    6    7    8    9    10
             *
             * 此时长数组中剩下 11 个位置， 短数组剩下 10 个全部，
             * 手动比较一下 3’ 位置 与 10 位置的值， 如果 3‘ 大，直接返回
             *
             * 否则除掉 3’   调用 等长数组的获取上中位数的方式
             * */
            if (longs[k - sLen - 1] > shorts[sLen - 1])
                return longs[k - sLen - 1];
            return getUpMedian(shorts, longs, 0, sLen - 1, k - sLen, k - 1);

        } else {
            // k > long.length
            /*
             * 此时需要寻找的 k  比长数组长，
             * 比如 k = 19
             *
             *
             * 这八个不可能    【即使干过了短数组中全部，这也是顶多第 18】
             * |    |    |    |    |    |    |    |
             * 1‘   2’   3‘   4’   5‘   6’   7‘   8’   9‘   10’   11‘   12’   13‘   14’   15‘
             * 1    2    3    4    5    6    7    8    9    10
             * |    |    |
             * 这三个不可能 【即使干过了长数组全部，也顶多是 18】
             *
             * =====================================================================================
             * =====================================================================================
             * =====================================================================================
             * 这个时候两个数组剩余部分继续等长了 ，但是一个尴尬的情况是 ：
             * 此时已经删除了 11 个数了，剩下的两个等长区域加起来也就 14 个数， 14 个数的上中位数 是 第 7 个数字，所以
             * 11 + 7 = 18 ！= 19
             * 所以此时求出了上中位数 仍然是不正确的答案！！！
             *
             * 然后应该做的就是继续手动比较两个位置
             * 9‘ 位置 > 10 ? 返回 9’ 就是 第19 大
             * 4 位置 > 15‘ ？ 返回 4 ，就是第 19 大
             *
             * 如果都不成立，继续扣掉两个位置，就删除了 13 个数了，从剩下的 12 个数中求上中位数就是 第6 大的， 13 + 6 = 19
             * 搞定
             * =====================================================================================
             * =====================================================================================
             * =====================================================================================
             * */
            if (longs[k - sLen - 1] >= shorts[sLen - 1])
                return longs[k - sLen - 1];
            if (shorts[k - lLen - 1] >= longs[lLen - 1])
                return shorts[k - lLen - 1];
            return getUpMedian(shorts, longs, k - lLen, sLen - 1, k - sLen, lLen - 1);

        }

    }


    public static int getUpMedian(int[] arr1, int[] arr2, int start1, int end1, int start2, int end2) {
        int length = arr1.length;
//        int start1 = 0, start2 = 0;
//        int end1 = length - 1, end2 = length - 1;
        int mid1, mid2;
        while (start1 < end1) { /* 在迭代中肯定能保证两个数组等长 */
            mid1 = start1 + (end1 - start1) / 2;
            mid2 = start2 + (end2 - start2) / 2;
            if (arr1[mid1] == arr2[mid2])
                return arr1[mid1];
            /* 偶数个元素的时候 */
            if ((start1 - end1 + 1) % 2 == 0) {
                /*
                 * 两个数组中 第一个数组中位数 > 第二个数组中位数
                 * */
                if (arr1[mid1] > arr2[mid2]) {
                    end1 = mid1;
                    start2 = mid2 + 1;
                } else if (arr1[mid1] < arr2[mid2]) {
                    start1 = mid1 + 1;
                    end2 = mid2;
                }
            } else {
                /* 奇数个元素的时候 */
                /*
                 * 两个数组中 第一个数组中位数 > 第二个数组中位数
                 * */
                if (arr1[mid1] > arr2[mid2]) {
                    if (arr2[mid2] >= arr1[mid1 - 1]) // 手动扣一下， 满足条件直接返回
                        return arr2[mid2];
                    end1 = mid1 - 1;
                    start2 = mid2 + 1;

                } else if (arr1[mid1] < arr2[mid2]) {
                    if (arr1[mid1] >= arr2[mid2 - 1]) // 手动扣一下  满足条件直接返回
                        return arr1[mid1];
                    start1 = mid1 + 1;
                    end2 = mid2 - 1;
                }
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }


    public static void main(String[] args) {
        int arr1[] = {1, 3};
        int arr2[] = {2};
        L4_MedianOfTwoSortedArrays test = new L4_MedianOfTwoSortedArrays();
        test.findMedianSortedArrays(arr1, arr2);
    }
}
