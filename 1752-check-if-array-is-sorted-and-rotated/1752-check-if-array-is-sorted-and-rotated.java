class Solution {
    public boolean check(int[] nums) {
        int count = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // Compare current element with the next element (using modulo for wrap-around)
            if (nums[i] > nums[(i + 1) % n]) {
                count++;
            }
            
            // Optimization: If we find more than one drop point, it's definitely not sorted & rotated
            if (count > 1) {
                return false;
            }
        }
        
        return true;
    }
}