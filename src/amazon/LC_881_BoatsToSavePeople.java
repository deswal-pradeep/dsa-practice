package amazon;

import java.util.Arrays;

public class LC_881_BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int l = 0;
        int r = people.length-1;
        int ans = 0;
        while(l <= r){
            if(l == r){
                ans++;
                l++;
            } else {
                if(limit >= people[l] + people[r]){
                    l++; r--;
                    ans++;
                } else {
                    ans++;
                    r--;
                }
            }
        }
        return ans;
    }
}
