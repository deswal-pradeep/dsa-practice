package leetcode.daily.y2026.m10;

public class LC_21_3863_MinimumOperationsToSortAString {
    public int minOperations(String s) {
        int minIndex = 0;
        int maxIndex = 0;
        char[] arr = s.toCharArray();
        boolean isAlreadySorted = true;
        for(int i = 0; i < arr.length; i++){
            if(arr[minIndex] > arr[i])
                minIndex = i;
            if(arr[maxIndex] <= arr[i]){
                maxIndex = i;
            }
            if(i > 0 && arr[i] < arr[i-1])
                isAlreadySorted = false;
        }

        if(isAlreadySorted)
            return 0;
        if(arr.length == 2 && minIndex == arr.length - 1)
            return -1;
        int count = 0;
        if(minIndex == 0 && maxIndex == arr.length-1)
            return 1;
        if(minIndex != 0)
            count++;
        if(maxIndex != arr.length-1)
            count++;
        if(maxIndex == 0 && minIndex == arr.length-1)
            count++;
        return count;
    }

    static void main() {
        int ans = new LC_21_3863_MinimumOperationsToSortAString().minOperations("dcba");
        System.out.println(ans);
    }
}
