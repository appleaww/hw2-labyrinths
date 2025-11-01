package academy.maze.generators;

import academy.maze.dto.CellType;
import academy.maze.dto.MazeDTO;
import academy.maze.dto.PointDTO;

import java.util.*;

public class DFSGenerator implements Generator {

    @Override
    public MazeDTO generate(int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Maze dimensions must be at least 1x1");
        }

        MazeDTO maze = new MazeDTO(width, height);
        if (width < 3 || height < 3) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    maze.setCell(x, y, CellType.PASSAGE);
                }
            }
            return maze;
        }

        int genWidth = width % 2 == 0 ? width - 1 : width;
        int genHeight = height % 2 == 0 ? height - 1 : height;

        for (int y = 0; y < genHeight; y++) {
            for (int x = 0; x < genWidth; x++) {
                maze.setCell(x, y, CellType.WALL);
            }
        }

        Random random = new Random();
        Stack<PointDTO> stack = new Stack<>();

        int startX = 1;
        int startY = 1;
        maze.setCell(startX, startY, CellType.PASSAGE);
        stack.push(new PointDTO(startX, startY));

        int[][] directions = {{0, -2}, {2, 0}, {0, 2}, {-2, 0}};

        while (!stack.isEmpty()) {
            PointDTO current = stack.peek();
            List<int[]> neighbors = new ArrayList<>();

            for (int[] dir : directions) {
                int nx = current.x() + dir[0];
                int ny = current.y() + dir[1];

                if (nx > 0 && nx < genWidth && ny > 0 && ny < genHeight &&
                    maze.getCell(nx, ny) == CellType.WALL) {
                    neighbors.add(new int[]{dir[0], dir[1]});
                }
            }

            if (!neighbors.isEmpty()) {
                int[] dir = neighbors.get(random.nextInt(neighbors.size()));

                int wallX = current.x() + dir[0] / 2;
                int wallY = current.y() + dir[1] / 2;

                int nextX = current.x() + dir[0];
                int nextY = current.y() + dir[1];

                maze.setCell(wallX, wallY, CellType.PASSAGE);
                maze.setCell(nextX, nextY, CellType.PASSAGE);

                stack.push(new PointDTO(nextX, nextY));
            } else {
                stack.pop();
            }
        }

        return maze;
    }
}
