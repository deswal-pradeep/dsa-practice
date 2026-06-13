package leetcode.daily.y2026.may;

import java.util.Arrays;

//https://leetcode.com/problems/jump-game-v/?envType=daily-question&envId=2026-05-24
public class LC_20260524_1340_JumpGameV {
    int n;
    public int maxJumps(int[] arr, int d) {
        n = arr.length;
        int ans = 0;
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        for(int ind = 0; ind < n; ind++){
            ans = Math.max(ans, maxJ(arr, d, ind, mem));
            //System.out.println(ans+", "+ind);
        }
        return ans;
    }

    int maxJ(int[] arr, int d, int ind, int[] mem){
        if(mem[ind] != -1)
            return mem[ind];
        int steps = 1;
        for(int i = 1; i <= d; i++){
            if(ind+i < n && arr[ind] > arr[ind + i]){
                steps = Math.max(steps, 1 + maxJ(arr, d, ind+i, mem));
            } else {
                break;
            }
        }

        for(int i = 1; i <= d; i++){
            if(ind-i >= 0 && arr[ind] > arr[ind - i]){
                steps = Math.max(steps, 1 + maxJ(arr, d, ind-i, mem));
            } else{
                break;
            }
        }
        mem[ind] = steps;
        return steps;
    }

    static void main() {
        int ans = new LC_20260524_1340_JumpGameV()
                .maxJumps(new int[]{6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}, 2);
        System.out.println(ans);
    }
}
