package academy.maze.util;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PointDTO;
import java.util.ArrayList;
import java.util.List;

public final class MazeUtils {

    private MazeUtils() {}

    public static List<PointDTO> getNeighbors(MazeDTO maze, PointDTO point) {
        List<PointDTO> neighbors = new ArrayList<>();
        int[][] directions = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

        for (int[] dir : directions) {
            int nx = point.x() + dir[0];
            int ny = point.y() + dir[1];

            if (maze.isInBounds(nx, ny) && maze.getCell(nx, ny) != CellType.WALL) {
                neighbors.add(new PointDTO(nx, ny));
            }
        }

        return neighbors;
    }
}
