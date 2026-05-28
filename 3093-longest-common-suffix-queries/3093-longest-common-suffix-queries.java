class Solution {
    // Definition for the Trie Node
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        // Tracks the index of the best word passing through this node
        int bestIndex = -1; 
        // Tracks the length of that best word to apply tie-breaking rules
        int minLength = Integer.MAX_VALUE; 
    }

    private TrieNode root;

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        root = new TrieNode();
        int globalBestIndex = 0;
        int globalMinLength = wordsContainer[0].length();

        // 1. Find the absolute global fallback (shortest, then earliest index)
        for (int i = 0; i < wordsContainer.length; i++) {
            int len = wordsContainer[i].length();
            if (len < globalMinLength) {
                globalMinLength = len;
                globalBestIndex = i;
            }
        }
        
        // Initialize the root node with the global fallback defaults
        root.minLength = globalMinLength;
        root.bestIndex = globalBestIndex;

        // 2. Build the Trie using reversed words
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        // 3. Process each query
        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String word, int wordIdx) {
        TrieNode curr = root;
        int len = word.length();

        // Traverse the string from right to left (building suffix match)
        for (int i = len - 1; i >= 0; i--) {
            int charIdx = word.charAt(i) - 'a';
            if (curr.children[charIdx] == null) {
                curr.children[charIdx] = new TrieNode();
            }
            curr = curr.children[charIdx];

            // Update the node's tracking if this word is strictly shorter
            // (Strictly less than ensures we keep the earliest index for ties)
            if (len < curr.minLength) {
                curr.minLength = len;
                curr.bestIndex = wordIdx;
            }
        }
    }

    private int search(String query) {
        TrieNode curr = root;
        int lastBestIndex = root.bestIndex;

        // FIXED: Using query.length() instead of query.length
        for (int i = query.length() - 1; i >= 0; i--) {
            int charIdx = query.charAt(i) - 'a';
            if (curr.children[charIdx] == null) {
                break;
            }
            curr = curr.children[charIdx];
            lastBestIndex = curr.bestIndex;
        }
        
        return lastBestIndex;
    }
}