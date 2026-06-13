package leetcode.daily.y2026.may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/separate-the-digits-in-an-array/?envType=daily-question&envId=2026-05-11
public class LC_20260511_2553_SeparateTheDigitsInAnArray {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int num : nums){
            int val = num;
            Stack<Integer> stack = new Stack<>();
            while(val > 0){
                stack.push(val % 10);
                val = val / 10;
            }
            while(!stack.empty()){
                list.add(stack.pop());
            }
        }
        int[] ans = new int[list.size()];
        for(int i = 0; i < ans.length; i++){
            ans[i] = list.get(i);
        }
        return ans;
    }

    static void main() {
        int[] ans = new LC_20260511_2553_SeparateTheDigitsInAnArray()
                .separateDigits(new int[]{13, 25, 83, 77});
        System.out.println(Arrays.toString(ans));
    }

}
