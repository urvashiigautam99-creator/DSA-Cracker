class Solution {
	public static int getMaximumGenerated(int n) {
		if (n == 0) return 0;
		if (n == 1) return 1;

		int[] nums = new int[n + 1];
		int count = 1; 

		for (int i = 0; i < n + 1; i++) {
			if (i == 0) {
				nums[0] = 0;
			} else if (i == 1) {
				nums[1] = 1;
			} else if (i % 2 == 0) {            
				nums[i] = nums[i / 2];
			} else {                              
				nums[i] = nums[i / 2] + nums[i / 2 + 1];
			}
			if (i >= 1) count = Math.max(count, nums[i]); 
		}

		return count;
	}
}