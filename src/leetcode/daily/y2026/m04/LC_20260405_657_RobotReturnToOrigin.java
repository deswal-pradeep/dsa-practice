package leetcode.daily.y2026.m04;

class LC_20260405_657_RobotReturnToOrigin {
    static void main() {
        System.out.println(new LC_20260405_657_RobotReturnToOrigin().judgeCircle("UD"));
        System.out.println(new LC_20260405_657_RobotReturnToOrigin().judgeCircle("LL"));
    }
    public boolean judgeCircle(String moves) {
        int[] counter = new int[2];
        for(char c : moves.toCharArray()){
            if(c == 'L'){
                counter[0]--;
            } else if (c == 'R') {
                counter[0]++;
            } else if (c == 'U') {
                counter[1]++;
            } else {
                counter[1]--;
            }
        }
        return counter[0] == 0 && counter[1] == 0;
    }
}
