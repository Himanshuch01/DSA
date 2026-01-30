class Node {
    Node[] children = new Node[26];
    int nodeId = -1;
}

class solution_day22 {
    private static final long INFINITY = 1L << 60;
    private Node trieRoot = new Node();
    private int nodeIdCounter;
    private long[][] costMatrix;
    private char[] sourceChars;
    private char[] targetChars;
    private Long[] memoization;

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int numTransformations = cost.length;
        costMatrix = new long[numTransformations << 1][numTransformations << 1];
        sourceChars = source.toCharArray();
        targetChars = target.toCharArray();

        for (int i = 0; i < costMatrix.length; ++i) {
            Arrays.fill(costMatrix[i], INFINITY);
            costMatrix[i][i] = 0;
        }

        for (int i = 0; i < numTransformations; ++i) {
            int fromNodeId = insertIntoTrie(original[i]);
            int toNodeId = insertIntoTrie(changed[i]);
            costMatrix[fromNodeId][toNodeId] = Math.min(costMatrix[fromNodeId][toNodeId], cost[i]);
        }

        for (int intermediate = 0; intermediate < nodeIdCounter; ++intermediate) {
            for (int from = 0; from < nodeIdCounter; ++from) {
                if (costMatrix[from][intermediate] >= INFINITY) {
                    continue;
                }
                for (int to = 0; to < nodeIdCounter; ++to) {
                    costMatrix[from][to] = Math.min(
                        costMatrix[from][to],
                        costMatrix[from][intermediate] + costMatrix[intermediate][to]
                    );
                }
            }
        }

        memoization = new Long[sourceChars.length];
        long result = computeMinimumCost(0);
        return result >= INFINITY ? -1 : result;
    }

    private int insertIntoTrie(String word) {
        Node currentNode = trieRoot;
        for (char character : word.toCharArray()) {
            int index = character - 'a';
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new Node();
            }
            currentNode = currentNode.children[index];
        }
        if (currentNode.nodeId < 0) {
            currentNode.nodeId = nodeIdCounter++;
        }
        return currentNode.nodeId;
    }

    private long computeMinimumCost(int position) {
        if (position >= sourceChars.length) {
            return 0;
        }

        if (memoization[position] != null) {
            return memoization[position];
        }

        long minCost = sourceChars[position] == targetChars[position] ?
            computeMinimumCost(position + 1) : INFINITY;

        Node sourceNode = trieRoot;
        Node targetNode = trieRoot;

        for (int endPos = position; endPos < sourceChars.length; ++endPos) {
            sourceNode = sourceNode.children[sourceChars[endPos] - 'a'];
            targetNode = targetNode.children[targetChars[endPos] - 'a'];

            if (sourceNode == null || targetNode == null) {
                break;
            }

            if (sourceNode.nodeId < 0 || targetNode.nodeId < 0) {
                continue;
            }

            long transformationCost = costMatrix[sourceNode.nodeId][targetNode.nodeId];
            if (transformationCost < INFINITY) {
                minCost = Math.min(minCost, transformationCost + computeMinimumCost(endPos + 1));
            }
        }

        memoization[position] = minCost;
        return minCost;
    }
}