package leetcode.daily.y2026.m07;

import java.util.*;

public class LC_19_1081_SmallestSubsequenceOfDistinctCharacters {
    int n;
    public String smallestSubsequence(String s) {
        n = s.length();
        int[] indexMap = new int[26];
        for(int i = 0; i < n; i++){
            indexMap[s.charAt(i)-'a'] = i;
        }

        boolean[] isPresentInStack = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            char curr = s.charAt(i);
            if (isPresentInStack[curr - 'a'])
                continue;
            while (!stack.isEmpty()
                    && stack.peek() > curr
                    && indexMap[stack.peek() - 'a'] > i) {

                isPresentInStack[stack.pop() - 'a'] = false;
            }
            stack.push(curr);
            isPresentInStack[curr - 'a'] = true;
        }
        StringBuilder buffer = new StringBuilder();
        while(!stack.isEmpty())
            buffer.append(stack.pop());
        buffer.reverse();
        return buffer.toString();
    }

    public String smallestSubsequence_1(String s) {
        n = s.length();
        Set<Character> set = new HashSet<>();
        Map<String, String> mem = new HashMap<>();
        for(char c : s.toCharArray())
            set.add(c);
        return find(0, s, set, new StringBuffer(), mem);
    }

    String find(int ind, String s, Set<Character> set, StringBuffer picked, Map<String, String> mem){
        if(set.isEmpty()){
            return picked.toString();
        }
        if(ind == n)
            return "{";
        String key = picked.toString()+"_"+ind;
        if(mem.get(key) != null)
            return mem.get(key);
        //pick it or not pick it
        String s2 = "zz"+s;
        Character c = s.charAt(ind);
        if(set.contains(c)){
            set.remove(c);
            s2 = find(ind+1, s, set, picked.append(c), mem);
            picked.deleteCharAt(picked.length()-1);
            set.add(c);
        }
        String s1 = find(ind+1, s, set, picked, mem);
        s1 = s1.compareTo(s2) <= 0 ? s1 : s2;
        mem.put(key, s1);
        return s1;
    }

    static void main() {
        String ans = new LC_19_1081_SmallestSubsequenceOfDistinctCharacters()
                .smallestSubsequence("cbacdcbc");
        System.out.println(ans);
    }
}
