class Solution {
    public int findWays(int[][] matrix, int k) {
        final int MOD = 1_000_000_007;
        int n = matrix.length;
        int m = matrix[0].length;

        boolean[][] rowHasOne = new boolean[n][m];
        for (int row = 0; row < n; row++) {
            rowHasOne[row][m - 1] = matrix[row][m - 1] == 1;
            for (int left = m - 2; left >= 0; left--) {
                rowHasOne[row][left] = (matrix[row][left] == 1) || rowHasOne[row][left + 1];
            }
        }

        boolean[][] colHasOne = new boolean[n][m];
        for (int col = 0; col < m; col++) {
            colHasOne[n - 1][col] = matrix[n - 1][col] == 1;
            for (int top = n - 2; top >= 0; top--) {
                colHasOne[top][col] = (matrix[top][col] == 1) || colHasOne[top + 1][col];
            }
        }

        int[][] p = new int[n][m];
        for (int left = 0; left < m; left++) {
            p[n - 1][left] = rowHasOne[n - 1][left] ? (n - 1) : n;
            for (int top = n - 2; top >= 0; top--) {
                p[top][left] = rowHasOne[top][left] ? top : p[top + 1][left];
            }
        }

        int[][] q = new int[n][m];
        for (int top = 0; top < n; top++) {
            q[top][m - 1] = colHasOne[top][m - 1] ? (m - 1) : m;
            for (int left = m - 2; left >= 0; left--) {
                q[top][left] = colHasOne[top][left] ? left : q[top][left + 1];
            }
        }

        long[][] prev = new long[n][m];
        for (int top = 0; top < n; top++) {
            for (int left = 0; left < m; left++) {
                prev[top][left] = (p[top][left] < n) ? 1L : 0L; // base case r = 1: region itself must have a 1
            }
        }

        if (k == 1) return (int) (prev[0][0] % MOD);

        long[][] curr = new long[n][m];
        long[] suffixTop = new long[n + 1];
        long[] suffixLeft = new long[m + 1];

        for (int r = 2; r <= k; r++) {

                suffixTop[n] = 0;
                for (int t = n - 1; t >= 0; t--) {
                    suffixTop[t] = (suffixTop[t + 1] + prev[t][left]) % MOD;
                }
                for (int top = 0; top < n; top++) {
                    int pp = p[top][left];
                    curr[top][left] = (pp < n) ? suffixTop[pp + 1] : 0;
                }
            }


            for (int top = 0; top < n; top++) {
                suffixLeft[m] = 0;
                for (int l = m - 1; l >= 0; l--) {
                    suffixLeft[l] = (suffixLeft[l + 1] + prev[top][l]) % MOD;
                }
                for (int left = 0; left < m; left++) {
                    int qq = q[top][left];
                    long vertVal = (qq < m) ? suffixLeft[qq + 1] : 0;
                    curr[top][left] = (curr[top][left] + vertVal) % MOD;
                }
            }

            long[][] tmp = prev;
            prev = curr;
            curr = tmp;
        }

        return (int) (prev[0][0] % MOD);
    }
}
