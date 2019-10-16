package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TwoSum {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode tl2 = null, tl1 = null, tls=null, tlh = null;
        int jinwei = 0;
        for (tl1 = l1, tl2 = l2; tl1!=null && tl2 != null; tl1 = tl1.next, tl2 = tl2.next) {
            if (tls == null) {
                tls = new ListNode((tl1.val + tl2.val)%10);
                tlh = tls;
                jinwei = (tl1.val + tl2.val) / 10;
            } else {
                tls.next = new ListNode((tl1.val + tl2.val + jinwei)%10);
                tls = tls.next;
                jinwei = (tl1.val + tl2.val + jinwei)/10;

            }
        }
        if (tl1 == null && tl2 != null) {
            while (tl2 != null) {
                tls.next = new ListNode((tl2.val + jinwei) % 10);
                tls = tls.next;
                jinwei = (tl2.val + jinwei) / 10;
                tl2 = tl2.next;
            }
        }
        else if (tl2 == null && tl1 != null) {
            while (tl1 != null) {
                tls.next = new ListNode((tl1.val + jinwei) % 10);
                tls = tls.next;
                jinwei = (tl1.val + jinwei) / 10;
                tl1 = tl1.next;
            }
        }
        if (jinwei != 0) {
            tls.next =  new ListNode(jinwei);
            tls = tls.next;
            tls.next = null;
        }

        return tlh;
    }
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i< nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList();
        Arrays.sort(nums);
        for (int i =0; i < nums.length; i++) {
            if (nums[i] > 0) { break;}

        }
        return null;

    }
    public static void main(String[] args) {
        HashMap<String, String> a;
       int[] nums = new int[]{2, 7, 11, 15};
       int target = 9;
       TwoSum twoSum = new TwoSum();
       int[] rtn = twoSum.twoSum(nums, target);

    }
}
