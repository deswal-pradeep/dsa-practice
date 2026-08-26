package leetcode.daily.y2026.m08;

import java.util.PriorityQueue;

public class LC_26_2904_ShortestAndLexicographicallySmallestBeautifulString {
    public String shortestBeautifulSubstring(String s, int k) {
        char[] a = s.toCharArray();
        String ans = "";
        int i = 0; int j = 0; int count = 0;
        while(j < a.length){
            if(a[j] == '1') count++;
            while(count == k){
                while(a[i] == 0)
                    i++;
                String sub = s.substring(i, j+1);
                ans = min(ans, sub);
                if(a[i] == '1') count--;
                i++;
            }
            j++;
        }
        return ans;
    }

    String min(String a, String b){
        if(a.isEmpty() || b.isEmpty()){
            return a.isEmpty() ? b : a;
        }
        if(a.length() == b.length()){
            return a.compareTo(b) > 0 ? b : a;
        }
        return a.length() > b.length() ? b : a;
    }

    public String shortestBeautifulSubstring_n2logN(String s, int k) {
        PriorityQueue<String> minHeap = new PriorityQueue<>((a,b) ->
                a.length() == b.length()
                        ? a.compareTo(b)
                        : Integer.compare(a.length(), b.length()));
        char[] arr = s.toCharArray();
        int[] prefix = new int[arr.length+1];
        for(int i = 1; i < prefix.length; i++){
            prefix[i] = prefix[i-1]+ (arr[i-1] == '1' ? 1 : 0);
        }

        for(int i = 0; i < arr.length-k; i++){
            for(int j = k; j <= arr.length; j++){
                int count = prefix[j] - prefix[i];
                if(count == k){
                    minHeap.offer(s.substring(i, j));
                    break;
                }
            }
        }
        return minHeap.isEmpty() ? "" : minHeap.poll();
    }

    static void main() {
        String s = new LC_26_2904_ShortestAndLexicographicallySmallestBeautifulString()
                .shortestBeautifulSubstring("100011001", 3);
        System.out.println(s);
    }
}
