class Solution {
    public int[] leftRightDifference(int[] nums) {
        int leftSum = 0;
        int rightSum = 0;
        
        // Calculate the total sum of the array
        for (int num : nums) {
            rightSum += num;
        }
        
        int[] ans = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            rightSum -= nums[i];
            ans[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }
        
        return ans;
    }
}