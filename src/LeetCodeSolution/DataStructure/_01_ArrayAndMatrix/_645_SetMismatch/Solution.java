package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._645_SetMismatch;

import java.util.Arrays;
import java.util.HashMap;

/*
 * 错误的集合
 *
 * 题目描述：
 * 集合 S 包含从 1 到 n 的整数。
 * 不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 思路：
 * 1. 可以先进行排序，然后再进行查找，时间复杂度为 O(nlogn)；
 * 2. 也可以在时间复杂度为 O(n)，空间复杂度为 O(1) 内完成求解；
 * 3. 该题和剑指 Offer 中的某个题思想是一样的，都是通过交换数组中的元素，使得数组落在正确的位置上；
 * 4. 当然，还可以使用 map 存储当前元素以及当前元素出现的次数；
 * 5. 再次遍历数组，如果当前遍历到的元素存在于 map 中，则判断它出现的次数；
 *                 如果当前遍历到的元素不存在 map 中，则就是缺失的元素。
 */
public class Solution {
    public int[] findErrorNums1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] res = new int[2];
        for (int i = 1; i <= nums.length; i++) {
            if (map.containsKey(i)) {
                // 首先找到重复出现的整数
                if (map.get(i) == 2) {
                    // 这里使用 i 的原因是：数组中的元素是在 1 到 n 范围内的
                    res[0] = i;
                }
            } else {
                // 然后找到丢失的整数
                res[1] = i;
            }
        }
        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        for (int i = 0; i < nums.length; i++) {
            // 由于数字的范围是 1 到 n，而索引的范围是 0 到 n-1，因此格外注意
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 2, 4};

        System.out.println(Arrays.toString(solution.findErrorNums1(nums)));
        System.out.println(Arrays.toString(solution.findErrorNums2(nums)));
    }
}
