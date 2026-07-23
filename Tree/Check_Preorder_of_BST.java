import java.util.*;

class Solution {
    public boolean canRepresentBST(List<Integer> arr) {
        Stack<Integer> stack = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;

        for (int val : arr) {
            if (val < lowerBound) {
                return false;
            }

            while (!stack.isEmpty() && val > stack.peek()) {
                lowerBound = stack.pop();
            }

            stack.push(val);
        }

        return true;
    }
}
