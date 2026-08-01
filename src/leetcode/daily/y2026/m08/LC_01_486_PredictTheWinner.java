package leetcode.daily.y2026.m08;

public class LC_01_486_PredictTheWinner {
    public boolean predictTheWinner(int[] nums) {
        return play(nums, 0, nums.length-1, 0, 0, 1);
    }

    boolean play(int[] nums, int i, int j, int scoreA, int scoreB, int turn){
        if(i > j){
            return scoreA >= scoreB;
        }
        if(turn == 1){
            //A turn
            return play(nums, i+1, j, scoreA+nums[i], scoreB, 2)
                    | play(nums, i, j-1, scoreA+nums[j], scoreB, 2);
        } else {
            //B turn
            return  play(nums, i+1, j, scoreA, scoreB+nums[i], 1)
                    & play(nums, i, j-1, scoreA, scoreB+nums[j], 1);
        }
    }

    static void main() {
        boolean b = new LC_01_486_PredictTheWinner().predictTheWinner(new int[]{1,5,2});
        System.out.println(b);
    }
}
