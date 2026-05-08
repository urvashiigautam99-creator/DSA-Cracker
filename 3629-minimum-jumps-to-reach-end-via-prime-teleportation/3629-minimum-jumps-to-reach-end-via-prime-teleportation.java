import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // 1. Pre-calculate prime factors for all numbers in the array
        // Map each prime to a list of indices that are multiples of it
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);
        
        // Sieve-like approach to find prime factors for the range of nums
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            if (val < 2) continue;
            
            // Get prime factors of nums[i]
            Set<Integer> factors = getPrimeFactors(val);
            for (int p : factors) {
                primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        // 2. BFS Setup
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visitedIndices = new boolean[n];
        Set<Integer> visitedPrimes = new HashSet<>();
        visitedIndices[0] = true;
        
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n - 1) return jumps;

                // --- Option 1: Adjacent Steps ---
                int[] neighbors = {curr - 1, curr + 1};
                for (int next : neighbors) {
                    if (next >= 0 && next < n && !visitedIndices[next]) {
                        visitedIndices[next] = true;
                        queue.add(next);
                    }
                }

                // --- Option 2: Prime Teleportation ---
                int val = nums[curr];
                if (isPrime(val)) {
                    // If we haven't exhausted this prime's multiples yet
                    if (!visitedPrimes.contains(val)) {
                        List<Integer> targets = primeToIndices.getOrDefault(val, new ArrayList<>());
                        for (int targetIdx : targets) {
                            if (!visitedIndices[targetIdx]) {
                                visitedIndices[targetIdx] = true;
                                queue.add(targetIdx);
                            }
                        }
                        visitedPrimes.add(val); // Mark prime as "fully explored"
                    }
                }
            }
            jumps++;
        }

        return -1;
    }

    // Helper: Basic Primality Test
    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // Helper: Get unique prime factors
    private Set<Integer> getPrimeFactors(int n) {
        Set<Integer> factors = new HashSet<>();
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add(n);
        return factors;
    }
}