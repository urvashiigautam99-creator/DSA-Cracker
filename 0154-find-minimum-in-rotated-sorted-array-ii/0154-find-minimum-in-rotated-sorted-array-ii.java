class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) {
                // The minimum element is in the right half
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // The minimum element is at mid or in the left half
                right = mid;
            } else {
                // nums[mid] == nums[right]
                // We cannot determine the side, securely shrink the search space
                right--;
            }
        }
        
        // When left == right, we've pinned down the minimum element
        return nums[left];
    }
}