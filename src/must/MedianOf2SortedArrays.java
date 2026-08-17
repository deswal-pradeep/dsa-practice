package must;

public class MedianOf2SortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return nums1.length > nums2.length
                ? median(nums1, nums2)
                : median(nums2, nums1);
    }

    double median(int[] larger, int[] smaller){
        //find the point on larger where leftCount completes
        //if we know point in larger, we know point in smaller by leftCount - medianPositionInLarger
        int leftCount = (larger.length + smaller.length - 1)/2;
        //both are sorted we can go for binary
        int l = 0;
        int r = larger.length-1;
        while(l <= r){
            int mid1 = (l + r) / 2;
            int mid2 = leftCount - mid1;
            //if(larger[mid])
        }
        return 0.0;
    }
}
