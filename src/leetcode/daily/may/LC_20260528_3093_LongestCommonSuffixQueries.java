package leetcode.daily.may;

import java.util.Arrays;

//https://leetcode.com/problems/longest-common-suffix-queries/?envType=daily-question&envId=2026-05-28
public class LC_20260528_3093_LongestCommonSuffixQueries {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
        int minLength = (int)1e9;
        int index = -1;

        public void addWord(String word, int index){
            TrieNode curr = this;
            for(int i = word.length()-1; i >= 0; i--){
                char c = word.charAt(i);
                if(curr.children[c-'a'] == null){
                    curr.children[c-'a'] = new TrieNode();
                }
                if(curr.minLength > word.length()){
                    curr.minLength = word.length();
                    curr.index = index;
                }
                curr = curr.children[c-'a'];
            }
            if(curr.minLength > word.length()){
                curr.minLength = word.length();
                curr.index = index;
            }
            curr.isEnd = true;
        }

        public int findSuffix(String word){
            TrieNode curr = this;
            for(int i = word.length()-1; i >= 0; i--){
                char c = word.charAt(i);
                if(curr.children[c - 'a'] == null)
                    break;
                curr = curr.children[c - 'a'];
            }
            return curr.index;
        }
    }
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();
        for(int i = 0; i < wordsContainer.length; i++){
            root.addWord(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];
        for(int i = 0; i < wordsQuery.length; i++){
            ans[i] = root.findSuffix(wordsQuery[i]);
        }
        return ans;
    }

    static void main() {
        int[] ans = new LC_20260528_3093_LongestCommonSuffixQueries()
                .stringIndices(new String[]{"abcd","bcd","xbcd"},
                        new String[]{"cd","bcd","xyz"});
        System.out.println(Arrays.toString(ans));
    }
}
