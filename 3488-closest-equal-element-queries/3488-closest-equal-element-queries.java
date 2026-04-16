import java.util.*;
import java.util.stream.Collectors;

class Solution {
    // 1. Changed return type to List<Integer>
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int targetIdx = queries[i];
            int val = nums[targetIdx];
            List<Integer> indices = map.get(val);

            if (indices.size() <= 1) {
                result[i] = -1;
                continue;
            }

            int pos = Collections.binarySearch(indices, targetIdx);
            
            int leftIdx = (pos > 0) ? indices.get(pos - 1) : indices.get(indices.size() - 1);
            int rightIdx = (pos < indices.size() - 1) ? indices.get(pos + 1) : indices.get(0);

            result[i] = Math.min(getDist(targetIdx, leftIdx, n), getDist(targetIdx, rightIdx, n));
        }

        // 2. Convert int[] to List<Integer> to match the signature
        return Arrays.stream(result).boxed().collect(Collectors.toList());
    }

    private int getDist(int i, int j, int n) {
        int absDist = Math.abs(i - j);
        return Math.min(absDist, n - absDist);
    }
}