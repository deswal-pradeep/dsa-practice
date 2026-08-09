package amazon;

import java.util.Arrays;

//https://leetcode.com/problems/magnetic-force-between-two-balls/
public class LC_1552_MagneticForceBetweenTwoBalls {
    boolean canPlaceBall(int[] arr, int m, int gap){
        int ballsPlaced = 1;
        int prevPosition = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] - prevPosition >= gap){
                ballsPlaced++;
                prevPosition = arr[i];
            }
            if(ballsPlaced == m)
                break;
        }
        return ballsPlaced == m;
    }
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int low = 0;
        int high = (position[n-1]/(m-1))+1;
        int ans = 0;
        while(low <= high){
            int mid = (low + high) / 2;
            if(canPlaceBall(position, m, mid)){
                ans = mid;
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return ans;
    }
}
