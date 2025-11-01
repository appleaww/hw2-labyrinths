package academy.maze.solvers;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PathDTO;
import academy.maze.dto.PointDTO;
import academy.maze.util.MazeUtils;
import java.util.*;

public class DijkstraSolver implements Solver {

    @Override
    public PathDTO solve(MazeDTO maze, PointDTO start, PointDTO end) {
        if (!maze.isInBounds(start) || !maze.isInBounds(end)) {
            return new PathDTO();
        }

        if (maze.getCell(start) == CellType.WALL || maze.getCell(end) == CellType.WALL) {
            return new PathDTO();
        }

        Map<PointDTO, Double> distances = new HashMap<>();
        Map<PointDTO, PointDTO> previous = new HashMap<>();
        PriorityQueue<PointDTO> queue =
                new PriorityQueue<>(Comparator.comparingDouble(p -> distances.getOrDefault(p, Double.MAX_VALUE)));
        Set<PointDTO> visited = new HashSet<>();

        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {
            PointDTO current = queue.poll();

            if (current.equals(end)) {
                return reconstructPath(previous, end);
            }

            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            for (PointDTO neighbor : MazeUtils.getNeighbors(maze, current)) {
                if (visited.contains(neighbor)) {
                    continue;
                }

                double newDist = distances.get(current) + 1;

                if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        return new PathDTO();
    }

    private PathDTO reconstructPath(Map<PointDTO, PointDTO> previous, PointDTO end) {
        List<PointDTO> pathPoints = new ArrayList<>();
        PointDTO current = end;

        while (current != null) {
            pathPoints.add(current);
            current = previous.get(current);
        }

        Collections.reverse(pathPoints);
        return new PathDTO(pathPoints);
    }
}
