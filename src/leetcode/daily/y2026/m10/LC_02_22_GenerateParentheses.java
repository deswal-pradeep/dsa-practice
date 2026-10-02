package leetcode.daily.y2026.m10;

import java.util.ArrayList;
import java.util.List;

public class LC_02_22_GenerateParentheses {
    int n;
    public List<String> generateParenthesis(int n) {
        this.n = n;
        List<String> list = new ArrayList<>();
        char[] arr = new char[2*n];
        gen(arr, 0, 0, list);
        return list;
    }

    void gen(char[] arr, int left, int right, List<String> list){
        if(left + right == arr.length){
            list.add(new String(arr));
            return;
        }
        //2 choices put ( or ), decide the validity
        if(left < n){
            arr[left+right] = '(';
            gen(arr, left+1, right, list);
        }
        if(right < n && left > right){
            arr[left+right] = ')';
            gen(arr, left, right+1, list);
        }
    }

    static void main() {
        List<String> strings = new LC_02_22_GenerateParentheses().generateParenthesis(3);
        System.out.println(strings);
    }
}
