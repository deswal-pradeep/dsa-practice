package leetcode.daily.y2026.m09;

public class LC_13_835_ImageOverlap {
    int m, n;
    public int largestOverlap(int[][] img1, int[][] img2) {
        m = img1.length;
        n = img1[0].length;
        int ans = 0;
        for(int dr = -(m-1); dr <= m-1; dr++){
            for(int dc = -(n-1); dc <= n-1; dc++){
                int count = 0;
                for(int i = 0; i < m; i++){
                    int i2 = i + dr;
                    if(i2 < 0 || i2 >= m)
                        continue;
                    for(int j = 0; j < n; j++){
                        int j2 = j + dc;
                        if(j2 < 0 || j2 >= n)
                            continue;
                        if(img1[i][j] == 1 && img2[i2][j2] == 1){
                            count++;
                            ans = Math.max(ans, count);
                        }
                    }
                }
            }
        }
        return ans;
    }
    public int largestOverlap_try1(int[][] img1, int[][] img2) {
        int ans = -1;
        m = img1.length;
        n = img1[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < m; k++){
                    for(int l = 0; l < n; l++){
                        int count1 = count(img1, img2, i, j, k, l);
                        ans = Math.max(ans, count1);
                    }
                }
            }
        }
        return ans;
    }

    int count(int[][] img1, int[][] img2, int i1, int j1, int i2, int j2){
        int cnt = 0;
        for(int i = 0; i < m-i1 && i < m-i2; i++){
            for(int j = 0; j < n-j1 && j < n-j2; j++){
                if(img1[i1+i][j1+j] == 1 && img2[i+i2][j+j2] == 1){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
