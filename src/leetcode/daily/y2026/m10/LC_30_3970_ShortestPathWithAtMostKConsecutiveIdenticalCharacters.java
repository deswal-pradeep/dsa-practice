package leetcode.daily.y2026.m10;

import java.util.*;

public class LC_30_3970_ShortestPathWithAtMostKConsecutiveIdenticalCharacters {
    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    static class Path {
        char c;
        int frequency;
        int weight;
        int node;

        public Path(Character c, int frequency, int weight, int node) {
            this.node = node;
            this.c = c;
            this.frequency = frequency;
            this.weight = weight;
        }
    }
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        Map<Integer, List<Edge>> eMap = new HashMap<>();

        for (int[] edge : edges) {
            eMap.computeIfAbsent(edge[0], x -> new ArrayList<>())
                    .add(new Edge(edge[0], edge[1], edge[2]));
        }

        long[][] distance = new long[n][k + 1];

        for (long[] row : distance) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        PriorityQueue<Path> queue =
                new PriorityQueue<>(Comparator.comparingLong(a -> a.weight));

        queue.offer(new Path(labels.charAt(0), 1, 0, 0));
        distance[0][1] = 0L;

        while (!queue.isEmpty()) {
            Path curr = queue.poll();

            // Ignore outdated queue entries.
            if (curr.weight > distance[curr.node][curr.frequency]) {
                continue;
            }

            if (curr.node == n - 1) {
                return (int) curr.weight;
            }

            List<Edge> outgoing =
                    eMap.getOrDefault(curr.node, Collections.emptyList());

            for (Edge edge : outgoing) {
                int nextNode = edge.to;

                int newFreq = curr.c == labels.charAt(nextNode)
                        ? curr.frequency + 1
                        : 1;

                if (newFreq > k) {
                    continue;
                }

                int newWeight = curr.weight + edge.weight;

                if (newWeight < distance[nextNode][newFreq]) {
                    distance[nextNode][newFreq] = newWeight;

                    queue.offer(new Path(
                            labels.charAt(nextNode),
                            newFreq,
                            newWeight,
                            nextNode
                    ));
                }
            }
        }

        return -1;
    }

    static void main() {
        int[][] edges = new int[][]{{0,1,1}, {1,2,1}, {0,2,3}};
        int ans = new LC_30_3970_ShortestPathWithAtMostKConsecutiveIdenticalCharacters().shortestPath(3, edges, "aab", 1);
        System.out.println(ans);
    }
}
