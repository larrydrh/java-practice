package slidingwindow;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length < k) {
            return new int[0];
        }
        int start = 0;
        int[] result = new int[nums.length-k+1];
        int j=0;
        int max;
        for (int end = k-1; end < nums.length; end++, start++) {
            max = nums[start];
            for (int b = start+1; b<= end; b++) {
                if (nums[b] > max) {
                    max = nums[b];
                }
            }
            result[j++] = max;
        }
        return result;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        Solution solution = new Solution();
        int[] result = solution.maxSlidingWindow(nums, k);
        System.out.println(result);
//        输出: [3,3,5,5,6,7]
    }
}
