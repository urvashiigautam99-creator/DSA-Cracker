class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        if (n == 1 || s.charAt(0) == '0') {
            return s.charAt(0) >= '1' && s.charAt(0) <= '9' ? 1 : 0;
        }

        int[] dp = new int[n];

        int d1 = s.charAt(0) - '0';
        int d2 = s.charAt(1) - '0';

        dp[0] = d1 >= 1 && d1 <= 9 ? 1 : 0;

        dp[1] += d2 >= 1 && d2 <= 9 ? 1 : 0;
        dp[1] += d1 * 10 + d2 >= 1 && d1 * 10 + d2 <= 26 && d1 != 0 ? 1 : 0;

        for (int i = 2; i < n; i++) {
            d1 = s.charAt(i - 1) - '0';
            d2 = s.charAt(i) - '0';

            int num = d1 * 10 + d2;

            int sum = (d2 >= 1 && d2 <= 9 ? dp[i - 1] : 0);
            sum += (num >= 1 && num <= 26 && d1 != 0 ? dp[i - 2] : 0);

            dp[i] = sum;

            if (dp[i] == 0) return 0;
        }

        return dp[n - 1];
    }
}