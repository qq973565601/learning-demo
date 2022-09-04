package cn.lzx.letcode.easy;

/**
 * * <p>
 * * 两数之和
 * * <p>
 * * 输入：nums = [2,7,11,15], target = 9
 * * 输出：[0,1]
 * * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1]
 *
 * @author lzx
 * @since 2022/6/29
 */
public class Code001 {

    public static void main(String[] args) {
        //int[] nums = new int[]{2, 7, 11, 15};
        int[] nums = new int[]{2, 3, 4};
        int[] ints = twoSum(nums, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length - i; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                } else {
                    result[0] = 0;
                    result[1] = 1;
                }
            }
        }
        return result;
    }
}
