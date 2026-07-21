class Solution {
    public int maxIndexDifference(String s) {
        int n = s.length();
        int[] best = new int[26];

        for (int i = 0; i < 26; i++) {
            best[i] = -1;
        }

        int ans = -1;

        for (int i = n - 1; i >= 0; i--) {
            int ch = s.charAt(i) - 'a';

            int farthest = i;

            if (ch < 25 && best[ch + 1] != -1) {
                farthest = best[ch + 1];
            }

            best[ch] = Math.max(best[ch], farthest);

            if (ch == 0) { // character 'a'
                ans = Math.max(ans, farthest - i);
            }
        }

        return ans;
    }
}
