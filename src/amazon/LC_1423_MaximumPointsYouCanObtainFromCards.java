package amazon;

public class LC_1423_MaximumPointsYouCanObtainFromCards {
    public int maxScore(int[] cardPoints, int k) {
        int score = 0;
        int n = cardPoints.length;
        for(int i = 0; i < k; i++)
            score += cardPoints[i];

        int ans = score;
        for(int i = 0; i < k; i++){
            score = score - cardPoints[k-i-1];
            score = score + cardPoints[n-1-i];
            ans = Math.max(ans, score);
        }
        return ans;
    }
}
