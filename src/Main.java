import java.util.*;

class Solution {
    int MOD = 1_000_000_007;
    List<Integer>[] map = new List[32];

    static void main() {
        System.out.println(new Solution().countArrays(new int[]{1}));
    }
    public int countArrays(int[] digitSum) {
        int n = digitSum.length;
        int[][] mem = new int[n][5002];

        for(int i = 0; i < n; i++)
            Arrays.fill(mem[i], -1);
        for(int i = 0; i < map.length; i++)
            map[i] = new ArrayList<>(100);

        for(int i = 0; i <= 5000; i++)
            map[digitSum(i)].add(i);

        int answer = find(n-1, digitSum, 5001, mem);
        return answer;
    }

    int find(int ind, int[] digitSum, int lastPick, int[][] mem){
        if(ind < 0)
            return 1;
        if(mem[ind][lastPick] != -1)
            return mem[ind][lastPick];
        int ans = 0;
        List<Integer> currDigitList = digitSum[ind] > 31
                ? Collections.emptyList() :
                map[digitSum[ind]];
        //find highest index which has val <= lastPick
        int uIndex = findUB(currDigitList, lastPick);
        for(int i = uIndex; i < uIndex; i++){
            ans =  ans + find(ind-1, digitSum, currDigitList.get(i), mem);
            ans %= MOD;
        }
        mem[ind][lastPick] = ans;
        return ans;
    }

    int findUB(List<Integer> currDigitList, int val){
        int low = 0;
        int high = currDigitList.size();
        while(low < high){
            int mid = (low + high) / 2;
            if(currDigitList.get(mid) > val){
                high = mid;
            } else {
                low = mid+1;
            }
        }
        return low;
    }

    int digitSum(int val){
        int dSum = 0;
        while(val > 0){
            dSum = dSum + (val % 10);
            val = val / 10;
        }
        return dSum;
    }
}