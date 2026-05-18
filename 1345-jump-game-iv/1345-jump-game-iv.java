import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n <= 1) return 0;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currIdx = queue.poll();

                if (currIdx == n - 1) return steps;

                List<Integer> nextIndices = graph.getOrDefault(arr[currIdx], new ArrayList<>());
                nextIndices.add(currIdx + 1);
                nextIndices.add(currIdx - 1);

                for (int neighbor : nextIndices) {
                    if (neighbor >= 0 && neighbor < n && !visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
                // Clear the entry for this value to avoid looking it up again
                graph.remove(arr[currIdx]);
            }
            steps++;
        }
        return steps;
    }
}