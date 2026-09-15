package leetcode.daily.y2026.m09;

public class LC_15_2472_MaximumNumberOfNonOverlappingPalindromeSubstrings {
    public int maxPalindromes(String s, int k) {
        char[] arr = s.toCharArray();
        int ans = 0;
        for(int i = 0; i <= arr.length - k;){
            //check palindrome with length k and k+1;
            //try with len k
            int start = i;
            int end = i + k - 1;
            if(isPalindrome(arr, start, end)){
                i = i + k;
                ans++;
            }
            //try with len k+1
            else if(isPalindrome(arr, start, end+1)) {
                i = i + k + 1;
                ans++;
            } else {
                i++;
            }
        }
        return ans;
    }

    boolean isPalindrome(char[] arr, int left, int right){
        if(left < 0 || right > arr.length-1)
            return false;
        while(right > left){
            if(arr[left] != arr[right])
                return false;
            left++;
            right--;
        }
        return true;
    }

    static void main() {
        int ans = new LC_15_2472_MaximumNumberOfNonOverlappingPalindromeSubstrings()
                .maxPalindromes("abaccdbbd", 3);
        System.out.println(ans);
    }
}
