package leetcode.daily.y2026.m07;

import java.util.Arrays;

public class LC_29_3518_SmallestPalindromicRearrangementII {
    int len;
    public String smallestPalindrome(String s, int k) {
        len = s.length();
        String substring = s.substring(0, len/2);
        String kthPermute = kthPermutation(substring, k);
        if(kthPermute.isEmpty() && !substring.isEmpty())
            return "";
        StringBuilder ans = new StringBuilder(kthPermute);
        StringBuilder back = new StringBuilder(kthPermute).reverse();
        if(len %2 != 0){
            ans.append(s.charAt(len/2));
        }
        ans.append(back);
        return ans.toString();
    }

    private String kthPermutation(String s, long k) {

        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        if (count(cnt, k) < k) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (int pos = 0; pos < s.length(); pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (cnt[ch] == 0) {
                    continue;
                }

                cnt[ch]--;

                long ways = count(cnt, k);

                if (ways >= k) {
                    ans.append((char) ('a' + ch));
                    break;
                }

                k -= ways;
                cnt[ch]++;
            }
        }

        return ans.toString();
    }

    // Counts distinct permutations, capped at limit.
    private long count(int[] freq, long limit) {

        int total = 0;
        for (int f : freq) {
            total += f;
        }

        long ans = 1;
        int rem = total;

        for (int f : freq) {
            if (f == 0) continue;

            ans = multiplyCap(ans, nCrCap(rem, f, limit), limit);
            if (ans >= limit) {
                return limit;
            }
            rem -= f;
        }

        return ans;
    }
    private long nCrCap(int n, int r, long limit) {

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            long num = n - r + i;
            long den = i;

            long g = gcd(num, den);
            num /= g;
            den /= g;

            g = gcd(res, den);
            res /= g;
            den /= g;

            if (res > limit / num) {
                return limit;
            }

            res *= num;
            res /= den;

            if (res >= limit) {
                return limit;
            }
        }

        return res;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private long multiplyCap(long a, long b, long cap) {
        if (a == 0 || b == 0) return 0;
        if (a > cap / b) return cap;
        return a * b;
    }

    static void main() {
        //String ans = new LC_29_3518_SmallestPalindromicRearrangementII().smallestPalindrome("axcyzzxzzycxa", 18);
        String ans = new LC_29_3518_SmallestPalindromicRearrangementII()
                .smallestPalindrome("legbxfuoquyhtgoxabyguyrggkqgwxpdbwitguothurajgzwnfkxfsxwharovnunuygjvewlikrmfiymolsftsklhyheagyveusdnmexwwxemndsuevygaehyhlkstfslomyifmrkilwevjgyununvorahwxsfxkfnwzgjaruhtougtiwbdpxwgqkggryugybaxogthyuqoufxbgel",
                        641053);
        boolean result = "aaaabbbddeeeeefffffgggggggggghhhhhiiijjkkkkllllmmmnnnnooooopqqrrrrssssttttuuuuuuuuvvvwwwwxxxyyyyyxxxwyzywwyzywxxxyyyyyxxxwwwwvvvuuuuuuuuttttssssrrrrqqpooooonnnnmmmllllkkkkjjiiihhhhhggggggggggfffffeeeeeddbbbaaaa".equals(ans);
        System.out.println(ans);
        System.out.println(result);
    }
}
