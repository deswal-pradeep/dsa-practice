package leetcode.daily.y2026.m06;

//https://leetcode.com/problems/weighted-word-mapping/?envType=daily-question&envId=2026-06-13
public class LC_20260613_3838_WeightedWordMapping {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder builder = new StringBuilder();
        for(String w  : words){
            int weight = 0;
            char[] arr = w.toCharArray();
            for(char c : arr){
                weight += weights[c-'a'];
            }
            weight %= 26;
            char y = (char)('z' - weight);
            builder.append(y);
        }
        return builder.toString();
    }

    static void main() {
        String[] words = new String[]{"abcd","def","xyz"};
        int[] weights = new int[]{5,3,12,14,1,2,3,2,10,6,6,9,7,8,7,10,8,9,6,9,9,8,3,7,7,2};
        String answerString = new LC_20260613_3838_WeightedWordMapping()
                .mapWordWeights(words, weights);
        System.out.println(answerString);
    }
}
