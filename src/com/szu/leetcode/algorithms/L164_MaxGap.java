package com.szu.leetcode.algorithms;
/*
 * @Author 郭学胤
 * @University 深圳大学
 * @Description
 *
 * 给定一个无序数组arr，返回如果排序之后，相邻最大的差值
 * {3 1 7 9}
 * 排序后的数足 为 1  3  7  9， 差值最大为 4
 *
 * 要求：不能真正的排序，并且要在 O(N) 时间复杂度解决
 *
 * @Date 2021/4/27 16:26
 */

import com.szu.leetcode.utils.LeetCodes;

import java.util.Arrays;

public class L164_MaxGap {

    private static int maxGap(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        /*
         * 找到数组中的最大值和最小值
         * */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        // 如果最大值和最小值相等，那么差值必定是0
        if (min == max)
            return 0;
        /*
         * 创建桶，需要三个数组，桶的数量为 数组长度 + 1
         * boolean[] hasNum ， 编号为 i 的桶 是否放进过数字，大小有一个放进去就算放过
         * int[] small   落入这个桶的最小值
         * int[] big     落入这个桶的最大值
         * 0号 和 arr.length 两头的桶必定有数字的
         * 最大的差值，必定出现在 跨桶之间，桶内不可能有最大差值，
         * 而且有数字的两个桶 的 最大值和最小值 必定是源数组中挨着的两个值
         * */
        int buckets = arr.length + 1;
        boolean[] hasNum = new boolean[buckets];
        int[] small = new int[buckets];
        int[] big = new int[buckets];
        int bucketId = 0;
        // 往桶里边方数字
        for (int i = 0; i < arr.length; i++) {
            /* （当前数字 - 数组最小值） * 数组长度 / （数组最大值 - 最小值） */
            bucketId = (int)getBucketId(arr[i], min, arr.length, max); /* 如何计算，数字位于哪一个桶内？ */
            small[bucketId] = hasNum[bucketId] ? Math.min(small[bucketId], arr[i]) : arr[i];
            big[bucketId] = hasNum[bucketId] ? Math.max(big[bucketId], arr[i]) : arr[i];
            hasNum[bucketId] = true;
        }
        int ans = Integer.MIN_VALUE;
        int last = big[0];
        for (int i = 1; i < buckets; i++) {
            if (hasNum[i]) {
                int cur = small[i] - last;
                last = big[i];
                if (cur > ans)
                    ans = cur;
            }
        }
        return ans;
    }

    /* （当前数字 - 数组最小值） * 数组长度 / （数组最大值 - 最小值） */
    private static long getBucketId(long curNum, long min, long length, long max) {
        return (curNum - min) * length / (max - min);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = LeetCodes.getRandomArrayWithNegative(maxSize, maxValue);
            int[] arr1 = LeetCodes.getInputArray("[601408776,63967816,431363697,242509930,15970592,60284088,228037800,147629558,220782926,55455864,456541040,106650540,17290078,52153098,103139530,294196042,16568100,426864152,61916064,657788565,166159446,1741650,101791800,28206276,6223796,524849590,125389882,84399672,153834912,164568204,1866165,283209696,560993994,16266096,219635658,9188983,485969304,782013650,120332636,44659356,444517408,36369045,47370708,18542592,98802990,137690000,124889895,56062800,265421676,309417680,4634176,801661539,510541206,258227892,398938089,47255754,152260962,409663140,102847688,45756553,377936600,269498,375738702,263761134,53797945,329493948,224442208,508336845,189507850,40944620,127879560,119629476,186894520,62409156,693721503,4289916,523899936,28955240,266488028,20356650,40769391,483694272,97988044,84102,67246047,310688630,41288643,58965588,42881432,152159462,94786355,174917835,119224652,525034376,261516,274800528,62643819,23613832,8397240,797832131,855155367,337066320,26341480,61932200,20661075,515542796,390337500,522552030,43538516,150800550,116747540,152989123,488640056,700610304,233604,344277340,21439176,9397864,16365822,73027584,453041413,197374275,157735188,15273822,187081152,379611084,865005504,223099767,80478651,377729400,186738219,34738263,16634072,112791343,99631856,119364960,477106486,583953920,624509809,188442472,294181256,213023715,146645884,149530380,497592753,132170327,72770643,126683010,405141255,590214306,26670714,95582385,162080790,231120099,8946432,204967980,592849110,54120698,375915096,602145859,5346440,226337825,425156369,653591624,578483360,572410800,32290700,381384563,149939976,183225375,155695620,38307636,457513760,97085778,75200576,8068176,221650296,556889418,252495726,895020231,19932465,156334887,191383314,348432526,368701264,14315598,148936587,279419435,237325542,252587218,322929504,26331343,355297676,600420786,652017765,51673622,159015675]");
            int[] arr2 = LeetCodes.copyArray(arr1);
//            int[] arr1 = {-3, -5, 3, -1, -2};
//            int[] arr2 = LeetCodes.copyArray(arr1);
            int my = maxGap(arr1);
            int right = comparator(arr2);
            if (my != right) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


    private static int comparator(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;
        Arrays.sort(arr);
        int maxGap = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int cur = arr[i + 1] - arr[i];
            if (cur > maxGap)
                maxGap = cur;
        }
        return maxGap;
    }

}
