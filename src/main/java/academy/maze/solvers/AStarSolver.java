package academy.maze.solvers;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;

import java.util.*;

public class AStarSolver implements Solver {

    private static class Node implements Comparable<Node> {
        final PointDTO point;
        final Node parent;
        final double g;
        final double h;
        final double f;

        Node(PointDTO point, Node parent, double g, double h) {
            this.point = point;
            this.parent = parent;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.f, other.f);
        }
    }

    @Override
    public PathDTO solve(MazeDTO maze, PointDTO start, PointDTO end) {
        if (!maze.isInBounds(start) || !maze.isInBounds(end)) {
            return new PathDTO();
        }

        if (maze.getCell(start) == CellType.WALL || maze.getCell(end) == CellType.WALL) {
            return new PathDTO();
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<PointDTO, Double> gScore = new HashMap<>();
        Set<PointDTO> closedSet = new HashSet<>();

        double startHCost = heuristic(start, end);
        openSet.add(new Node(start, null, 0, startHCost));
        gScore.put(start, 0.0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.point.equals(end)) {
                return reconstructPathDTO(current);
            }

            closedSet.add(current.point);

            for (PointDTO neighbor : getNeighbors(maze, current.point)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                double tentativeG = gScore.get(current.point) + 1;

                if (!gScore.containsKey(neighbor) || tentativeG < gScore.get(neighbor)) {
                    gScore.put(neighbor, tentativeG);
                    double hCost = heuristic(neighbor, end);
                    Node neighborNode = new Node(neighbor, current, tentativeG, hCost);

                    openSet.removeIf(node -> node.point.equals(neighbor));
                    openSet.add(neighborNode);
                }
            }
        }

        return new PathDTO();
    }

    private double heuristic(PointDTO a, PointDTO b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private List<PointDTO> getNeighbors(MazeDTO mazeDTO, PointDTO pointDTO) {
        List<PointDTO> neighbors = new ArrayList<>();
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

        for (int[] dir : directions) {
            int nx = pointDTO.x() + dir[0];
            int ny = pointDTO.y() + dir[1];

            if (mazeDTO.isInBounds(nx, ny) && mazeDTO.getCell(nx, ny) != CellType.WALL) {
                neighbors.add(new PointDTO(nx, ny));
            }
        }

        return neighbors;
    }

    private PathDTO reconstructPathDTO(Node endNode) {
        List<PointDTO> pathPoints = new ArrayList<>();
        Node current = endNode;

        while (current != null) {
            pathPoints.add(current.point);
            current = current.parent;
        }

        Collections.reverse(pathPoints);
        return new PathDTO(pathPoints);
    }
}
