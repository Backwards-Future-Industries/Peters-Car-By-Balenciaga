package enemy.aiMovement;

import java.util.*;

public class AStarSearch {
    private final int[][] map;
    private final int width;
    private final int height;

    public AStarSearch(int[][] map) {
        this.map = map;
        this.width = map.length;
        this.height = map[0].length;
    }

    public List<Node> findPath(Node start, Node goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        start.setC(0);
        start.setH(manhattanDistance(start, goal));
        start.setF(start.getH());

        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeG = current.getC() + 1;

                if (!openSet.contains(neighbor) || tentativeG < neighbor.getC()) {
                    neighbor.setC(tentativeG);
                    neighbor.setH(manhattanDistance(neighbor, goal));
                    neighbor.setF(neighbor.getC() + neighbor.getH());
                    neighbor.setParent(current);

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();

        int x = node.getX();
        int y = node.getY();

        if (x > 0 && (this.map[x - 1][y] == 0 || this.map[x - 1][y] == 1)) {
            neighbors.add(new Node(x - 1, y));
        }

        if (x < this.width - 1 && (this.map[x + 1][y] == 0 || this.map[x + 1][y] == 1)) {
            neighbors.add(new Node(x + 1, y));
        }

        if (y > 0 && (this.map[x][y - 1] == 0 || this.map[x][y - 1] == 1)) {
            neighbors.add(new Node(x, y - 1));
        }

        if (y < this.height - 1 && (this.map[x][y + 1] == 0 || this.map[x][y + 1] == 1)) {
            neighbors.add(new Node(x, y + 1));
        }

        return neighbors;
    }

    private List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();

        while (node != null) {
            path.add(node);
            node = node.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    private int manhattanDistance(Node current, Node goal) {
        int dx = Math.abs(current.getX() - goal.getX());
        int dy = Math.abs(current.getY() - goal.getY());

        // Higher cost for optional paths
        int optionalPathCost = 0;
        if (this.map[goal.getX()][goal.getY()] == 1)optionalPathCost = 1;

        return dx + dy + optionalPathCost;
    }
}
