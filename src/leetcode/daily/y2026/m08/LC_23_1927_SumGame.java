package leetcode.daily.y2026.m08;

public class LC_23_1927_SumGame {
    public boolean sumGame(String num) {
        int leftSum = 0;
        int rightSum = 0;
        int leftCount = 0;
        int rightCount = 0;
        char[] arr = num.toCharArray();
        for(int i = 0; i < arr.length / 2; i++){
            int idx1 = i;
            int idx2 = arr.length - 1 - i;
            if(arr[idx1] == '?'){
                leftCount++;
            } else {
                leftSum += arr[idx1] - '0';
            }
            if(arr[idx2] == '?'){
                rightCount++;
            } else {
                rightSum += arr[idx2] - '0';
            }
        }
        if((leftCount + rightCount) % 2 == 0){
            int diffCount = Math.abs(leftCount - rightCount);
            int diffSum = Math.abs(leftSum - rightSum);
            if((leftSum > rightSum && leftCount > rightCount )||
                    (leftSum < rightSum && leftCount < rightCount)) {
                return true;
            } else if (leftSum == rightSum && leftCount == rightCount) {
                return false;
            } else {
                return diffCount / 2 * 9 != diffSum;
            }
        } else {
            return true;
        }
    }

    static void main() {
        boolean b = new LC_23_1927_SumGame().sumGame("25??");
        System.out.println(b);
    }
}
