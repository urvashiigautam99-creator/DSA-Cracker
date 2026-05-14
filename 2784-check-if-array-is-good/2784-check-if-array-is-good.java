class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        if (n < 1) return false;
        
        Arrays.sort(nums);
        
        // Check all elements up to the last two
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return false;
        }
        
        // Check if the last element is also n
        return nums[n] == n;
    }
}