class Solution {

    private boolean dfs(int i, int j,
                        String s,
                        String p,
                        Boolean[][] dp) {

        if (dp[i][j] != null)
            return dp[i][j];

        if (j == p.length())
            return i == s.length();

        boolean firstMatch =
                i < s.length() &&
                (s.charAt(i) == p.charAt(j)
                 || p.charAt(j) == '.');

        boolean ans;

        if (j + 1 < p.length()
                && p.charAt(j + 1) == '*') {

            ans = dfs(i, j + 2, s, p, dp)
                    || (firstMatch
                    && dfs(i + 1, j, s, p, dp));

        } else {

            ans = firstMatch
                    && dfs(i + 1, j + 1, s, p, dp);
        }

        dp[i][j] = ans;
        return ans;
    }

    public boolean isMatch(String s, String p) {

        Boolean[][] dp =
                new Boolean[s.length() + 1]
                             [p.length() + 1];

        return dfs(0, 0, s, p, dp);
    }
}