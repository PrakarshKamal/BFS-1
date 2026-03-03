import java.util.*;

// BFS Topological Sort O(V + E) time
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegrees = new int[numCourses];

        for (int[] pre : prerequisites) { // O(E) where E is edge, here E is all egdes which is prerequisites
            int dependant = pre[0];
            int independant = pre[1];

            indegrees[dependant]++;
            map.putIfAbsent(independant, new ArrayList<>());
            map.get(independant).add(dependant);
        }

        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) { // O(V) where V is vertex
            if (indegrees[i] == 0) { // it is independant course, its indegree[i] = 0
                q.offer(i);
                count++;
            }
        }

        while (!q.isEmpty()) { // O(V + E) time since we only get particular E for that V (its not V * E)
            int curr = q.poll();
            List<Integer> dependencies = map.get(curr);

            if (dependencies != null) {
                for (int dep : dependencies) {
                    indegrees[dep]--;

                    if (indegrees[dep] == 0) {
                        q.offer(dep);
                        count++;
                    }
                }
            }
        }
        if (count == numCourses) {
            return true;
        }
        return false;
    }
}