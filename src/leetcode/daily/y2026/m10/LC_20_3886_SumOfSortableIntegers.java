package leetcode.daily.y2026.m10;

public class LC_20_3886_SumOfSortableIntegers {
    static class SparseTable {
        int[][] sparse;
        int type;
        public SparseTable(int[] arr, int type){
            this.type = type;
            buildTree(arr, type);
        }

        private void buildTree(int[] arr, int type) {
            int n = arr.length;
            int k = 31 - Integer.numberOfLeadingZeros(n);
            sparse = new int[k+1][n];
            sparse[0] = arr;

            for(int j = 1; j <= k; j++){
                for(int i = 0; i+(1 << j) <= n; i++){
                    if(type == 0){
                        sparse[j][i] = Math.min(sparse[j-1][i], sparse[j-1][i+(1 << (j-1))]);
                    } else {
                        sparse[j][i] = Math.max(sparse[j-1][i], sparse[j-1][i+(1 << (j-1))]);
                    }
                }
            }
        }

        int query(int l, int r){
            int count = r - l + 1;
            int k = 31 - Integer.numberOfLeadingZeros(count);
            int ans = 0;
            if(type == 0){
                ans = Math.min(sparse[k][l], sparse[k][r-(1<<k)+1]);
            } else {
                ans = Math.max(sparse[k][l], sparse[k][r-(1<<k)+1]);
            }
            return ans;
        }

    }
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        SparseTable minTable = new SparseTable(nums, 0);
        SparseTable maxTable = new SparseTable(nums, 1);
        int[] prefix = new int[n+1];
        for(int i = 1; i < n; i++){
            prefix[i+1] = prefix[i] + (nums[i] < nums[i-1] ? 1 : 0);
        }

        int ans = 0;
        for(int k = 1; k <= n; k++){
            if(n % k != 0){
                continue;
            }
            boolean isKValid = true;
            for(int i = 0; i <= n - k; i+=k){
                int start = i;
                int end = i + k; //end exclusive
                if(start > 0){
                   int prevStart = start - k;
                   int prevEnd = start;
                   if(maxTable.query(prevStart, prevEnd-1) > minTable.query(start, end-1)){
                       isKValid = false;
                       break;
                   }
                }
                int descents = prefix[end] - prefix[start+1];
                // Include the circular boundary: last element -> first element.
                if (nums[start] < nums[end - 1]) {
                    descents++;
                }
                if(descents > 1){
                    isKValid = false;
                    break;
                }
            }
            if(isKValid)
                ans += k;
        }
        return ans;
    }

    static void main() {
        int ans = new LC_20_3886_SumOfSortableIntegers().sortableIntegers(new int[]{2,1,4,3});
        System.out.println(ans);
    }
}
