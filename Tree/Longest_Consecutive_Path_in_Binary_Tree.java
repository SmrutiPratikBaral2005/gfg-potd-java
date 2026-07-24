class Solution {
    int maxLen = 1;

    public int longestConsecutive(Node root) {
        if (root == null) return -1;

        dfs(root, root.data - 1, 0);

        return maxLen == 1 ? -1 : maxLen;
    }

    private void dfs(Node node, int parentVal, int len) {
        if (node == null) return;

        if (node.data == parentVal + 1) {
            len++;
        } else {
            len = 1;
        }

        maxLen = Math.max(maxLen, len);

        dfs(node.left, node.data, len);
        dfs(node.right, node.data, len);
    }
}
