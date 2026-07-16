/*
Problem: Numbers with Given Digit Sum
Topic: Dynamic Programming
Date: 16.07.2026
*/


class Solution {
    int[][] dp;

    public int countWays(int n, int sum) {
        // Impossible cases
        if (sum > 9 * n || sum < 1)
            return -1;

        dp = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        int ans = 0;

        // Choose the first digit (1 to 9)
        for (int d = 1; d <= 9; d++) {
            if (sum >= d) {
                ans += solve(n - 1, sum - d);
            }
        }

        return ans == 0 ? -1 : ans;
    }

    private int solve(int digitsLeft, int sumLeft) {
        if (digitsLeft == 0)
            return sumLeft == 0 ? 1 : 0;

        if (dp[digitsLeft][sumLeft] != -1)
            return dp[digitsLeft][sumLeft];

        int ways = 0;

        for (int d = 0; d <= 9; d++) {
            if (sumLeft >= d) {
                ways += solve(digitsLeft - 1, sumLeft - d);
            }
        }

        return dp[digitsLeft][sumLeft] = ways;
    }
}
