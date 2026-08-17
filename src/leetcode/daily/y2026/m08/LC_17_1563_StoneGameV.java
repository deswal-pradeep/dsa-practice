package leetcode.daily.y2026.m08;

public class LC_17_1563_StoneGameV {
    int[] prefix;
    int[] suffix;
    public int stoneGameV(int[] stoneValue) {
        prefix = new int[stoneValue.length+1];
        suffix = new int[stoneValue.length+1];
        for(int i = 1; i < prefix.length; i++){
            prefix[i] = stoneValue[i-1] + prefix[i-1];
        }
        return f(stoneValue, 0, stoneValue.length-1);
    }

    int f(int[] arr, int start, int end){
        if(end == start){
            return 0;
        }
        int maxScore = 0;
        for(int i = start+1; i <= end; i++){
            int score = 0;
            int psum = sum(start, i-1);
            int ssum = sum(i, end);
            if(psum == ssum){
                score = psum + Math.max(
                        f(arr, start, i-1),
                        f(arr, i, end));
            } else if (psum < ssum){
                score = psum + f(arr, start, i-1);
            } else {
                score = ssum + f(arr, i, end);
            }
            maxScore = Math.max(maxScore, score);
        }
        return maxScore;
    }

    int sum(int start, int end){
        return prefix[end+1] - prefix[start];
    }

    static void main() {
        int ans = new LC_17_1563_StoneGameV()
                .stoneGameV(new int[]{6, 2, 3, 4, 5, 5});
        System.out.println(ans);
    }
}
