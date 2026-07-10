package leetcode.daily.y2026.m04;

public class LC_20260415_2515_ShortestDistanceToTargetStringInACircularArray {
    //https://leetcode.com/problems/shortest-distance-to-target-string-in-a-circular-array/?envType=daily-question&envId=2026-04-15
    static void main() {
        new LC_20260415_2515_ShortestDistanceToTargetStringInACircularArray()
                .closestTarget(new String[]{"hello","i","am","leetcode","hello"}, "hello", 1);
    }
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int distance = 0;
        for(int i = 0; i < n; i++){
            int ind1 = (startIndex + i)%n;
            int ind2 = (startIndex - i + n)%n;
            if(target.equals(words[ind1]) || target.equals(words[ind2]))
                return distance;
            distance++;
        }
        return -1;
    }
}
