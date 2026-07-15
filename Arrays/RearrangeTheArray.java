/*
Problem: Rearrange the Array
Time Complexity: O(n√n)
Space Complexity: O(n)
Date : 14.07.2026
*/
class Solution {
    static final int MOD = 1000000007;

    int minOperations(int[] b) {
        int n = b.length;
        boolean[] visited = new boolean[n];

        // Store maximum exponent of every prime
        HashMap<Integer, Integer> primePower = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int len = 0;
                int cur = i;

                while (!visited[cur]) {
                    visited[cur] = true;
                    cur = b[cur] - 1;   // Convert to 0-based index
                    len++;
                }

                factorize(len, primePower);
            }
        }

        long ans = 1;

        for (Map.Entry<Integer, Integer> e : primePower.entrySet()) {
            int prime = e.getKey();
            int power = e.getValue();

            for (int i = 0; i < power; i++) {
                ans = (ans * prime) % MOD;
            }
        }

        return (int) ans;
    }

    void factorize(int num, HashMap<Integer, Integer> map) {
        int x = num;

        for (int p = 2; p * p <= x; p++) {
            int cnt = 0;

            while (num % p == 0) {
                cnt++;
                num /= p;
            }

            if (cnt > 0) {
                map.put(p, Math.max(map.getOrDefault(p, 0), cnt));
            }
        }

        if (num > 1) {
            map.put(num, Math.max(map.getOrDefault(num, 0), 1));
        }
    }
}
